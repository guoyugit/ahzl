package com.ahzl;

import com.ahzl.dao.BuildDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * @author liutao
 * @date 2018/04/20
 */
public class TestBuilding {

    @Autowired
    private BuildDao buildDao;
//    public static void main(String[] args) {
//        Map<String, Object> jsonObject = new HashedMap();
//        //devId:开发者id 每个部门都分配开发者id（唯一）（必填）
//        jsonObject.put("devId", "0347b4c7784540d4b6369ca2dc471779");
//        //apiName:调用的api的名称（唯一）（必填）
//        jsonObject.put("timestamp", System.currentTimeMillis());
//        jsonObject.put("apiName", "wxb.building");
//        jsonObject.put("buildingId", "460");
//        String sign = CommonUtil.signatureWithParamsOnly(jsonObject, "fa130ecd227e4109b3ed854350d85021");
//        jsonObject.put("sign", sign);
//        //拼接url
//        String url = CommonUtil.getWholeUrl("http://10.96.8.101:8200/gateway/api/v1/grid", jsonObject);
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        String result = "";
//        try {
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == HttpStatus.SC_OK) {
//                //解析返回的值
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

    public static void main(String[] args) throws ParseException {
//        String s = "{\"code\":200,\"data\":\"{\\\"code\\\":200,\\\"status\\\":\\\"yes\\\",\\\"data\\\":{\\\"buildingId\\\":460,\\\"communityId\\\":176,\\\"communityName\\\":\\\"东城家园\\\",\\\"streetId\\\":16,\\\"streetName\\\":\\\"雪山路\\\",\\\"gridCode\\\":\\\"12011000301405\\\",\\\"type\\\":\\\"10\\\",\\\"no\\\":\\\"16\\\",\\\"noSuffix\\\":\\\"9\\\",\\\"address\\\":\\\"雪山路东城家园16号楼\\\",\\\"x\\\":117.288871,\\\"y\\\":39.11306,\\\"createUserId\\\":2018,\\\"createTime\\\":\\\"2019-01-21 11:40:00\\\",\\\"buildingPhoto\\\":\\\"/vux-img/87e1c4c643864fb99d47589907998392.jpg||/vux-img/87e1c4c643864fb99d47589907998392.jpg\\\",\\\"unitCount\\\":1,\\\"floorCountUp\\\":11,\\\"floorCountDown\\\":0,\\\"doorCountPerFloorDefault\\\":4,\\\"isMultiple\\\":\\\"1\\\",\\\"gatePhoto\\\":\\\"/vux-img/377903780de54365ad4b259f1d312a93.jpg||/vux-img/377903780de54365ad4b259f1d312a93.jpg\\\",\\\"checkStage\\\":\\\"2\\\",\\\"checkCount\\\":0}}\",\"msg\":\"调用成功！\"}";
//        JSONObject jsonObject = JSONObject.fromObject(s);
//        JSONObject build = new JSONObject();
//        Build newBuild=new Build();
//        JsonToStringUtil jsonToStringUtil=new JsonToStringUtil();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String data = jsonObject.get("data").toString();
//        if(StringUtils.isNotBlank(data)){
//            JSONObject data1 = JSONObject.fromObject(data);
//            String data2 = data1.get("data").toString();
//            if(StringUtils.isNotBlank(data2)){
//                JSONObject object = JSONObject.fromObject(data2);
//                if (object != null) {
//                    build=jsonToStringUtil.jsonToString(build);
//                    String createTime = (String) build.get("createTime");
//                    if(StringUtils.isNotBlank(createTime))build.put("createTime", sdf.parse(createTime));
//                    String updateTime = (String)  build.get("updateTime");
//                    if(StringUtils.isNotBlank(updateTime)) build.put("updateTime", sdf.parse(updateTime));
//                    newBuild = (Build) JSONObject.toBean(build, Build.class);
//                    // newBuild.setId(id + "");
//                    newBuild.setId(newBuild.getBuildingId());
//                    buildDao.insertBuild(newBuild);
//                    // id++;
//                }
//            }
//        }
    }
}
