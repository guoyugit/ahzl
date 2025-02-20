package com.ahzl.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author lilin
 */
@Slf4j
public class SignTools {

	/**
	 * 签名生成算法
	 * @param  params 请求参数集，所有参数必须已转换为字符串类型
	 * @param  privateKey 签名密钥
	 * @return 签名
	 * @throws IOException
	 */
	public static String getSignature(HashMap<String,String> params,String privateKey)
	{
	    // 先将参数以其参数名的字典序升序进行排序
	    Map<String, String> sortedParams = new TreeMap<String,String>(params);
	    Set<Entry<String, String>> entrys = sortedParams.entrySet();

	    // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, String> param : entrys) {
	        basestring.append(param.getKey())
	        .append("=")
	        .append(param.getValue())
			.append("&");
	    }
	    basestring.append(privateKey );

		System.out.println("this str is :" + basestring);

		// 使用MD5对待签名串求签
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }

	    // 将MD5输出的二进制结果转换为小写的十六进制
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex);
	    }
	    return sign.toString().toLowerCase();
	}



	public static void main(String[] args) {
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("account", "aaa");
			map.put("aac", "dasd");
			map.put("base", "adas");
			map.put("baseaac", "dasd");
			map.put("telCode", "wew");
			String privateKey = "we3";
			String sign = getSignature(map, privateKey);
			System.out.println(sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
