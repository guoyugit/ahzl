package com.ahzl.service;

import com.ahzl.model.Build;
import com.ahzl.utils.QueryUtil;
import com.ahzl.dao.BuildDao;
import com.ahzl.utils.JsonToStringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BuildService {
    // private long id=2L;
    @Autowired
    private BuildDao buildDao;

    //存入建筑物数据
    public Map<String, Object> insertBuild() {
        Map<String, Object> param = new HashMap<>();
        //设置抽取条数
        param.put("areaCode", "120110");
//        param.put("page","1");
//        param.put("limit","50");

        Map<String, Object> map = new HashMap<>();
        //使用接口数据源
        QueryUtil queryUtil = new QueryUtil();
        String jsonStr = queryUtil.queryData("wxb.buildings", "http://10.96.8.101:8200/gateway/api/v1/grid", param);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
        try {
            // String jsonStr = getJson();//使用模拟数据源
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject build = new JSONObject();
            Build newBuild = new Build();

            String data = jsonObject.get("data").toString();
            if (StringUtils.isNotBlank(data)) {
                JSONObject data1 = JSONObject.fromObject(data);
                String data2 = data1.get("data").toString();
                if (StringUtils.isNotBlank(data2)) {
                    JSONArray jsonArray = JSONArray.fromObject(data2);
                    if (jsonArray.size() > 0) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            build = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            build = jsonToStringUtil.jsonToString(build);
                            String createTime = (String) build.get("createTime");
                            if (StringUtils.isNotBlank(createTime)) build.put("createTime", sdf.parse(createTime));
                            String updateTime = (String) build.get("updateTime");
                            if (StringUtils.isNotBlank(updateTime)) build.put("updateTime", sdf.parse(updateTime));
                            newBuild = (Build) JSONObject.toBean(build, Build.class);
                            // newBuild.setId(id + "");
                            newBuild.setId(newBuild.getBuildingId());
                            buildDao.insertBuild(newBuild);
                            // id++;
                        }
                        map.put("result", true);
                        map.put("num", jsonArray.size() + "");
                    }
                }
            }
        } catch (Exception e) {
            map.put("result", false);
            map.put("num", "存入建筑物失败");
            System.out.println("存入建筑物失败");
            e.printStackTrace();

        }
        return map;
    }

    //存入建筑物数据
    public Map<String, Object> updateBuild() {
        Map<String, Object> param = new HashMap<>();
        //设置抽取条数
        param.put("areaCode", "120110");
//        param.put("page","1");
//        param.put("limit","50");

        Map<String, Object> map = new HashMap<>();
        //使用接口数据源
        QueryUtil queryUtil = new QueryUtil();
        String jsonStr = queryUtil.queryData("wxb.buildings", "http://10.96.8.101:8200/gateway/api/v1/grid", param);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
        try {
            // String jsonStr = getJson();//使用模拟数据源
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject build = new JSONObject();
            Build newBuild = new Build();

            String data = jsonObject.get("data").toString();
            if (StringUtils.isNotBlank(data)) {
                JSONObject data1 = JSONObject.fromObject(data);
                String data2 = data1.get("data").toString();
                if (StringUtils.isNotBlank(data2)) {
                    JSONArray jsonArray = JSONArray.fromObject(data2);
                    if (jsonArray.size() > 0) {
                        //获取所有id
                        List<String> ids = buildDao.selectAllBuildId();
                        for (int i = 0; i < jsonArray.size(); i++) {
                            build = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                            build = jsonToStringUtil.jsonToString(build);
                            String createTime = (String) build.get("createTime");
                            if (StringUtils.isNotBlank(createTime)) build.put("createTime", sdf.parse(createTime));
                            String updateTime = (String) build.get("updateTime");
                            if (StringUtils.isNotBlank(updateTime)) build.put("updateTime", sdf.parse(updateTime));
                            newBuild = (Build) JSONObject.toBean(build, Build.class);
                            // newBuild.setId(id + "");
                            if(ids.contains(newBuild.getBuildingId())) continue;//库中已存在该数据，无需插入
                            newBuild.setId(newBuild.getBuildingId());
                            buildDao.insertBuild(newBuild);
                            // id++;
                        }
                        map.put("result", true);
                        map.put("num", jsonArray.size() + "");
                    }
                }
            }
        } catch (Exception e) {
            map.put("result", false);
            map.put("num", "存入建筑物失败");
            System.out.println("存入建筑物失败");
            e.printStackTrace();

        }
        return map;
    }

    //组装模拟数据
    public String getJson() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject data = new JSONObject();
            JSONObject build = new JSONObject();
            build.put("buildingId", "301");
            build.put("gateId", "341");
            build.put("communityName", "医院宿舍");
            build.put("streetId", "211");
            build.put("streetName", "天山南路");
            build.put("gridCode", "12011000300505");
            build.put("type", "10");
            build.put("address", null);
            build.put("x", "117.265935");
            build.put("y", "39.123924");
            build.put("createUserId", "233");
            build.put("createTime", "2019-01-15 11:33:14");
            build.put("updateUserId", "233");
            build.put("updateTime", "2019-02-21 15:47:18");
            build.put("gateNo", "123");
            build.put("gateNoSuffix", "1");
            build.put("gateNoPreffix", "123");
            build.put("gateSubNo", "1");
            build.put("gateSubNoSuffix", "1");
            build.put("gatePhoto", "1");
            build.put("unitCount", "1");
            build.put("floorCountUp", "2");
            build.put("doorCountPerFloorDefault", "4");
            build.put("isMultiple", "1");
            build.put("checkStage", "1");
            build.put("checkCount", "0");
            build.put("buildingPhoto", "1");
            JSONObject build1 = build;
            build1.put("buildingId", 302);
            List builds = new ArrayList();
            builds.add(build);
            builds.add(build1);
            data.put("page", 1);
            data.put("limit", 2);
            data.put("data", builds);
            jsonObject.put("code", 200);
            jsonObject.put("status", "yes");
            jsonObject.put("data", data);
        } catch (Exception e) {
            System.out.println("时间格式转换错误");
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public Map<String, Object> updateNums(int num) {
        Map<String, Object> map = new HashMap<>();
        List<String> strs = buildDao.selectAllBuildIdForUpdate();
        int nums = 0;
        if (strs != null && strs.size() > 0) {
            for (int i = 0; i < num; i++) {
                if(i >= strs.size()) break;
                nums++;
                String id = strs.get(i);
                if (StringUtils.isNotBlank(id)) updateBuildById(id);
            }
        }
        map.put("result", true);
        map.put("msg", "更新条数："+nums);
        return map;
    }

    public Map<String, Object> updateBuildById(String buildId) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        //设置抽取条数
        param.put("buildingId", buildId);
        //使用接口数据源
        QueryUtil queryUtil = new QueryUtil();
        String jsonStr = queryUtil.queryData("wxb.building", "http://10.96.8.101:8200/gateway/api/v1/grid", param);
        try {
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            JSONObject build = new JSONObject();
            Build newBuild = new Build();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
            String data = jsonObject.get("data").toString();
            if (StringUtils.isNotBlank(data)) {
                JSONObject data1 = JSONObject.fromObject(data);
                if (data1.toString().indexOf("data") >= 0) {
                    String data2 = data1.get("data").toString();
                    if (StringUtils.isNotBlank(data2)) {
                        JSONObject object = JSONObject.fromObject(data2);
                        if (object != null) {
                            build = jsonToStringUtil.jsonToString(object);
                            String createTime = (String) build.get("createTime");
                            if (StringUtils.isNotBlank(createTime)) build.put("createTime", sdf.parse(createTime));
                            String updateTime = (String) build.get("updateTime");
                            if (StringUtils.isNotBlank(updateTime)) build.put("updateTime", sdf.parse(updateTime));
                            newBuild = (Build) JSONObject.toBean(build, Build.class);
                            // newBuild.setId(id + "");
                            newBuild.setId(newBuild.getBuildingId());
                            buildDao.updateBuildById(newBuild);
                            map.put("result", true);
                            map.put("msg", "success");
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "fail");
        }
        return map;
    }

}
