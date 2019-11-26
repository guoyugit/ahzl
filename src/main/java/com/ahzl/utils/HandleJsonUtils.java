package com.ahzl.utils;

import net.sf.json.JSONObject;

public final class HandleJsonUtils {

    public static String handleStr(JSONObject obj, String rex) {
        String valueStr = "";
        if (obj.toString().indexOf(rex) >= 0) {
            Object o = obj.get(rex);
            if(null != o) valueStr = o.toString();
        }
        return valueStr;
    }
}
