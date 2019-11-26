package com.ahzl.service;

import com.ahzl.dao.DoorDao;
import com.ahzl.dao.PeopleDao;
import com.ahzl.utils.QueryUtil;
import com.ahzl.model.People;
import com.ahzl.utils.HandleJsonUtils;
import com.ahzl.utils.JsonToStringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PeopleService {
    //   private Long id=2L;
    @Autowired
    private PeopleDao peopleDao;
    @Autowired
    private DoorDao doorDao;

    public Map<String, Object> insertPeople() {
        QueryUtil queryUtil = new QueryUtil();
        Map<String, Object> param = new HashMap<>();
        Map<String, Object> map = new HashMap();
        JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> doorIds = doorDao.selectAllNeedDoorId();//获取需要插入的doorId
        int num = 0;
        try {
            for (String doorId : doorIds) {
                param.put("doorId", doorId);
                //使用接口数据源
                String jsonStr = queryUtil.queryData("wxb.people", "http://10.96.8.101:8200/gateway/api/v1/grid", param);
                //  String jsonStr = getJson();//使用模拟数据源
                JSONObject people = new JSONObject();
                JSONObject jsonObject = JSONObject.fromObject(jsonStr);

                String data = jsonObject.get("data").toString();
                if (StringUtils.isNotBlank(data)) {
                    JSONObject data1 = JSONObject.fromObject(data);
                    String data2 = data1.get("data").toString();
                    if (StringUtils.isNotBlank(data2)) {
                        JSONArray jsonArray = JSONArray.fromObject(data2);
                        if (jsonArray.size() > 0) {
                            List<String> ids = peopleDao.selectAllPeopleId();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                people = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                                people = jsonToStringUtil.jsonToString(people);
                                String age = HandleJsonUtils.handleStr(people, "age");
//                                    int age = Integer.parseInt(ageStr);
                                String birthday = HandleJsonUtils.handleStr(people, "birthday");
                                String createTime = HandleJsonUtils.handleStr(people, "createTime");
                                String updateTime = HandleJsonUtils.handleStr(people, "updateTime");
                                if (StringUtils.isNotBlank(createTime))
                                    people.put("createTime", sdf.parse(createTime));
                                if (StringUtils.isNotBlank(updateTime))
                                    people.put("updateTime", sdf.parse(updateTime));
                                if (StringUtils.isNotBlank(birthday))
                                    people.put("birthday", sdf.parse(birthday));
                                people.put("age", age);
                                People newpeople = (People) JSONObject.toBean(people, People.class);
                                //  newpeople.setId(id+"");
                                if(ids.contains(newpeople.getPeopleId())) continue;//库中已存在该数据，无需插入
                                newpeople.setId(newpeople.getPeopleId());
                                peopleDao.insertPeople(newpeople);
                                // id++;
                            }
                            map.put("result", true);
                            map.put("num", jsonArray.size() + "");
                        }
                    }
                }
            }
        } catch (Exception e) {
            map.put("result", false);
            map.put("num", "存入人口失败");
            System.out.println("存入人口失败");
            e.printStackTrace();

        }
        return map;
    }

    //模拟数据源
    public String getJson() {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject people = new JSONObject();
        people.put("peopleId", 33);
        people.put("doorId", 65688);
        people.put("familyId", 321);
        people.put("name", "赵赵");
        people.put("sex", "MAN");
        people.put("cardType", "IDCARD");
        people.put("cardCode", "130525199101012313");
        people.put("birthday", "1991-01-01 00:00:00");
        people.put("nation", "BUYIZU");
        people.put("marriage", "LIHUN");
        people.put("type", "CHANGZHU");
        people.put("eduLevel", "HIGH_SCHOOL");
        people.put("politic", "NONGGONGMINZHU");
        people.put("regAddress", "户籍地址");
        people.put("age", 28);
        people.put("phone", "16666668888");
        people.put("org", "xxxx");
        people.put("profession", "xxxx");
        people.put("religion", "7");
        people.put("gridCode", "12011000300901");
        people.put("militaryStatus", "OTHER");
        people.put("isLivingAlone", "NO");
        people.put("isDisability", "NO");
        people.put("isTeen", "NO");
        people.put("isElder", "NO");
        people.put("prEquals", "RENHUYIZHI");
        people.put("addType", "PUCHA");
        people.put("masterRelation", "BENREN");
        people.put("createUserId", 1985);
        people.put("createTime", "2019-03-25 14:56:31");
        people.put("updateUserId", 1985);
        people.put("updateTime", "2019-03-25 14:56:31");
        List peoples = new ArrayList();
        peoples.add(people);
        data.put("page", 1);
        data.put("limit", 2);
        data.put("data", peoples);
        jsonObject.put("code", 200);
        jsonObject.put("status", "yes");
        jsonObject.put("data", data);
        return jsonObject.toString();

    }
}
