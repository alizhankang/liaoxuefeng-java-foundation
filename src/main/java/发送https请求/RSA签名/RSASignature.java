package 发送https请求.RSA签名;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

//import static org.example.HttpProxyLitePOS.httpPostPos;

/**
 * @author ssddp
 * @ClassNameRSASignature
 * @Description: RSA签名验签类
 * @date 2019/7/18 12:03
 * @Version 1.0
 **/
public class RSASignature {
    /**
     * 签名算法
     */
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    //public static final String SIGN_ALGORITHMS = "SHA256withRSA";

    //编码
    private static final String CHARSET_UTF_8 = "utf-8";

    /**
     * RSA签名
     *
     * @param content
     *            待签名数据
     * @param privateKey
     *            商户私钥
     * @param encode
     *            字符集编码
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String encode) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKey));

            KeyFactory keyf = KeyFactory.getInstance("RSA");

            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(encode));

            byte[] signed = signature.sign();

            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    Base64.decodeBase64(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);
            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes());
            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param publicKey
     *            分配给开发商公钥
     * @param encode
     *            字符集编码
     * @return 布尔值
     */
    public static boolean doCheck(String content, String sign,
                                  String publicKey, String encode) {
        try {

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            PublicKey pubKey = keyFactory
                    .generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(encode));

            boolean bverify = signature.verify(Base64.decodeBase64(sign));
            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {

            System.out.println("publicKey:"+publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("SHA256WithRSA");

            byte[] encodedKey = Base64.decodeBase64(publicKey);

            PublicKey pubKey = keyFactory
                    .generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes());

            boolean bverify = signature.verify(Base64.decodeBase64(sign));

            return bverify;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main(String[] args) {
        String a=" {\n" +
                "\t\t\"head\": {\n" +
                "\t\t\t\"request_time\": \"2023-01-06T16:36:38+08:00\",\n" +
                "\t\t\t\"appid\": \"28lpm0000002\",\n" +
                "\t\t\t\"reserve\": \"{}\",\n" +
                "\t\t\t\"version\": \"1.0.0\",\n" +
                "\t\t\t\"sign_type\": \"SHA1\"\n" +
                "\t\t},\n" +
                "\t\t\"body\": {\n" +
                "\t\t\t\"store_sn\": \"DKHTEST\",\n" +
                "\t\t\t\"brand_code\": \"999888\",\n" +
                "\t\t\t\"amount\": \"2\",\n" +
                "\t\t\t\"subject\": \"testcommodity\",\n" +
                "\t\t\t\"industry_code\": \"1\",\n" +
                "\t\t\t\"dynamic_id\": \"\",\n" +
                "\t\t\t\"check_sn\": \"2641840488526118\",\n" +
                "\t\t\t\"description\": \"\",\n" +
                "\t\t\t\"expire_time\": \"3600\",\n" +
                "\t\t\t\"workstation_sn\": \"0\",\n" +
                "\t\t\t\"notify_url\": \"https://hooks.upyun.com/Gq5kaBfxB\",\n" +
                "\t\t\t\"resolution\": \"1\",\n" +
                "\t\t\t\"sales_time\": \"2023-01-06T16:36:38+08:00\",\n" +
                "\t\t\t\"operator\": \"张三\",\n" +
                "\t\t\t\"scene\": \"2\",\n" +
                "\t\t\t\"store_name\": \"teststore\",\n" +
                "\t\t\t\"pos_info\": \"ice\",\n" +
                "\t\t\t\"currency\": \"156\",\n" +
                "\t\t\t\"request_id\": \"8631131375\",\n" +
                "\t\t\t\"items\": [{\n" +
                "\t\t\t\t\"item_code\": \"80844718112\",\n" +
                "\t\t\t\t\"item_desc\": \"哦\",\n" +
                "\t\t\t\t\"item_price\": \"2\",\n" +
                "\t\t\t\t\"sales_price\": \"2\",\n" +
                "\t\t\t\t\"category\": \"1\",\n" +
                "\t\t\t\t\"type\": \"0\",\n" +
                "\t\t\t\t\"item_qty\": \"1\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"customer\": \"zs\"\n" +
                "\t\t}\n" +
                "\t}";
        String b="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCe/j8SCVJgkuq3VF20TFCiuU1z1rd+VXlfF0dkaBKxykl+MxaVQsdGQeldPMWDt7GTEAaTgcbAaaxT1S1sUStz9TNl02jgutiyZ+55iEr464l4ZTVVBQk0KlDfqAb9Si6oWw2Env20bEjtgZWpgiybW6vY64SItLtj7G5etCFJ4M4qGOANpx9UEN2fQuDFTSPbGQKi0F983eaTBdjrzREr5PWz4L4M7jU6SQSgEse9mihh9vwWt+d9Cffu+onUxPAvW14Je6kW3WA7S0vyyTsAUceysgqvOKZQhoEs+Leg9DRD7UGwinuTc3A9qSYIGAb8V2nb0r0P5XsgmUTEmVZnAgMBAAECggEAf2T45FrTxs3xhDP1YSJE+h3AEbFaFcAnICpm6ez6DbsoaBZHYhG/2mu1sR+go4nsnwmYO1khB8ukaQfG/aOMTb2LEWvz/R25xDnCu16ZtBoGbJGryhPe7A17/7mC4DEwcXWDv2AlqQua47ORV+EWHOW3LHiKGX+3JrmrNfbfNXHFwdQ7egHImCbaeVgfUBY3gUPB0Ynyf3e2ufr3QvV5Q57NUCx5YvYiP7zjj5/uFk76cGDC9CkH0LtZ2uYMyMtx5iITmQhO29F7IY7EmOwETYS9aW7sd6RIMU6PJ/QcUez4iTILqNqF6ejZx2aWSH8z+fgFSVbn6Ao5dULrCphlAQKBgQDldzCi7lrsOT4w/g3xv7sM+fej8e4qSQzr+5Gv2DoGR/GqvrDd/BiR/VtYtSDpJe3SPOEYExRyeeWlWhuaa2AiC49ULwVJWlJqnfTiSL4gqUwFpfjFMP6+5KHfRaMYxibNSB9FfOBAgXcF+D4IITEVsQc1TCTmKB5ZGpjecRh6hQKBgQCxYOETZnK4NFUAZu1dRoTKyrBoui+4qqPxJSx4pdezt/8RAgJuk6UYcfkzHGFsIl+oXH1hzmIcltxalc6sag4/75hGt9mNlMKySORYT3sgTK6nxa62aCXvvYcHWfr2V7cK/XgVB/RCiWlxXjiVU4f0SqF8eMPoYf7Rh1hiNEk++wKBgQC9hcks+TMtokXkjyETR6mFmTvZM+vjDvzWN6znkO6z214WCXPplNryUVDOHqP1DTe1CkVb7f5YYqey/46G5yK6W9Pg0wlJwYkKuDXXY/9s2IeKrr+els4A+rNbxpdj0d2gdW4mpXJOtN+KlbMeYdO5t8JdWeusEPyn2ZjjOIPgRQKBgGIH5NzP9f8QDRpXyD+Qxbs+Ihj/LXil9k1D+jwDjB7rRbCkp6ttNgU4mD1DJiSZKrzlwPXZFiguyEHYIYzwYEe9py8OVNIGsUPPPUQBSU8kkjJu8owlKzJAUOwjMqwK9kLAqykUaE6NmxToueTtcWn2BSHBrKQ15Jrwbkx4ETMZAoGAM+/clMBth6NclFf5LIGc8JXd47gI2Mbgm8a07I9aVw7FrpOPyyJ9YZwH3QXYoQ9Mn3alcoeU8BVs9FW2+8M3DJ0busvkxmF9AAt+5qFsFj3mi2HW5T2VWv+0ElFT6Uer7k20WmL4bFHc1dqM0v5z4/U0pvIpCBT9lv004yJbIbw=";
        String c="UTF-8";
        String api_domain = "https://vip-apigateway.iwosai.com";
        String url = api_domain + "/invoice/blue";
        String sign = sign(a, b, c);
        System.out.println(sign);
        //System.out.println(System.currentTimeMillis());
//
//        String d="{\n" +
//                "\t\"head\": {\n" +
//                "\t\t\"version\": \"1.0.0\",\n" +
//                "\t\t\"appid\": \"28dp02175996\",\n" +
//                "\t\t\"request_time\": \"2022-11-15T16:26:49+08:00\",\n" +
//                "\t\t\"reserve\": \"{}\",\n" +
//                "\t\t\"sign_type\": \"SHA256\"\n" +
//                "\t},\n" +
//                "\t\"body\": {\n" +
//                "\t\t\"request_id\": \"TN000002926\",\n" +
//                "\t\t\"order_id\": \"06041033\",\n" +
//                "\t\t\"invoice_type_code\": \"3\",\n" +
//                "\t\t\"payee_info\": {\n" +
//                "\t\t\t\"payee_register_no\": \"91440300661005378A\",\n" +
//                "\t\t\t\"checker\": \"陈佳欣\",\n" +
//                "\t\t\t\"receiver\": \"端蓓\",\n" +
//                "\t\t\t\"drawer\": \"端蓓\"\n" +
//                "\t\t},\n" +
//                "\t\t\"payer_info\": {\n" +
//                "\t\t\t\"payer_name\": \"上海技孚信息技术有限公司\",\n" +
//                "\t\t\t\"payer_phone\": \"\",\n" +
//                "\t\t\t\"payer_bank_account\": \"\",\n" +
//                "\t\t\t\"payer_bank_name\": \"\",\n" +
//                "\t\t\t\"payer_address\": \"\",\n" +
//                "\t\t\t\"payer_register_no\": \"133445556666\",\n" +
//                "\t\t\t\"payer_email\": \"yelong@setfu.com\"\n" +
//                "\t\t},\n" +
//                "\t\t\"sum_price_tax\": \"1.000000\",\n" +
//                "\t\t\"sum_tax\": \"0.056604\",\n" +
//                "\t\t\"sum_price\": \"0.943396\",\n" +
//                "\t\t\"items\": [{\n" +
//                "\t\t\t\"item_tax_code\": \"3040304020000000000\",\n" +
//                "\t\t\t\"item_name\": \"制证服务\",\n" +
//                "\t\t\t\"item_sum_price\": \"0.943396\",\n" +
//                "\t\t\t\"item_sum_tax\": \"0.056604\",\n" +
//                "\t\t\t\"item_sum_price_tax\": \"1.000000\",\n" +
//                "\t\t\t\"item_tax_rate\": \"0.06\",\n" +
//                "\t\t\t\"item_quantity\": \"1\",\n" +
//                "\t\t\t\"item_unit_price\": \"0.943396\",\n" +
//                "\t\t\t\"item_unit\": \"\",\n" +
//                "\t\t\t\"item_specification_type\": \"\",\n" +
//                "\t\t\t\"zero_rate_flag\": \"\"\n" +
//                "\t\t}],\n" +
//                "\t\t\"business_type\": \"2\",\n" +
//                "\t\t\"invoice_memo\": \"\",\n" +
//                "\t\t\"request_time\": \"2022-11-15T16:26:49+08:00\",\n" +
//                "\t\t\"callback_url\": \"http://njkg.setfu.com//NormalCustomer/sqbinvoice_callback\"\n" +
//                "\t}\n" +
//                "}";
//        JSONObject request= new JSONObject();
//       String f=sign;
//        request.put("request",d);
//        request.put("signature", f);
//        System.out.println(request);
//
//        try {
//            String result = httpPostPos(url, request.toString()); //请求
//            System.out.println(result);
//        } catch (UnrecoverableKeyException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyStoreException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }

    }


}
