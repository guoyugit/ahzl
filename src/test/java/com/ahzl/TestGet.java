package com.ahzl;

import com.ahzl.utils.CommonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author liutao
 * @date 2018/04/20
 */
public class TestGet {
    public static void main(String[] args) {
        Map<String, Object> jsonObject = new HashedMap();
        //devId:开发者id 每个部门都分配开发者id（唯一）（必填）
        jsonObject.put("devId", "0347b4c7784540d4b6369ca2dc471779");
        //apiName:调用的api的名称（唯一）（必填）
        jsonObject.put("timestamp", System.currentTimeMillis());
        jsonObject.put("apiName", "wxb.buildings");
        //根据部门api设置不同的参数（部门api参数）
        jsonObject.put("areaCode", "120110");
        String sign = CommonUtil.signatureWithParamsOnly(jsonObject, "fa130ecd227e4109b3ed854350d85021");
        jsonObject.put("sign", sign);
        //拼接url
        String url = CommonUtil.getWholeUrl("http://10.96.8.101:8200/gateway/api/v1/grid", jsonObject);
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
    }
}
