package com.transaction.monitoring.monitoringservice.service.gateway.fourstop;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction.monitoring.monitoringservice.entity.BinBase;
import com.transaction.monitoring.monitoringservice.entity.MonitoringRequest;
import com.transaction.monitoring.monitoringservice.entity.MonitoringResponse;
import com.transaction.monitoring.monitoringservice.entity.TriggeredRules;
import com.transaction.monitoring.monitoringservice.registry.AbstractTMGateway;
import com.transaction.monitoring.monitoringservice.service.utils.FourStopUtils;
import com.transaction.monitoring.monitoringservice.service.utils.Functions;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;


@Service("fourstop")
public class FourStopServiceGateway extends AbstractTMGateway{

    Logger logger = Logger.getLogger(FourStopServiceGateway.class.getName());

    @Autowired
    private Functions functions;

    @Autowired
    private FourStopUtils utils;

    @Value("${fourstop.transactionUrl}")
    private String transactionUrl;

    @Value("${fourstop.updateTransactionUrl}")
    private String updateTransactionUrl;

    @Autowired
    private FourStopRequestVO requestVO;

    @Autowired
    private MonitoringResponse monitoringResponse;

    @Override
    public MonitoringResponse processTransaction(MonitoringRequest monitoringRequest)  {
        logger.info("-----inside processTransaction----");
        logger.info("-----inside transactionUrl----"+transactionUrl);
        logger.info("-----inside updateTransactionUrl----"+updateTransactionUrl);

        try {

            utils.setFourStopRequestParameter(monitoringRequest,requestVO);
            requestVO.setMerchant_id(monitoringRequest.getAccount().getUsername());
            requestVO.setPassword(monitoringRequest.getAccount().getPassword());

            HashMap requestHash=requestVO.getHashMap();
            logger.info("Request----"+requestHash);
            //String response=utils.callAPI(requestHash, transactionUrl);
            
            String response="{\"internal_trans_id\":\"70250352\",\"status\":0,\"desc\":\"Success\",\"rec\":\"Review\",\"score\":60.46,\"third_party\":\"\",\"processors\":\"\",\"confidence_level\":100,\"transaction_type\":\"Deposit\",\"rules_triggered\":[{\"name\":\"BIN/Country Mismatch (BIN Info)\",\"score\":\"30\",\"display_to_merchant\":1},{\"name\":\"Payment Method Verification : BIN/Country Mismatch (BIN Info)\",\"score\":\"30\",\"display_to_merchant\":1}],\"bin_information\":{\"bank_name\":\"Not Found\",\"bank_location\":\"UNITED KINGDOM\",\"card_type\":\"CLASSIC\",\"card_level\":\"CREDIT\",\"card_brand_db\":\"VISA\",\"card_brand_script\":\"VISA\",\"iso_card_country\":\"GB\"},\"scrubber_results\":{\"geo_check\":[],\"idv_usa\":[],\"phoneid\":[],\"idv_usa2\":[],\"idv_global\":[],\"address_check\":[],\"idv_br\":[],\"bav_usa\":[],\"bav_advanced\":[],\"cb_aml\":[],\"cb_bvs\":[],\"email_intel\":[],\"device_id_advanced\":[],\"compliance_watchlist\":[],\"compliance_watchlist2\":[],\"idv_global2\":[],\"idv_global3\":[],\"idv_global4\":[],\"idv_uk_1\":[],\"phone_verify2\":[],\"breached_intel\":[],\"idv_usa3\":[],\"compliance_watchlist3\":{\"cw3_code\":200,\"cw3_status\":\"success\",\"cw3_id\":\"D0AHQDPE7EEX8CP-,- 9L1ATQF8JIFBNY8-,- VDXQTUJRXWM5WA4\",\"cw3_ref\":\"1596211441-qaslOxkj\",\"cw3_searcher_id\":\"7199-,- 7199\",\"cw3_assignee_id\":\"7199-,- 7199\",\"cw3_filters_birth_year\":2016,\"cw3_filters_exact_match\":false,\"cw3_filters_fuzziness\":0.2,\"cw3_match_status\":\"potential_match-,- potential_match-,- potential_match\",\"cw3_risk_level\":\"unknown\",\"cw3_search_term\":\"John Max\",\"cw3_submitted_term\":\"John Max\",\"cw3_client_ref\":\"integration@4stop.com\",\"cw3_total_hits\":3,\"cw3_updated_at\":\"2020-07-31 16:04:01\",\"cw3_created_at\":\"2020-07-31 16:04:01\",\"cw3_limit\":100,\"cw3_offset\":0,\"cw3_aka_name\":\"John Max Gadebo-,- John Maxwell Hodgkin-,- John Maxwell Elliott-,- Max Elliott\",\"cw3_assets_public_url\":\"\",\"cw3_associates_association\":\"\",\"cw3_associates_name\":\"\",\"cw3_entity_type\":\"person-,- person-,- person\",\"cw3_countries_value\":\"New Zealand, United States\",\"cw3_countries_source\":\"N/A\",\"cw3_person_name_value\":\"\",\"cw3_person_name_source\":\"\",\"cw3_organization_value\":\"\",\"cw3_organization_source\":\"\",\"cw3_comment_value\":\"\",\"cw3_comment_source\":\"\",\"cw3_comments_value\":\"\",\"cw3_comments_source\":\"\",\"cw3_titles_value\":\"\",\"cw3_titles_source\":\"\",\"cw3_exclusion_type_value\":\"\",\"cw3_exclusion_type_source\":\"\",\"cw3_exclusion_program_value\":\"\",\"cw3_exclusion_program_source\":\"\",\"cw3_country_value\":\"New Zealand-,- United States\",\"cw3_country_source\":\"complyadvantage-adverse-media-,- united-states-county-governments\",\"cw3_excluding_agency_value\":\"\",\"cw3_excluding_agency_source\":\"\",\"cw3_ofac_id_value\":\"\",\"cw3_ofac_id_source\":\"\",\"cw3_programs_value\":\"\",\"cw3_programs_source\":\"\",\"cw3_justification_value\":\"\",\"cw3_justification_source\":\"\",\"cw3_other_information_value\":\"\",\"cw3_other_information_source\":\"\",\"cw3_program_name_value\":\"\",\"cw3_program_name_source\":\"\",\"cw3_remark_value\":\"\",\"cw3_remark_source\":\"\",\"cw3_role_value\":\"\",\"cw3_role_source\":\"\",\"cw3_regime_value\":\"\",\"cw3_regime_source\":\"\",\"cw3_committees_value\":\"\",\"cw3_committees_source\":\"\",\"cw3_region_value\":\"Pike County, IN\",\"cw3_region_source\":\"united-states-county-governments\",\"cw3_special_economic_measure_act_value\":\"\",\"cw3_special_economic_measure_act_source\":\"\",\"cw3_entity_type_value\":\"\",\"cw3_entity_type_source\":\"\",\"cw3_am_types_value\":\"\",\"cw3_am_types_source\":\"\",\"cw3_name_value\":\"\",\"cw3_name_source\":\"\",\"cw3_chamber_value\":\"County Government\",\"cw3_chamber_source\":\"united-states-county-governments\",\"cw3_renseignements_compl_mentaires_value\":\"\",\"cw3_renseignements_compl_mentaires_source\":\"\",\"cw3_nature_value\":\"\",\"cw3_nature_source\":\"\",\"cw3_category_value\":\"\",\"cw3_category_source\":\"\",\"cw3_other_events_value\":\"\",\"cw3_other_events_source\":\"\",\"cw3_date_modified_value\":\"\",\"cw3_date_modified_source\":\"\",\"cw3_political_party_value\":\"Independent\",\"cw3_political_party_source\":\"complyadvantage\",\"cw3_date_of_birth_value\":\"\",\"cw3_date_of_birth_source\":\"\",\"cw3_place_of_birth_value\":\"\",\"cw3_place_of_birth_source\":\"\",\"cw3_related_url_value\":\"https://www.bcb.gov.br/estabilidadefinanceira/diarioeletronico/?data=2019-01-29\",\"cw3_related_url_source\":\"brazil-banco-central-administrative-penalty-process\",\"cw3_political_position_value\":\"sdsd-,- Council\",\"cw3_political_position_source\":\"complyadvantage-,- united-states-county-governments\",\"cw3_passport_value\":\"\",\"cw3_passport_source\":\"\",\"cw3_active_end_date_value\":\"\",\"cw3_active_end_date_source\":\"\",\"cw3_active_start_date_value\":\"\",\"cw3_active_start_date_source\":\"\",\"cw3_last_updated_utc\":\"2018-04-12T11:13:52Z-,- 2020-06-15T04:01:40Z-,- 2018-12-05T15:20:19Z\",\"cw3_name\":\"John Max Gadebo-,- John Maxwell Hodgkin-,- Max Elliott\",\"cw3_source_notes_europe-sanctions-list_listing_started_utc\":\"\",\"cw3_source_notes_hm-treasury-list_listing_started_utc\":\"\",\"cw3_source_notes_swiss-seco-list_listing_started_utc\":\"\",\"cw3_source_notes_us-system-for-award-management-exclusions_listing_started_utc\":\"\",\"cw3_sources\":\"complyadvantage-,- brazil-banco-central-administrative-penalty-process-,- united-states-county-governments-,- complyadvantage-adverse-media\",\"cw3_types\":\"pep-,- pep-class-1-,- warning-,- adverse-media-,- adverse-media-general-,- pep-,- pep-class-4\",\"cw3_match_types\":\"name_exact-,- equivalent_name-,- equivalent_aka\",\"cw3_match_types_details\":\"John Max Gadebo (name: john name_exact name: max name_exact type: name )-,- John Maxwell Hodgkin (name: john name_exact name: max equivalent_name type: name )-,- John Maxwell Elliott (name: john name_exact name: max equivalent_name type: aka )\",\"cw3_score\":\"12.417639-,- 5.705615-,- 5.648375\",\"cw3_share_url\":\"https://app.complyadvantage.com/public/search/1596211441-qaslOxkj/c17184fedaee\",\"cw3_original_country_text_value\":\"\",\"cw3_original_country_text_source\":\"\",\"cw3_adverse_media_subtypes_value\":\"\",\"cw3_adverse_media_subtypes_source\":\"\",\"cw3_basis_for_listing_value\":\"\",\"cw3_basis_for_listing_source\":\"\",\"cw3_description_value\":\"\",\"cw3_description_source\":\"\",\"cw3_designation_date_value\":\"\",\"cw3_designation_date_source\":\"\",\"cw3_legal_basis_value\":\"\",\"cw3_legal_basis_source\":\"\",\"cw3_list_id_value\":\"\",\"cw3_list_id_source\":\"\",\"cw3_list_name_value\":\"\",\"cw3_list_name_source\":\"\",\"cw3_list_history_value\":\"\",\"cw3_list_history_source\":\"\",\"cw3_other_info_value\":\"\",\"cw3_other_info_source\":\"\",\"cw3_program_value\":\"\",\"cw3_program_source\":\"\",\"cw3_program_entry_value\":\"\",\"cw3_program_entry_source\":\"\",\"cw3_reason_value\":\"\",\"cw3_reason_source\":\"\",\"cw3_regulation_value\":\"\",\"cw3_regulation_source\":\"\",\"cw3_sanction_type_value\":\"\",\"cw3_sanction_type_source\":\"\",\"cw3_type_value\":\"\",\"cw3_type_source\":\"\",\"cw3_type_of_wmd_value\":\"\",\"cw3_type_of_wmd_source\":\"\",\"cw3_un_list_type_value\":\"\",\"cw3_un_list_type_source\":\"\",\"cw3_unique_id_value\":\"\",\"cw3_unique_id_source\":\"\"}}}";

            logger.info("Response-----"+response);

            if(functions.isValueNull(response) && response.startsWith("{")){

                org.json.JSONObject jsonObject = new org.json.JSONObject(response);

                if(jsonObject.has("internal_trans_id")){
                    monitoringResponse.setResponseId(jsonObject.getString("internal_trans_id"));
                }
                if(jsonObject.has("status")){
                    monitoringResponse.setStatus(String.valueOf(jsonObject.getInt("status")));
                }
                if(jsonObject.has("desc")){
                    monitoringResponse.setDescription(jsonObject.getString("desc"));
                }
                if(jsonObject.has("rec")){
                    monitoringResponse.setRecommendation(jsonObject.getString("rec"));
                }
                if(jsonObject.has("score")){
                    monitoringResponse.setScore(jsonObject.getDouble("score"));
                }
                if(jsonObject.has("confidence_level")){
                    monitoringResponse.setConfidence_level(jsonObject.getInt("confidence_level"));
                }

                if(jsonObject.has("rules_triggered")){
                   JSONArray jsonArray= jsonObject.getJSONArray("rules_triggered");
                    if(jsonArray!=null && jsonArray.length()>0){
                        List<TriggeredRules> triggeredRulesList = new ArrayList<>();
                        TriggeredRules triggeredRules= null;
                        for(int i=0;i<jsonArray.length();i++){
                         Object object=jsonArray.get(i);
                            if(object instanceof org.json.JSONObject){

                                org.json.JSONObject jsonObject1=(org.json.JSONObject) object;
                                triggeredRules = new TriggeredRules();

                                triggeredRules.setFraud_transid(String.valueOf(monitoringRequest.getFraud_transaction_id()));
                               if(jsonObject1.has("name")){
                                   triggeredRules.setRulename(jsonObject1.getString("name"));
                               }
                                if(jsonObject1.has("score")){
                                    triggeredRules.setRulescore(jsonObject1.getString("score"));
                                }
                                triggeredRulesList.add(triggeredRules);
                            }
                        }
                        monitoringResponse.setTriggeredRulesList(triggeredRulesList);
                    }
                }

                BinBase binBase = new BinBase();
                binBase.setFirstName(monitoringRequest.getFirstname());
                binBase.setLastName(monitoringRequest.getLastname());
                binBase.setFirstSix(monitoringRequest.getFirstsix());
                binBase.setLastFour(monitoringRequest.getLastfour());
                binBase.setEmail(monitoringRequest.getEmailaddr());
                binBase.setFraud_transid(String.valueOf(monitoringRequest.getFraud_transaction_id()));
                if(jsonObject.has("bin_information")){

                    binBase.setFirstName(monitoringRequest.getFirstname());
                    binBase.setLastName(monitoringRequest.getLastname());
                    binBase.setFirstSix(monitoringRequest.getFirstsix());
                    binBase.setLastFour(monitoringRequest.getLastfour());
                    binBase.setEmail(monitoringRequest.getEmailaddr());
                    org.json.JSONObject jsonObject2 =jsonObject.getJSONObject("bin_information");
                    if(jsonObject2.has("bank_name")){
                        binBase.setBankName(jsonObject2.getString("bank_name"));
                    }
                    if(jsonObject2.has("bank_location")){
                        binBase.setBankLocation(jsonObject2.getString("bank_location"));
                    }
                    if(jsonObject2.has("card_type")){
                        binBase.setCardType(jsonObject2.getString("card_type"));
                    }
                    if(jsonObject2.has("card_level")){
                        binBase.setCardLevel(jsonObject2.getString("card_level"));
                    }
                    if(jsonObject2.has("iso_card_country")){
                        binBase.setCardCountry(jsonObject2.getString("iso_card_country"));
                    }
                }
                monitoringResponse.setBinBases(binBase);
            }



        } catch (ParseException e) {
            logger.info("ParseException-----" + e);
        } catch (JSONException e) {
            logger.info("JSONException-----" + e);
        }
        return monitoringResponse;
    }



    @Override
    public MonitoringResponse updateTransaction(MonitoringRequest request) {
     /*   FourStopRequestVO requestVO = (FourStopRequestVO) request;
        FourStopResponseVO gatewayResponse=  restTemplate.postForObject(updateTransactionUrl, requestVO, FourStopResponseVO.class);
        return gatewayResponse;*/
        return null;
    }
}
