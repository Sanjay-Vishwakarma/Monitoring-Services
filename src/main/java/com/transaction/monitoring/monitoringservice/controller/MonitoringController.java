package com.transaction.monitoring.monitoringservice.controller;

import com.transaction.monitoring.monitoringservice.entity.*;
import com.transaction.monitoring.monitoringservice.model.*;
import com.transaction.monitoring.monitoringservice.registry.AbstractTMGateway;
import com.transaction.monitoring.monitoringservice.registry.TMGatewayService;
import com.transaction.monitoring.monitoringservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;


@RestController
@RequestMapping("/api")
@Configuration
@PropertySource("classpath:fourStopRiskRule.properties")
public class MonitoringController {

    Logger logger = Logger.getLogger(MonitoringController.class.getName());

    @Autowired
    private TMGatewayService service;

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private AccountMappingRepository accountMappingRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private GatewayMasterRepository gatewayMasterRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TriggeredRulesRepository triggeredRulesRepository;

    @Autowired
    private BinBaseRepository binBaseRepository;

    @Autowired
    private MonitoringResponse monitoringResponse;

    @Autowired
    private ErrorResponse errorResponse;

    @Autowired
    private Environment env;

    @Autowired
    private RiskRuleRepository riskRuleRepository;


    @PostMapping("/transaction")
    public MonitoringResponse processTransaction(@Valid @RequestBody MonitoringRequest monitoringRequest,@RequestHeader HttpHeaders httpHeaders){
        logger.info("-----inside processTransaction-----");

        String remoteAddr = "";
        if(httpHeaders.get("X-Forwarded-For")!=null){
            remoteAddr=  String.valueOf(httpHeaders.get("X-Forwarded-For"));
        }

        String userAgent = "";
        if(httpHeaders.get("User-Agent")!=null){
            userAgent=  String.valueOf(httpHeaders.get("User-Agent"));
        }
        InetSocketAddress host=httpHeaders.getHost();
        int serverPort = host.getPort();
        String servletPath =host.getHostName();

        String header = "Client =" + remoteAddr + ":" + serverPort + ",X-Forwarded=" + servletPath + ",User-Agent="+userAgent;

        logger.info("header----"+header);

        Optional<Partner> partnerRepo= partnerRepository.findById(monitoringRequest.getPartnerid());

        if(partnerRepo.isPresent()){
            logger.info("----successfully reterived data from partner table----");
            Partner partner= partnerRepo.get();
            monitoringRequest.setModified_by(partner.getUsername());
            monitoringRequest.setRole(partner.getRole());
            monitoringRequest.setRequest_header(header);
            monitoringRequest.setHeader_ip(remoteAddr);
            logger.info("---partner level isTransactionMonitoring flag-----"+partner.getIsTransactionMonitoring());
            if("Y".equals(partner.getIsTransactionMonitoring())){
                List<AccountMapping> accountList=  accountMappingRepository.findByPartnerId(monitoringRequest.getPartnerid());
                logger.info("account List-----"+accountList.size());
                for (AccountMapping accountMapping : accountList){
                    logger.info("account level isTransactionMonitoring flag-----"+accountMapping.getIsTransactionMonitoring());
                    if("Y".equals(accountMapping.getIsTransactionMonitoring())){
                        monitoringRequest.setIscompliance_peps(accountMapping.getIscompliance_peps());
                        monitoringRequest.setIsgeo_check(accountMapping.getIsgeo_check());
                        monitoringRequest.setIsidv_global_4_check(accountMapping.getIsidv_global_4_check());
                        monitoringRequest.setIsemail_profiling(accountMapping.getIsemail_profiling());
                        monitoringRequest.setIsaddress_check(accountMapping.getIsaddress_check());

                        Optional<Account> accountRepo= accountRepository.findById(accountMapping.getFsaccountid());

                        if(accountRepo.isPresent()){
                            logger.info("----successfully reterived data from account table----");
                            Account account=accountRepo.get();
                            logger.info("------accountname-----"+account.getAccountname());
                            logger.info("------username-----"+account.getUsername());
                            logger.info("------password-----"+account.getPassword());
                            logger.info("------isTest-----"+account.getIsTest());
                            logger.info("------fsId-----"+account.getFsid());

                            Optional<GatewayMaster> masterRepo=gatewayMasterRepository.findById(Integer.parseInt(account.getFsid()));

                            if(masterRepo.isPresent()){
                                long unixTime = System.currentTimeMillis() / 1000L;
                                logger.info("----successfully reterived data from master table----");
                                GatewayMaster gatewayMaster=masterRepo.get();
                                logger.info("------accountname-----"+gatewayMaster.getGatewayname());
                                monitoringRequest.setFsid(account.getFsid());
                                monitoringRequest.setFraud_trans_status("PENDING");
                                monitoringRequest.setDtstamp((int) unixTime);
                                monitoringRequest=  transactionRepository.save(monitoringRequest);

                                monitoringRequest.setAccount(account);
                                logger.info("detailId-----"+monitoringRequest.getFraud_transaction_id());

                                logger.info("gateway-----"+gatewayMaster.getGatewayname());
                                AbstractTMGateway gateway=service.gateway(gatewayMaster.getGatewayname());

                                monitoringResponse= gateway.processTransaction(monitoringRequest);

                                if(monitoringResponse!=null) {
                                    logger.info("-----inside response-----");

                                    monitoringResponse.setTransId(monitoringRequest.getFraud_transaction_id());

                                    monitoringRequest= transactionRepository.getOne(monitoringRequest.getFraud_transaction_id());
                                    monitoringRequest.setStatus("N/A");
                                    monitoringRequest.setFraud_trans_status("COMPLETED");
                                    monitoringRequest.setDescription(monitoringResponse.getDescription());
                                    monitoringRequest.setScore(monitoringResponse.getScore());
                                    monitoringRequest.setConfidence_level(monitoringResponse.getConfidence_level());
                                    monitoringRequest.setRecommendation(monitoringResponse.getRecommendation());
                                    monitoringRequest.setResponseId(monitoringResponse.getResponseId());

                                    //This will update the existing record
                                    transactionRepository.save(monitoringRequest);

                                    List<Integer> listOfscore= new ArrayList<>();
                                    if (monitoringResponse.getTriggeredRulesList() != null && monitoringResponse.getTriggeredRulesList().size() > 0) {
                                        List<TriggeredRules> rulesList = monitoringResponse.getTriggeredRulesList();
                                        int defaultScore=0;
                                        String score="0";


                                        for (TriggeredRules triggeredRules : rulesList) {
                                            logger.info("Rule Name----"+triggeredRules.getRulename());
                                            if(triggeredRules.getRulename()!=null)
                                            {
                                                String ruleName=triggeredRules.getRulename();
                                                ruleName=ruleName.replace(":","");
                                                logger.info("replace 1---"+ruleName);
                                                ruleName=ruleName.replace(" ", "_");
                                                logger.info("replace 2---"+ruleName);

                                                String ruleId=env.getProperty(ruleName);
                                                logger.info("Rule Id----"+ruleId);
                                                if(ruleId!=null)
                                                {
                                                    score = riskRuleRepository.getScore(ruleId, String.valueOf(monitoringRequest.getPartnerid()));
                                                    if (score == null) {
                                                        score = riskRuleRepository.getScore(ruleId, "0");
                                                    }
                                                }else
                                                {
                                                    score=triggeredRules.getRulescore();
                                                }
                                                listOfscore.add(Integer.valueOf(score));
                                                logger.info("Score---"+score);

                                                if(score!=null) {
                                                    triggeredRules.setDbscore(Integer.parseInt(score));
                                                }
                                            }
                                            logger.info("RuleScore----"+triggeredRules.getRulescore());
                                            triggeredRulesRepository.save(triggeredRules);
                                        }
                                    }

                                    int responseScore=0;
                                    Iterator<Integer> iterator= listOfscore.iterator();
                                    while (iterator.hasNext()){
                                        responseScore+=iterator.next();
                                    }

                                    if(responseScore>=100){
                                        monitoringResponse.setScore(100);
                                    }else{
                                        monitoringResponse.setScore(responseScore);
                                    }


                                    if (monitoringResponse.getScore() < 50.0) {
                                        monitoringResponse.setRecommendation("Approve");
                                    } else if (monitoringResponse.getScore() >= 50.0 && monitoringResponse.getScore() <= 89.0) {
                                        monitoringResponse.setRecommendation("Review");
                                    } else if (monitoringResponse.getScore() > 89.0) {
                                        monitoringResponse.setRecommendation("Reject");
                                    }

                                    if (monitoringResponse.getBinBases() != null) {
                                        BinBase binBase = monitoringResponse.getBinBases();
                                        binBaseRepository.save(binBase);
                                        logger.info("binBase-----"+binBase.getId());
                                    }
                                }else {
                                    errorResponse.setStatus("-6");
                                    errorResponse.setDescription("Internal Error ,please try after sometimes");
                                    monitoringResponse.setErrorResponse(errorResponse);
                                }
                            }else {
                                errorResponse.setStatus("-5");
                                errorResponse.setDescription("Fraud System not found by fsid-"+account.getFsid());
                                monitoringResponse.setErrorResponse(errorResponse);
                            }
                        }else {
                            errorResponse.setStatus("-4");
                            errorResponse.setDescription("Account not found by accountId-"+accountMapping.getFsaccountid());
                            monitoringResponse.setErrorResponse(errorResponse);
                        }

                        return monitoringResponse;
                    }else {
                        errorResponse.setStatus("-3");
                        errorResponse.setDescription("This functionality is not allowed ,please check your account level configuration.");
                        monitoringResponse.setErrorResponse(errorResponse);
                    }
                }
            }else {
                errorResponse.setStatus("-2");
                errorResponse.setDescription("This functionality is not allowed ,please check your partner level configuration.");
                monitoringResponse.setErrorResponse(errorResponse);
            }
        } else {
            errorResponse.setStatus("-1");
            errorResponse.setDescription("Partner not found by partnerId-"+monitoringRequest.getPartnerid());
            monitoringResponse.setErrorResponse(errorResponse);
        }

        return monitoringResponse ;
    }
}
