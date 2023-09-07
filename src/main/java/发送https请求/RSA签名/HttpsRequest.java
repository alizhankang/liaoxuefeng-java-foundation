package 发送https请求.RSA签名;

//import com.sun.org.slf4j.internal.Logger;
//import com.sun.org.slf4j.internal.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


//import cn.hutool.core.collection.CollUtil;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONException;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.parser.Feature;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpException;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.TrustStrategy;
//import org.apache.http.util.EntityUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//import javax.net.ssl.SSLContext;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicReference;
public class HttpsRequest {
    private static Logger log = LoggerFactory.getLogger(HttpsRequest.class);
    private String api_domain = "https://vapi.shouqianba.com/";
    private String appid = "28lpm0000002";
    private String store_sn = "DKHTEST";
    private String brand_code = "999888";
    private String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe/j8SCVJgkuq3VF20TFCiuU1z1rd+VXlfF0dkaBKxykl+MxaVQsdGQeldPMWDt7GTEAaTgcbAaaxT1S1sUStz9TNl02jgutiyZ+55iEr464l4ZTVVBQk0KlDfqAb9Si6oWw2Env20bEjtgZWpgiybW6vY64SItLtj7G5etCFJ4M4qGOANpx9UEN2fQuDFTSPbGQKi0F983eaTBdjrzREr5PWz4L4M7jU6SQSgEse9mihh9vwWt+d9Cffu+onUxPAvW14Je6kW3WA7S0vyyTsAUceysgqvOKZQhoEs+Leg9DRD7UGwinuTc3A9qSYIGAb8V2nb0r0P5XsgmUTEmVZnAgMBAAECggEAf2T45FrTxs3xhDP1YSJE+h3AEbFaFcAnICpm6ez6DbsoaBZHYhG/2mu1sR+go4nsnwmYO1khB8ukaQfG/aOMTb2LEWvz/R25xDnCu16ZtBoGbJGryhPe7A17/7mC4DEwcXWDv2AlqQua47ORV+EWHOW3LHiKGX+3JrmrNfbfNXHFwdQ7egHImCbaeVgfUBY3gUPB0Ynyf3e2ufr3QvV5Q57NUCx5YvYiP7zjj5/uFk76cGDC9CkH0LtZ2uYMyMtx5iITmQhO29F7IY7EmOwETYS9aW7sd6RIMU6PJ/QcUez4iTILqNqF6ejZx2aWSH8z+fgFSVbn6Ao5dULrCphlAQKBgQDldzCi7lrsOT4w/g3xv7sM+fej8e4qSQzr+5Gv2DoGR/GqvrDd/BiR/VtYtSDpJe3SPOEYExRyeeWlWhuaa2AiC49ULwVJWlJqnfTiSL4gqUwFpfjFMP6+5KHfRaMYxibNSB9FfOBAgXcF+D4IITEVsQc1TCTmKB5ZGpjecRh6hQKBgQCxYOETZnK4NFUAZu1dRoTKyrBoui+4qqPxJSx4pdezt/8RAgJuk6UYcfkzHGFsIl+oXH1hzmIcltxalc6sag4/75hGt9mNlMKySORYT3sgTK6nxa62aCXvvYcHWfr2V7cK/XgVB/RCiWlxXjiVU4f0SqF8eMPoYf7Rh1hiNEk++wKBgQC9hcks+TMtokXkjyETR6mFmTvZM+vjDvzWN6znkO6z214WCXPplNryUVDOHqP1DTe1CkVb7f5YYqey/46G5yK6W9Pg0wlJwYkKuDXXY/9s2IeKrr+els4A+rNbxpdj0d2gdW4mpXJOtN+KlbMeYdO5t8JdWeusEPyn2ZjjOIPgRQKBgGIH5NzP9f8QDRpXyD+Qxbs+Ihj/LXil9k1D+jwDjB7rRbCkp6ttNgU4mD1DJiSZKrzlwPXZFiguyEHYIYzwYEe9py8OVNIGsUPPPUQBSU8kkjJu8owlKzJAUOwjMqwK9kLAqykUaE6NmxToueTtcWn2BSHBrKQ15Jrwbkx4ETMZAoGAM+/clMBth6NclFf5LIGc8JXd47gI2Mbgm8a07I9aVw7FrpOPyyJ9YZwH3QXYoQ9Mn3alcoeU8BVs9FW2+8M3DJ0busvkxmF9AAt+5qFsFj3mi2HW5T2VWv+0ElFT6Uer7k20WmL4bFHc1dqM0v5z4/U0pvIpCBT9lv004yJbIbw=";
    private String publicKey = "";
    private final static String CHARSET_UTF8 = "utf8";

    /**
     * 随机数
     *
     * @param codeLenth
     * @return
     */
    public String GetClient_Sn(int codeLenth) {
        while (true) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < codeLenth; i++) {
                if (i == 0)
                    sb.append(new Random().nextInt(9) + 1); // first field will not start with 0.
                else
                    sb.append(new Random().nextInt(10));
            }
            return sb.toString();
        }
    }

    /**
     * 获取当前时间
     * @return
     */
    public String Date() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String sales_time1 = formatter.format(date);
        return sales_time1;
    }

    /**
     * 同步反参验签 （轻POS、电子发票）
     */
    public boolean YanQianBoolean(String result, String publicKey) {
        JSONObject jsonObject = JSON.parseObject(result, Feature.OrderedField);
        String response =
                JSON.toJSONString(jsonObject.get("response"), SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames);
        String signature = JSON.toJSONString(jsonObject.get("signature"), SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames);
        System.out.println("response:" + response);
        System.out.println("signature:" + signature);
        boolean YQresult = RSASignature.doCheck(response.toString(), signature.toString(), publicKey);
        System.out.println("YQresult:" + YQresult);//返回
        return YQresult;
    }

    /**
     * 回调验签 （轻POS、电子发票）
     */
    public boolean YanQianBoolean2(String result, String publicKey) {
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(result, Feature.OrderedField);
        String response =
                JSON.toJSONString(jsonObject.get("request"), SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames);
        String signature = JSON.toJSONString(jsonObject.get("signature"), SerializerFeature.WRITE_MAP_NULL_FEATURES, SerializerFeature.QuoteFieldNames);
        System.out.println("request:" + response);
        System.out.println("signature:" + signature);
        boolean YQresult = RSASignature.doCheck(response.toString(), signature.toString(), publicKey);
        System.out.println("YQresult2:" + YQresult);
        return YQresult;
    }


    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain,
                                         String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            log.error("", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("", e);
        } catch (KeyStoreException e) {
            log.error("", e);
        }
        return HttpClients.createDefault();
    }


    /**
     * http POST 请求
     *
     * @param url:请求地址
     * @param body:    body实体字符串
     * @param
     * @return
     */
    public static String httpPostPos(String url, String body) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        String xmlRes = "{}";
        HttpClient client = createSSLClientDefault();
        HttpPost httpost = new HttpPost(url);
        try {
            log.debug("Request string: " + body);

            //所有请求的body都需采用UTF-8编码
            StringEntity entity = new StringEntity(body, "UTF-8");
            entity.setContentType("application/json");
            httpost.setEntity(entity);

            //支付平台所有的API仅支持JSON格式的请求调用，HTTP请求头Content-Type设为application/json
            // httpost.addHeader("Content-Type","multipart/form-data");
            httpost.addHeader("Content-Type", "application/json");
            httpost.addHeader("Accept-Encoding", "zip");

            HttpResponse response = client.execute(httpost);
            //所有响应也采用UTF-8编码

            //xmlRes = EntityUtils.toString(response.getEntity(), "UTF-8");
            xmlRes = EntityUtils.toString(response.getEntity(), "UTF-8");

            log.debug("Response string: " + xmlRes);
        } catch (ClientProtocolException e) {
            log.error("", e);
        } catch (IOException e) {
            log.error("", e);
        }
        return xmlRes;
    }
    /**
     * http get 请求
     *
     * @param  url:请求地址
     * @param  parameter:body实体字符串
     * @param
     * @return
     */
//    public static String httpGet(String url, String charset) throws HttpException, IOException {
//
//        String json = null;
//        HttpGet httpGet = new HttpGet();
//        HttpClient client = createSSLClientDefault();
//// 设置参数
//        try {
//            httpGet.setURI(new URI(url));
//        } catch (URISyntaxException e) {
//            throw new HttpException("请求url格式错误。"+e.getMessage());
//        }
//
//// 发送请求
//        HttpResponse httpResponse = client.execute(httpGet);
//// 获取返回的数据
//        HttpEntity entity = httpResponse.getEntity();
//        byte[] body = EntityUtils.toByteArray(entity);
//        StatusLine sL = httpResponse.getStatusLine();
//        int statusCode = sL.getStatusCode();
//        if (statusCode == 200) {
//            json = new String(body, charset);
//            entity.consumeContent();
//        } else {
//            throw new HttpException("statusCode="+statusCode);
//        }
//
//        return json;
//
//    }
    public static String doGet(String url,String parameter)
    {
        String uriAPI =url + parameter ; //"http://XXXXX?str=I+am+get+String";
        String result= "";
        HttpClient client = createSSLClientDefault();
        HttpGet httpRequst = new HttpGet(uriAPI);
        try {

            HttpResponse httpResponse = client.execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            }
            else
                httpRequst.abort();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }


    /**
     * @param store_name     商户门店名称
     * @param workstation_sn 门店收银编号 如果没有传入0
     * @param check_sn       商户订单号 在商户系统中唯一
     * @param amount         订单价格 精确到分
     * @param currency       币种 如“156”for CNY
     * @param subject        订单主题
     * @param description    订单描述
     * @param operator       操作员
     * @param customer       客户信息
     * @param industry_code  行业代码 0:零售 1:酒店 2:餐饮 4:教育
     * @param pos_info       本接口对接的对端信息
     * @param resolution     是否支持拆单 1:不支持 2:支持
     * @param request_id
     * @return
     */
    public String purchase(String store_name, String workstation_sn,
                      String check_sn, String expire_time, String amount, String currency, String sales_sn,
                      String subject, String description, String operator, String customer, String industry_code,
                      String pos_info, String resolution, String request_id, String reflect,
                      String tender_type, String sub_tender_type, String dynamic_id, String Tenderamount, String transaction_sn, String original_tender_sn, String pay_status, List<JSONObject> items) throws JSONException {
        String url = api_domain + "api/lite-pos/v1/sales/purchase";     //购买


        //item
        JSONObject itemsJson = new JSONObject();
        List<JSONObject> item = new ArrayList<>();

        //tenders
        JSONObject tendersJson = new JSONObject();
        List<JSONObject> tenders = new ArrayList<>();

        try {
            //head
            JSONObject headJson = new JSONObject();
            headJson.put("version", "1.0.0");
            headJson.put("appid", appid);
            headJson.put("request_time",Date());
            headJson.put("reserve", "{}");
            headJson.put( "sign_type","SHA1");
            // headJson.put( "sign_type","SHA256");

            //body
            JSONObject bodyJson = new JSONObject(
            );
            bodyJson.put("brand_code", brand_code);
            bodyJson.put("store_sn", store_sn);
            bodyJson.put("store_name", store_name);
            bodyJson.put("scene", "2");//0-无场景，1-智能终端，2-H5，4-PC，5-微信小程序/插件，7-刷脸终端，8-立即付，10-APP支付，11-顾客扫码支付，12-顾客出码
            bodyJson.put("workstation_sn", "0");
            bodyJson.put("check_sn", GetClient_Sn(16));
            bodyJson.put("sales_time", Date());
            //bodyJson.put("sales_time",  "2022-35-09T02:35:27+08:00");
            bodyJson.put("amount", amount);
            bodyJson.put("currency", currency);
            bodyJson.put("subject", subject);
            bodyJson.put("description", description);
            bodyJson.put("operator", operator);
            bodyJson.put("customer", customer);
            bodyJson.put("industry_code", industry_code);
            bodyJson.put("pos_info", pos_info);
            bodyJson.put("dynamic_id", dynamic_id);
            bodyJson.put("resolution", resolution);
            // bodyJson.put("enable_sub_tender_types", "301");
            bodyJson.put("notify_url", "https://hooks.upyun.com/Gigfz0ZYR");
            bodyJson.put("request_id", GetClient_Sn(10));
            // bodyJson.put("reflect", reflect);
            //bodyJson.put("disable_sub_tender_types", "301");
            bodyJson.put("return_url", "https://www.baidu.com/");
            bodyJson.put("expire_time", "1440");
            //bodyJson.put("expire_time", "1");
            bodyJson.put("items",items);



            // tenders
            tendersJson.put("tender_type", tender_type);
            tendersJson.put("sub_tender_type", sub_tender_type);
            tendersJson.put("amount", Tenderamount);
            tendersJson.put("transaction_sn", GetClient_Sn(16));
            tenders.add(tendersJson);
            // bodyJson.put("tenders", tenders);

            //request
            JSONObject requestJson = new JSONObject();
            requestJson.put("head", headJson);
            requestJson.put("body", bodyJson);

            //全部
            JSONObject request = new JSONObject();
            request.put("request", requestJson);
            System.out.println("request" + requestJson.toString());
            String sign = RSASignature.sign(requestJson.toString(), privateKey, "UTF-8");//使用私钥签名，并转成BASE64编码
            System.out.println(sign);
            request.put("signature", sign);
            System.out.println("request"+request);

            String result = httpPostPos(url, request.toString()); //请求
            System.out.println("result：" + result);
            /*boolean  a = YanQianBoolean(result,publicKey);
            if(a == true){
                return  result;
            }*/
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 立即付
     */
    public String pay(String workstation_sn, String check_sn, String order_sn, String sales_sn, String sales_time, String expire_time
            , String expired_at, String amount, String item_amount, String freight, String currency, String subject
            , String operator, String customer, String notify_url, String return_url, String scene_type, String dynamic_id
            , String appid1, String reflect, String payer_uid, String tender_type, String sub_tender_type, String installment_number){
        String url = api_domain+"api/lite-pos/v1/sales/ipay";

        //item
        JSONObject itemsJson = new JSONObject();
        List<JSONObject> items = new ArrayList<>();

        //tenders
        JSONObject tendersJson = new JSONObject();
        List<JSONObject> tenders = new ArrayList<>();

        try {
            //head
            JSONObject headJson = new JSONObject();
            headJson.put("version", "1.0.0");
            headJson.put("appid",appid);
            headJson.put("request_time", Date());
            headJson.put("reserve", "{}");
            headJson.put( "sign_type","SHA1");

            //body
            JSONObject bodyJson = new JSONObject();
            bodyJson.put("brand_code", brand_code);
            bodyJson.put("store_sn", store_sn);
            bodyJson.put("scene_type", scene_type);//支付方式  1-付款码支付、协议支付2–wap 支付3-小程序支付5-原生APP支付6-原生H5支付不同 scene_t
            bodyJson.put("workstation_sn", workstation_sn);//门店收银编号
            bodyJson.put("check_sn", check_sn);//商户订单号
            bodyJson.put("order_sn", order_sn);//商户订单号
            bodyJson.put("sales_time", Date());//订单创建时间
            bodyJson.put("amount", amount);//订单总金额
            bodyJson.put("currency", currency);//币种
            bodyJson.put("subject", subject);//订单简短描述
            //bodyJson.put("description", description);//订单描述
            bodyJson.put("operator", operator);//操作员
            bodyJson.put("customer", customer);//备注
            bodyJson.put("dynamic_id", dynamic_id);//scene_type=1 时必填，支付凭证
            bodyJson.put("appid", appid1);//scene_type=3 小程序支付时必传，小程序appid
            bodyJson.put("reflect", reflect);//scene_type=3 小程序支付时必传，小程序appid
            bodyJson.put("payer_uid", payer_uid);//消费者在支付通道的唯一 id，小程序支付必传，微信小程序支付必须传 open_id，支付宝小程序支付必传用户授权的 userId
            bodyJson.put("request_id", GetClient_Sn(10));
            bodyJson.put("tender_type", tender_type);//一级支付方式类型
            bodyJson.put("sub_tender_type", sub_tender_type);//二级支付方式类型
            bodyJson.put("expire_time", expire_time);//订单有效期
            //bodyJson.put("notify_url", "0");
            bodyJson.put("return_url",return_url);


//            //tenders
//            tendersJson.put("tender_type", tender_type);
//            tendersJson.put("sub_tender_type", sub_tender_type);
//            tendersJson.put("amount", Tenderamount);
//            tendersJson.put("transaction_sn", GetClient_Sn(16));
//            tenders.add(tendersJson);
//            bodyJson.put("tenders", tenders);
            //request
            JSONObject requestJson = new JSONObject();
            requestJson.put("head", headJson);
            requestJson.put("body", bodyJson);

            //全部
            JSONObject request = new JSONObject();
            request.put("request", requestJson);
            System.out.println("request" + requestJson.toString());
            String sign = RSASignature.sign(requestJson.toString(), privateKey, "UTF-8");//使用私钥签名，并转成BASE64编码
            System.out.println(sign);
            request.put("signature", sign);

            String result = httpPostPos(url, request.toString()); //请求
            System.out.println("result：" + result);
            /*boolean  a = YanQianBoolean(result,publicKey);
            if(a == true){
                return  result;
            }*/
            return result;
        } catch (Exception e) {
            return null;
        }

    }


}
