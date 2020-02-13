package com.ahzl.utils.jiguang;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
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
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JiguangUtils {

    //在极光注册的APPKEY和MASTERSECRET    必填
    private static final String APPKEY = "057f04d899e4f4156719f7b4";
    private static final String MASTERSECRET = "aa5ea2831781b15abc5fd6f2";
    //测试应用
//    private static final String APPKEY = "0586fdd31322e6d9966f677e";
//    private static final String MASTERSECRET = "d352760b25067021dd61068d";

    private static JPushClient jpushClient = null;

    private static Logger logger = LoggerFactory.getLogger(JiguangUtils.class);

    public static PushPayload buildPushObject_all_alias_alert(List<String> aliasList, String content, String title, String msg) {
        IosAlert alert = IosAlert.newBuilder()
                .setTitleAndBody(title, null, content)
                .setActionLocKey("PLAY")
                .build();
        logger.info("生成对象" + new Date());
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(aliasList))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                AndroidNotification.newBuilder().setTitle(title).setAlert(content).build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder().incrBadge(1).setAlert(alert).build()
                        )
                        .build()
                )
                .setMessage(Message.newBuilder().setMsgContent(msg).build())
                .setOptions(Options.newBuilder().setApnsProduction(false).build())
                .build();
    }

    private static PushPayload buildPushObject_all_alertWithTitle(String content, String title, String msg) {
        // 创建一个IosAlert对象，可指定APNs的alert、title等字段
        IosAlert alert = IosAlert.newBuilder()
                .setTitleAndBody(title, null, content)
                .setActionLocKey("PLAY")
                .build();

        return PushPayload.newBuilder()
                // 指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.android_ios())
                // 指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registrationid  Audience.registrationId(registrationId)
                .setAudience(Audience.all())
                // jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        // 指定当前推送的android通知
                        .addPlatformNotification(
                                AndroidNotification.newBuilder().setAlert(content).setTitle(title)
                                        // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        //.addExtra("context", extrasparam)
                                        .build()
                        )
                        // 指定当前推送的iOS通知
                        .addPlatformNotification(
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                IosNotification.newBuilder().setAlert(alert).incrBadge(1)
                                        // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                        // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                        //.setSound("sound.caf")
                                        // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        //.addExtra("context", extrasparam)
                                        .build()
                        )
                        // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                        // 取消此注释，消息推送时ios将无法在锁屏情况接收
                        // .setContentAvailable(true)
                        .build()
                )
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg)
                        //.setTitle(msg_title)
                        //.addExtra("message extras key", extrasparam)
                        .build()
                )
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        //.setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        //.setTimeToLive(86400)

                        .build()
                )
                .build();
    }

    public static PushPayload buildPushObject_all_alias(List<String> aliasList, String msg) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.alias(aliasList))
                .setMessage(Message.newBuilder().setMsgContent(msg).build()) //透传
                //.setNotification(Notification.alert(msg))  //通知
                .build();
    }

    public static PushPayload buildPushObject_all(String msg) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setMessage(Message.newBuilder().setMsgContent(msg).build()) //透传
                //.setNotification(Notification.alert(msg))  //通知
                .build();
    }

    //指定别名顶部通知加透传推送
    public static int jiguangHeadPush(List<String> alias, String content, String title, String msg) {
        int code = 1;
        try {
            jpushClient = new JPushClient(MASTERSECRET, APPKEY);

            PushPayload payload = buildPushObject_all_alias_alert(alias, content, title, msg);
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            if (result != null) {
                System.out.println(result.toString());
                code = (Integer) JSONObject.parseObject(result.toString()).get("statusCode");
            }
        } catch (APIConnectionException e) {
            code = 1;
            e.printStackTrace();
        } catch (APIRequestException e) {
            code = 1;
            e.printStackTrace();
        }
        return code;
    }

    //指定别名推送  , null, ClientConfig.getInstance()
    public static int jiguangPush(List<String> alias, String msg) {
        int code = 1;
        try {
            jpushClient = new JPushClient(MASTERSECRET, APPKEY);

            PushPayload payload = buildPushObject_all_alias(alias, msg);
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            if (result != null) {
                System.out.println(result.toString());
                code = (Integer) JSONObject.parseObject(result.toString()).get("statusCode");
            }
        } catch (APIConnectionException e) {
            code = 1;
            e.printStackTrace();
        } catch (APIRequestException e) {
            code = 1;
            e.printStackTrace();
        }
        return code;
    }

    //所有人顶部通知加透传推送
    public static int jiguangHeadPushall(String content, String title, String msg) {
        int code = 0;
        try {
            jpushClient = new JPushClient(MASTERSECRET, APPKEY);

            PushPayload payload = buildPushObject_all_alertWithTitle(content, title, msg);
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            if (result != null) {
                System.out.println(result.toString());
                code = (Integer) JSONObject.parseObject(result.toString()).get("statusCode");
            }
        } catch (APIConnectionException e) {
            code = 1;
            e.printStackTrace();
        } catch (APIRequestException e) {
            code = 1;
            e.printStackTrace();
        }
        return code;
    }

    //所有人推送
    public static int jiguangPushall(String msg) {
        int code = 0;
        try {
            jpushClient = new JPushClient(MASTERSECRET, APPKEY);

            PushPayload payload = buildPushObject_all(msg);
            PushResult result = jpushClient.sendPush(payload);
            jpushClient.close();
            if (result != null) {
                System.out.println(result.toString());
                code = (Integer) JSONObject.parseObject(result.toString()).get("statusCode");
            }
        } catch (APIConnectionException e) {
            code = 1;
            e.printStackTrace();
        } catch (APIRequestException e) {
            code = 1;
            e.printStackTrace();
        }
        return code;
    }

    public static void main(String[] args) {
        try {

            String[] alia = new String[]{String.valueOf(18)};
            List<String> alias = Arrays.asList(alia);

            /*List<String> ali = new ArrayList<>();
            ali.add("8");
            ali.add("11");*/

            /*Map<String, Object> params = new HashMap<>();
            params.put("code", 903);
            params.put("msg", "adsd");
            params.put("alias", new String[]{String.valueOf(8), String.valueOf(10)});*/

            //Map<String, Object> params = new HashMap<>();
            //params.put("msg", "{\"msg\":\"好友添加申请\",\"code\":903}");
            //params.put("alias", new String[]{String.valueOf(8)});
            //String json = JSONObject.toJSONString(params);
            //JSONObject jsons = JSONObject.parseObject(json);
            //List<String> alias = (List<String>) jsons.get("alias");
            //String msg = (String) jsons.get("msg");

            Map<String, Object> param = new HashMap<>();
            /*param.put("code", 910);
            param.put("information", "VIP服务");
            param.put("informurl", "https://kaldijiuyangapp.oss-cn-beijing.aliyuncs.com/root/inform/2019/0505/20190505025050rtwkha");
            param.put("biaoshi", "zx1");
            param.put("activitybt", "测试一条资讯消息");
            param.put("time", DateUtils.format(new Date()));*/

            param.put("code", 907);
            param.put("inform", "xtInform");   //系统通知的标识
            param.put("informbt", "系统通知"); //系统通知的标题
            //系统通知的头像图片
            param.put("informimg", "https://kaldijiuyangapp.oss-cn-beijing.aliyuncs.com/root/sysimage/2019/0516/20190516101805v05l13");

            param.put("informtype", 2);  //系统通知类型
            param.put("informname", "投诉消息");  //系统通知类型名称
            //系统通知的消息内容
            param.put("informmsg", "测试123");
            param.put("time", DateUtils.formatDate(new Date()));

            Map<String, Object> params = new HashMap<>();
            params.put("msg", param);
            params.put("channel", "JPush");
            String json = JSONObject.toJSONString(params);
            /*jpushClient = new JPushClient(MASTERSECRET, APPKEY, null, ClientConfig.getInstance());
            PushPayload payload =
                    //buildPushObject_all_alias(alias, json);
                    buildPushObject_all_alias_alert(alias, "测试", "投诉消息", json);
                    //buildPushObject_all_alertWithTitle("测试一条资讯消息", "资讯消息", json);
            PushResult result = jpushClient.sendPush(payload);
            Integer code = (Integer) JSONObject.parseObject(result.toString()).get("statusCode");*/

            int code = jiguangHeadPush(alias, "测试123", "投诉消息", json);
            System.out.println(code);
            //int code = jiguangPush(alias, "测试");
            /*Integer[] num = new Integer[]{1, 2, 3, 4};
            String[] str = new String[num.length];
            for (int i = 0; i < num.length; i++){
                str[i] = String.valueOf(num[i]);
            }*/
            //String result = "{\"msg_id\":3037689929,\"sendno\":1463225981,\"statusCode\":0}";
            //Integer code = (Integer) JSONObject.parseObject(result).get("statusCode");
            /*JSONObject jsons = JSONObject.parseObject(json);
            List<String> alias = (List<String>) jsons.get("alias");
            System.out.println(json);*/
            //System.out.println(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }*/
    }

}
