package com.ahzl.utils;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class QueryUtil {
    //查询工具类
    public  String  queryData(String apiName,String httpUrl,Map<String,Object> param) {
        Map<String, Object> jsonObject = new HashMap<>();
        //devId:开发者id 每个部门都分配开发者id（唯一）（必填）
        jsonObject.put("devId", "0347b4c7784540d4b6369ca2dc471779");
        //apiName:调用的api的名称（唯一）（必填）
        jsonObject.put("apiName", apiName);//在资源管理-服务管理-服务申请-公共参数-apiName中查看
        //根据部门api设置请求参数（部门api参数）
        //jsonObject.put("***", "***");
        //添加时间戳配置
        jsonObject.put("timestamp", System.currentTimeMillis());
        if(param.size()>0 && jsonObject.size()>0){
            Set set=param.keySet();
            Iterator iterator=set.iterator();
            while (iterator.hasNext()){
                String key=(String) iterator.next();
                jsonObject.put(key,(String)param.get(key));
            }
        }
//        jsonObject.put("areaCode", "120110");
        //调用CommonUtil.signatureWithParamsOnly方法生成签名;工具类在资料中心下载《市级接口调用资料》即可获得
        //appSecretKey：每个部门都分配密钥
        String sign = CommonUtil.signatureWithParamsOnly(jsonObject, "fa130ecd227e4109b3ed854350d85021");
               jsonObject.put("sign", sign);

        //拼接url url为请求地址；在资源管理-服务管理-服务申请-API详情-请求地址中查看
        String url = CommonUtil.getWholeUrl(httpUrl, jsonObject);

//        if(param.size()>0 && jsonObject.size()>0){
//            Set set=param.keySet();
//            Iterator iterator=set.iterator();
//            while (iterator.hasNext()){
//                String key=(String) iterator.next();
//                url=url+"&"+key+"="+param.get(key).toString();
//            }
//        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                //解析返回的值
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }
}
