package com.ahzl.service;

import com.ahzl.utils.JsonToStringUtil;
import com.ahzl.utils.QueryUtil;
import com.ahzl.dao.BuildDao;
import com.ahzl.dao.DoorDao;
import com.ahzl.model.Door;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoorService {
    // private Long id=2L;
    @Autowired
    private DoorDao doorDao;
    @Autowired
    private BuildDao buildDao;

    //存入户室数据
    public Map<String, Object> insertDoor() {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
        QueryUtil queryUtil = new QueryUtil();
        //获取建筑物的id
        List<String> buildIds = buildDao.selectAllBuildId();
        int num = 0;
        try {
            for (String buildId : buildIds) {//循环根据buildId查出户室并插入数据库
                param.put("buildingId", buildId);
                //使用接口数据源
                String jsonStr = queryUtil.queryData("wxb.doors", "http://10.96.8.101:8200/gateway/api/v1/grid", param);

                JSONObject jsonObject = JSONObject.fromObject(jsonStr);
                JSONObject door = new JSONObject();

                String data = jsonObject.get("data").toString();
                if (StringUtils.isNotBlank(data)) {
                    JSONObject data1 = JSONObject.fromObject(data);
                    String data2 = data1.get("data").toString();
                    if (StringUtils.isNotBlank(data2)) {
                        JSONArray jsonArray = JSONArray.fromObject(data2);
                        if (jsonArray.size() > 0) {
                            for (int i = 0; i < jsonArray.size(); i++) {
                                door = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                                String unitNo = handleStr(door, "unitNo");
                                String unitSuffix = handleStr(door, "unitSuffix");
                                String unitIndex = handleStr(door, "unitIndex");
                                String floors = handleStr(door, "floors");
                                JSONArray jsonArray2 = JSONArray.fromObject(floors);
                                if (jsonArray2 != null) {
                                    for (int j = 0; j < jsonArray2.size(); j++) {
                                        JSONObject floor = jsonArray2.getJSONObject(j);
                                        String floorNo = handleStr(floor, "floorNo");
                                        String floorSuffix = handleStr(floor, "floorSuffix");
                                        String floorIndex = handleStr(floor, "floorIndex");
                                        String doors = handleStr(floor, "doors");
                                        JSONArray jsonArray3 = JSONArray.fromObject(doors);
                                        for (int k = 0; k < jsonArray3.size(); k++) {
                                            JSONObject d = (JSONObject) jsonArray3.get(k);
                                            d = jsonToStringUtil.jsonToString(d);//将所属性类型转换为String
                                            //对应数据类型转换处理
                                            String peopleCount = "-1";
                                            peopleCount = handleStr(door, "peopleCount");
                                            d.put("peopleCount", peopleCount);
                                            String partyMemberCount = "-1";
                                            partyMemberCount = handleStr(door, "partyMemberCount");
                                            d.put("partyMemberCount", partyMemberCount);
                                            //数据组装处理
//                                            int unitNoI = Integer.parseInt(unitNo);
                                            d.put("unitNo", unitNo);
                                            d.put("unitSuffix", unitSuffix);
//                                            int unitIndexI = Integer.parseInt(unitIndex);
                                            d.put("unitIndex", unitIndex);
//                                            int floorNoI = Integer.parseInt(floorNo);
                                            d.put("floorNo", floorNo);
                                            d.put("floorSuffix", floorSuffix);
//                                            int floorIndexI = Integer.parseInt(floorIndex);
                                            d.put("floorIndex", floorIndex);
                                            //数据转换为Door类型
                                            Door newDoor = (Door) JSONObject.toBean(d, Door.class);
                                            System.out.println(newDoor.toString());
                                            //newDoor.setId(id+"");
                                            newDoor.setId(newDoor.getDoorId());
                                            doorDao.insertDoor(newDoor);
                                            //id++;
                                        }
                                        num += jsonArray3.size();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            map.put("result", true);
            map.put("num", num);
        } catch (Exception e) {
            map.put("result", false);
            map.put("num", "存入户室失败");
            e.printStackTrace();
        }
        return map;
    }

    //模拟数据源
    public String getJson() {
        JSONObject door = new JSONObject();
        JSONObject floor = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        door.put("doorId", 65686);
        door.put("gridCode", "12011000300901");
        door.put("buildingId", 1482);
        door.put("no", "201");
        door.put("noSuffix", "室");
        door.put("usage", "1");
        door.put("area", 0.0);
        door.put("usageForm", "1");
        door.put("roomCount", 3);
        door.put("ownerName", "xxx");
        door.put("ownerPhone", "xxx");
        door.put("ownerCardtype", "xxx");
        door.put("onerCardCode", "xxx");
        door.put("propertyNote", "xxx");
        door.put("address", "1单元2层201室");
        door.put("status", "0");
        door.put("orgId", 650);
        door.put("peopleCount", 1);
        door.put("partyMemberCount", 1);
        List doors = new ArrayList();
        doors.add(door);
        floor.put("floorNo", "2");
        floor.put("floorSuffix", "层");
        floor.put("floorIndex", 2);
        floor.put("doors", doors);
        List floors = new ArrayList();
        floors.add(floor);
        data.put("unitNo", "1");
        data.put("unitSuffix", "单元");
        data.put("unitIndex", 1);
        data.put("floors", floors);
        List datas = new ArrayList();
        datas.add(data);
        jsonObject.put("code", 200);
        jsonObject.put("status", "yes");
        jsonObject.put("floorIndex", 2);
        jsonObject.put("data", datas);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    public int stringtoInt(JSONObject jsonObject, String key) {

        String valueStr = jsonObject.getString(key);
        if (StringUtils.isBlank(valueStr)) return -1;
        int value = Integer.parseInt(valueStr);
        return value;
    }

    public String handleStr(JSONObject obj, String rex) {
        String valueStr = "";
        if (obj.toString().indexOf(rex) >= 0) {
            Object o = obj.get(rex);
            if (null != o) valueStr = o.toString();
        }
        return valueStr;
    }


    //存入户室数据
    public Map<String, Object> updateDoor() {
        Map<String, Object> map = new HashMap<>();

        List<String> buildIds = doorDao.selectNeedUpdateBuildId();
        int flag = 0;
        if (null != buildIds && buildIds.size() > 0) {
            for (int i = 0; i < buildIds.size(); i++) {
                if (flag == 100) break;
                String bid = buildIds.get(i);
                if (StringUtils.isNotBlank(bid)) {
                    flag++;
                    JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
                    QueryUtil queryUtil = new QueryUtil();
                    Map<String, Object> param = new HashMap<>();
                    param.put("buildingId", bid);
                    //使用接口数据源
                    String jsonStr = queryUtil.queryData("wxb.doors", "http://10.96.8.101:8200/gateway/api/v1/grid", param);
                    JSONObject jsonObject = JSONObject.fromObject(jsonStr);
                    JSONObject door = new JSONObject();
                    String data = jsonObject.get("data").toString();
                    if (StringUtils.isNotBlank(data)) {
                        JSONObject data1 = JSONObject.fromObject(data);
                        String data2 = data1.get("data").toString();
                        if (StringUtils.isNotBlank(data2)) {
                            JSONArray jsonArray = JSONArray.fromObject(data2);
                            if (jsonArray.size() > 0) {
                                List<String> ids = doorDao.selectAllDoorId();
                                for (int j = 0; j < jsonArray.size(); j++) {
                                    door = jsonArray.getJSONObject(j); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                                    String unitNo = handleStr(door, "unitNo");
                                    String unitSuffix = handleStr(door, "unitSuffix");
                                    String unitIndex = handleStr(door, "unitIndex");
                                    String floors = handleStr(door, "floors");
                                    JSONArray jsonArray2 = JSONArray.fromObject(floors);
                                    if (jsonArray2 != null) {
                                        for (int k = 0; k < jsonArray2.size(); k++) {
                                            JSONObject floor = jsonArray2.getJSONObject(k);
                                            String floorNo = handleStr(floor, "floorNo");
                                            String floorSuffix = handleStr(floor, "floorSuffix");
                                            String floorIndex = handleStr(floor, "floorIndex");
                                            String doors = handleStr(floor, "doors");
                                            JSONArray jsonArray3 = JSONArray.fromObject(doors);
                                            for (int l = 0; l < jsonArray3.size(); l++) {
                                                JSONObject d = (JSONObject) jsonArray3.get(l);
                                                d = jsonToStringUtil.jsonToString(d);//将所属性类型转换为String
                                                //对应数据类型转换处理
                                                String peopleCount = "-1";
                                                peopleCount = handleStr(door, "peopleCount");
                                                d.put("peopleCount", peopleCount);
                                                String partyMemberCount = "-1";
                                                partyMemberCount = handleStr(door, "partyMemberCount");
                                                d.put("partyMemberCount", partyMemberCount);
                                                //数据组装处理
//                                            int unitNoI = Integer.parseInt(unitNo);
                                                d.put("unitNo", unitNo);
                                                d.put("unitSuffix", unitSuffix);
//                                            int unitIndexI = Integer.parseInt(unitIndex);
                                                d.put("unitIndex", unitIndex);
//                                            int floorNoI = Integer.parseInt(floorNo);
                                                d.put("floorNo", floorNo);
                                                d.put("floorSuffix", floorSuffix);
//                                            int floorIndexI = Integer.parseInt(floorIndex);
                                                d.put("floorIndex", floorIndex);
                                                //数据转换为Door类型
                                                Door newDoor = (Door) JSONObject.toBean(d, Door.class);
                                                System.out.println(newDoor.toString());
                                                //newDoor.setId(id+"");
                                                if(ids.contains(newDoor.getDoorId())) continue;//库中已存在该数据，无需插入
                                                newDoor.setId(newDoor.getDoorId());
                                                doorDao.insertDoor(newDoor);
                                                //id++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        map.put("result", true);
        map.put("msg", "更新数据" + flag);
        return map;
    }
}
