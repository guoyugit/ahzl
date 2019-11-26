//package com.ahzl.data;
//
//import com.alibaba.fastjson.JSONObject;
//import com.ahzl.data.utils.CommonUtil;
//import org.apache.commons.collections.map.HashedMap;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * @author liutao
// * @date 2018/04/28
// */
//public class TestPost {
//    public static void main(String[] args) {
//        Map<String, Object> jsonObject = new HashedMap();
//        //devId:开发者id 每个部门都分配开发者id（唯一）（必填）
//        jsonObject.put("devId", "106ca9decdb243e79d4ae3beb516368a");
//        //apiName:调用的api的名称（唯一）（必填）
//        jsonObject.put("apiName", "gaj.checkIdcardBaseFace");
//        //根据部门api设置不同的参数（部门api参数）
//        jsonObject.put("idNumber", "37050219820307641X");
//        jsonObject.put("name", "尚辉");
//        jsonObject.put("photo", "00000000");
//        String sign = CommonUtil.signatureWithParamsOnly(jsonObject, "dc1b4832affc493195e7c336aed24fca");
//        jsonObject.put("sign", sign);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("http://localhost:8080/api-monitor/api/deptapi");
//        System.out.println(JSONObject.toJSONString(jsonObject));
//        StringEntity entity = new StringEntity(JSONObject.toJSONString(jsonObject), "UTF-8");
//        // 发送Json格式的数据请求
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//        String result = "";
//        try {
//            HttpResponse response = httpClient.execute(httpPost);
//            // 检验返回码
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                ////解析返回的值
//                result = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(result);
//    }
//}
