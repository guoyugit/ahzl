package com.ahzl.controller;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.ahzl.utils.jiguang.JiguangUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * <p>Title:TestMsg.java</p >
 * <p>Description: </p >
 * <p>Copyright: 公共服务与应急管理战略业务本部 Copyright(c)2019</p >
 * <p>Date:2020/2/12 13:53</p >
 *
 * @author guoyu (guoyu@mail.taiji.com.cn)
 * @version 1.0
 */
public class TestMsg {

    protected static final Logger LOG = LoggerFactory.getLogger(TestMsg.class);

    //益农e审调用成功
//    protected static final String APP_KEY = "057f04d899e4f4156719f7b4";
//    protected static final String MASTER_SECRET = "aa5ea2831781b15abc5fd6f2";
    //益农E审(开发对接应用)
    protected static final String APP_KEY = "7850a02a6984cfef9ae12261";
    protected static final String MASTER_SECRET = "0b82c0ca251ed033ad9c8438";

    protected static final String GROUP_PUSH_KEY = "2c88a01e073a0fe4fc7b167c";
    protected static final String GROUP_MASTER_SECRET = "b11314807507e2bcfdeebe2e";

    public static final String TITLE = "Test from API example";
    public static final String ALERT = "Test from API Example - alert";
    public static final String MSG_CONTENT = "Test from API Example - msgContent";
    public static final String REGISTRATION_ID = "0900e8d85ef";
    public static final String TAG = "tag_api";
    public static long sendCount = 0;
    private static long sendTotalTime = 0;

    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll(ALERT);
    }


    public static void main(String[] args) {
//        pushToUsers();
//        pushAll();
        pushMsg();
    }

    private static void pushMsg() {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_alias_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    //构建推送对象：所有平台，推送目标是别名为 "wangpeng"，通知内容为 ALERT。(益农e审
    //iOSAndroid)
    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias("wangpeng"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }
    //构建推送对象：所有平台，推送目标是别名为 "wangpeng"，通知内容为 ALERT。(益农E审
    //Android
    public static PushPayload buildPushObject_all_alias_alert1() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.alias("wangpeng"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    private static void pushToUsers() {
        JPushClient jpushClient = null;
        List<String> alias = new ArrayList<>();
        alias.add("wangpeng");
        String content = "测试content";
        String title = "测试title";
        String msg = "测试msg";
        try {
            jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
            PushPayload payload = buildBody(alias, content, title, msg);

            PushResult pushResult = jpushClient.sendPush(payload);
            jpushClient.close();
            if(null != pushResult){
                LOG.error(pushResult.toString());
                Integer statusCode = (Integer) JSONObject.parseObject(pushResult.toString()).get("statusCode");
            }
        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    //app方提供
    private static void pushAll() {
        JiguangUtils.jiguangHeadPushall("测试content","测试title","测试msg");
    }

    private static PushPayload buildBody(List<String> alias, String content, String title, String msg) {
        IosAlert alert = IosAlert.newBuilder().setTitleAndBody(title, null, content).setActionLocKey("PLAY").build();
        LOG.info("生成对象" + new Date());
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).setAlert(content).build())
                        .addPlatformNotification(IosNotification.newBuilder().incrBadge(1).setAlert(alert).build()).build())
                .setMessage(Message.newBuilder().setMsgContent(msg).build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build()).build();
    }
//    public static void main(String[] args) {
//        ClientConfig config = ClientConfig.getInstance();
//        // Setup the custom hostname
//        config.setPushHostName("https://api.jpush.cn");
//
//        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, config);
//
//        // For push, all you need do is to build PushPayload object.
//        PushPayload payload = buildPushObject_all_all_alert();
//
//        try {
//            PushResult result = jpushClient.sendPush(payload);
//            LOG.info("Got result - " + result);
//
//        } catch (APIConnectionException e) {
//            // Connection error, should retry later
//            LOG.error("Connection error, should retry later", e);
//
//        } catch (APIRequestException e) {
//            // Should review the error, and fix the request
//            LOG.error("Should review the error, and fix the request", e);
//            LOG.info("HTTP Status: " + e.getStatus());
//            LOG.info("Error Code: " + e.getErrorCode());
//            LOG.info("Error Message: " + e.getErrorMessage());
//        }
//    }
}
