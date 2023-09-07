package 发送https请求.RSA签名;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HttpsRequestTest {
    public static void main(String[] args) {
        HttpsRequestTest.purchaseApi();
    }

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
     * //  购买
     */
    public static void purchaseApi(){
        HttpsRequest pos = new HttpsRequest();
        //iteams订单
        List<JSONObject> it=new ArrayList<>();
        JSONObject json=new JSONObject();
        json.put("item_code","80844718112"); //描述
        json.put("item_desc","哦"); //描述
        json.put("category","1");  //大类
        json.put("item_qty","1");   //数量
        json.put("item_price","1"); //单价
        json.put("sales_price","1"); //总价
        json.put("type","0"); //销售类型0-购买, 1-退货
//        json.put("return_store_sn","Efapiaotest002");//原门店号
//        json.put("return_workstation_sn","0");//原收银机号
//        json.put("return_check_sn","2951104449094091");//原单号
        it.add(json);

//        JSONObject json1=new JSONObject();
//        json1.put("item_code","808447181121"); //描述
//        json1.put("item_desc","哦1"); //描述
//        json1.put("category","1");  //大类
//        json1.put("item_qty","1");   //数量
//        json1.put("item_price","1"); //单价
//        json1.put("sales_price","1"); //总价
//        json1.put("type","0"); //销售类型0-购买, 1-退货
////        json.put("return_store_sn","Efapiaotest002");//原门店号
////        json.put("return_workstation_sn","0");//原收银机号
////        json.put("return_check_sn","2951104449094091");//原单号
//        it.add(json1);

        String result = pos.purchase("teststore", "0","", "2","1", "156", "156111",
                "testcommodity", "", "张三", "zs", "1", "ice", "1",
                "1", "", "3", "301", "","1","q","", "",it

        );
        System.out.println(result);
    }

    /**
     * ipay
     */
    public void payApi(){
        HttpsRequest pos= new HttpsRequest();
        String pay = pos.pay("0", GetClient_Sn(16), "", "", "", "1440", "",
                "1", "", "", "156", "subject", "zs", "customer", "", "https://www.baidu.com/",
                "3", "" , "", "reflect", "", "3", "302", "");
        System.out.println(pay);
    }
}
