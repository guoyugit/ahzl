package com.ahzl.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>
 * <p>Title:CommonUtils.java</p >
 * <p>Description: </p >
 * <p>Copyright: 公共服务与应急管理战略业务本部 Copyright(c)2019</p >
 * <p>Date:2019/11/26 19:03</p >
 *
 * @author guoyu (guoyu@mail.taiji.com.cn)
 * @version 1.0
 */
public final class CommonUtils {

    public static final String formatDateTimeStr = "yyyy-MM-dd HH:mm:ss";
    public static final String formatDateStr = "yyyyMMddHH";

    public static String locateToStr(LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatDateTimeStr);
        return dateTimeFormatter.format(localDateTime);
    }

    public static LocalDateTime StrToLocate(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatDateTimeStr);
        return LocalDateTime.parse(dateStr, dateTimeFormatter);
    }

    public static String getInsId() {
        SimpleDateFormat tempDate = new SimpleDateFormat(formatDateStr);
        return tempDate.format(new Date(System.currentTimeMillis())) + System.nanoTime();
    }

    public static MultiValueMap<String, Object> convertMap2MultiValueMap(Map<String, Object> sourceM) {
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        for (String key : sourceM.keySet()) {
            Object obj = sourceM.get(key);
            if (obj instanceof List) {
                for (Object o : (List) obj) {
                    multiValueMap.add(key, o);
                }
            } else {
                multiValueMap.add(key, sourceM.get(key));
            }
        }
        return multiValueMap;
    }

    public static void main(String[] args) {
        HashSet set = new HashSet<String>(10000);
        Instant now = Instant.now();
        for (int i = 0; i < 10000000; i++) {
            String result = getInsId();
//            System.out.println(result);
            set.add(result);
        }
        long used = ChronoUnit.MILLIS.between(now, Instant.now());
        System.out.println("used " + used / 1000 + " s");
        System.out.println(set.size());
    }
}
