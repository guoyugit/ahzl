package com.ahzl.utils;

import net.sf.json.JSONObject;

import java.util.Iterator;

public final class JsonToStringUtil {
    //将JSONObject 的属性值全部转换为String
    public static JSONObject jsonToString(JSONObject jsonObject) {
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            Object value=jsonObject.get(key);
            jsonObject.put(key,(value == null)?"": value.toString());
        }
        return jsonObject;
    }
}
