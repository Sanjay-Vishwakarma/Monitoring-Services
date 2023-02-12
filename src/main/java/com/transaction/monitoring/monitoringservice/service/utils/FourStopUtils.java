package com.transaction.monitoring.monitoringservice.service.utils;

import com.transaction.monitoring.monitoringservice.entity.MonitoringRequest;
import com.transaction.monitoring.monitoringservice.service.gateway.fourstop.FourStopRequestVO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Admin on 7/17/20.
 */

@Component
public class FourStopUtils{

    Logger logger =Logger.getLogger(FourStopUtils.class.getName());

   /* @Autowired
    private RestTemplate restTemplate;
*/


    /*public String doHttpConnection(String request){
        logger.info("-----inside doHttpConnection-----");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/x-www-form-urlencoded");

        HttpEntity<String> response = restTemplate.exchange(request, HttpMethod.POST, new HttpEntity(headers), String.class);
        logger.info("-----inside res-----"+response.getBody());
        return response.getBody();
    }*/

    public String callAPI(HashMap requestMap, String URL)
    {
        HashMap responseMap = new HashMap();
        FourStopUtils fourStopUtils= new FourStopUtils();
        String reqParameters = fourStopUtils.joinMapValue(requestMap, '&');
        logger.info("Request :" + reqParameters);
        org.json.JSONObject json =null;
        String response="";

        try
        {
            Date date541=new Date();
            response = fourStopUtils.doPostHTTPSURLConnectionClient(URL, reqParameters);
            logger.info("PZFraudProcessor doPostURLConnection(fraud gateway call) diff time 541########"+(new Date().getTime()-date541.getTime()));
            logger.info("Response :" + response);
            json = new org.json.JSONObject(response);
            Iterator i = json.keys();
            while (i.hasNext())
            {
                String key = (String) i.next();
                logger.info("Response key :  " + key + "   Value:" + json.get(key));
                responseMap.put(key,json.get(key));
            }
            responseMap.put("jsonObject",json);

        }
        catch (Exception e)
        {
            responseMap.put("status","1");
            responseMap.put("score","0");
            responseMap.put("desc",e.getMessage());
            logger.info(e.getMessage());
        }
        return response;
    }

    public String doPostHTTPSURLConnectionClient(String url,String request) throws IOException {
        logger.info("Inside doPostHTTPSURLConnectionClient---" + url);

        String result = null;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        try {
            //SSLUtils.setupSecurityProvider();
            //SSLUtils.trustAllHttpsCertificates();
            //System.setProperty("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
            //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            //System.setProperty("https.protocols", "TLSv1.2");
            URL url1 = new URL(url);
            URLConnection con = url1.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            out = new BufferedOutputStream(con.getOutputStream());
            byte outBuf[] = request.getBytes("UTF-8");
            out.write(outBuf);
            out.close();

            in = new BufferedInputStream(con.getInputStream());
            result = ReadByteStream(in);
        }
        catch (Exception ex)
        {   ex.printStackTrace();
        }
        finally
        {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }
        if (result == null)
            return "";
        else
            return result;
    }

    private String ReadByteStream(BufferedInputStream in) throws IOException
    {
        LinkedList<ConnectionUtilsBuf> bufList = new LinkedList<ConnectionUtilsBuf>();
        int size = 0;
        byte buf[];
        do {
            buf = new byte[128];
            int num = in.read(buf);
            if (num == -1)
                break;
            size += num;
            bufList.add(new ConnectionUtilsBuf(buf, num));
        } while (true);
        buf = new byte[size];
        int pos = 0;
        for (ListIterator<ConnectionUtilsBuf> p = bufList.listIterator(); p.hasNext();) {
            ConnectionUtilsBuf b = p.next();
            for (int i = 0; i < b.size;) {
                buf[pos] = b.buf[i];
                i++;
                pos++;
            }

        }

        return new String(buf,"UTF-8");
    }

    class ConnectionUtilsBuf
    {

        public byte buf[];
        public int size;

        public ConnectionUtilsBuf(byte b[], int s)
        {
            buf = b;
            size = s;
        }
    }

    public  String getParamsString(Map<String, String> params)
                throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();

            for (Map.Entry<String, String> entry : params.entrySet()) {
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                result.append("&");
            }

            String resultString = result.toString();
            return resultString.length() > 0
                    ? resultString.substring(0, resultString.length() - 1)
                    : resultString;
        }

    public void setFourStopRequestParameter(MonitoringRequest requestVO ,FourStopRequestVO fourStopRequestVO) throws ParseException {
        logger.info("-----inside setFourStopRequestParameter-----");
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
        String time=targetFormat.format(targetFormat.parse(requestVO.getTime()));

        String amount=requestVO.getAmount();

        amount=String.valueOf(Math.round(Double.parseDouble(amount)));
        fourStopRequestVO.setTrans_id(String.valueOf(requestVO.getFraud_transaction_id()));
        fourStopRequestVO.setCurrency(requestVO.getCurrency());
        fourStopRequestVO.setAmount(amount + "00");
        fourStopRequestVO.setStatus("0");
        fourStopRequestVO.setTime(time);
        fourStopRequestVO.setCustomer_information_first_name(requestVO.getFirstname());
        fourStopRequestVO.setCustomer_information_last_name(requestVO.getLastname());
        fourStopRequestVO.setCustomer_information_email(requestVO.getEmailaddr());
        fourStopRequestVO.setCustomer_information_city(requestVO.getCity());
        fourStopRequestVO.setCustomer_information_country(requestVO.getCountrycode());
        fourStopRequestVO.setCustomer_information_address1(requestVO.getAddress1());
        fourStopRequestVO.setCustomer_information_postal_code(requestVO.getZip());
        fourStopRequestVO.setIp(requestVO.getIpaddrs());

        //Newly added considering sophie recommendation
        fourStopRequestVO.setReg_ip_address(requestVO.getIpaddrs());
        fourStopRequestVO.setCustomer_information_province(requestVO.getState());
        fourStopRequestVO.setCustomer_information_phone1(requestVO.getPhone());
        fourStopRequestVO.setCustomer_information_dob(requestVO.getDob());
        //till:Newly added considering sophie recommendation

        fourStopRequestVO.setBilling_first_name(requestVO.getFirstname());
        fourStopRequestVO.setBilling_last_name(requestVO.getLastname());
        fourStopRequestVO.setBilling_email(requestVO.getEmailaddr());
        fourStopRequestVO.setBilling_city(requestVO.getCity());
        fourStopRequestVO.setBilling_country(requestVO.getCountrycode());
        fourStopRequestVO.setBilling_address1(requestVO.getAddress1());
        fourStopRequestVO.setBilling_postal_code(requestVO.getZip());
        fourStopRequestVO.setBilling_province(requestVO.getState());
        fourStopRequestVO.setBilling_phone1(requestVO.getPhone());

        fourStopRequestVO.setPayment_method_bin(requestVO.getFirstsix());
        fourStopRequestVO.setPayment_method_last_digits(requestVO.getLastfour());
        fourStopRequestVO.setDeposit_limits_dl_min(String.valueOf(requestVO.getDailycardminlimit()));
        fourStopRequestVO.setDeposit_limits_dl_daily(String.valueOf(requestVO.getDailycardlimit()));
        fourStopRequestVO.setDeposit_limits_dl_monthly(String.valueOf(requestVO.getMonthlycardlimit()));
        fourStopRequestVO.setDeposit_limits_dl_weekly(String.valueOf(requestVO.getWeeklycardlimit()));
        fourStopRequestVO.setDeposit_limits_pay_method_type(requestVO.getPaymenttype());

        fourStopRequestVO.setInternal_trans_id(requestVO.getResponseId());
        fourStopRequestVO.setReason(requestVO.getReason());
        fourStopRequestVO.setMemberId(String.valueOf(requestVO.getMemberid()));
    }

    public String joinMapValue(Map<String, String> map, char connector)
    {
        StringBuffer b = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            b.append(entry.getKey());
            b.append('=');
            if (entry.getValue() != null)
            {
                b.append(entry.getValue());
            }
            b.append(connector);
        }
        return b.toString();
    }

}
