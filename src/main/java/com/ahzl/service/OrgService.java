package com.ahzl.service;


import com.ahzl.dao.OrgDao;
import com.ahzl.utils.QueryUtil;
import com.ahzl.model.Org;
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
public class OrgService {
    // private Long id=2L;
    @Autowired
    private OrgDao orgDao;

    //存入户室数据
    public Map<String, Object> insertOrg(Integer pageNumber , Integer pageSize) {
        Map<String, Object> param = new HashMap<>();
        //设置抽取条数
        param.put("areaCode", "120110");
        param.put("page", pageNumber+"");
        param.put("limit", pageSize+"");
        Map<String, Object> map = new HashMap<>();
        JsonToStringUtil jsonToStringUtil = new JsonToStringUtil();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        //使用接口数据源
        QueryUtil queryUtil = new QueryUtil();
        String jsonStr = queryUtil.queryData("wxb.orgs", "http://10.96.8.101:8200/gateway/api/v1/grid", param);

        try {
            // String jsonStr = getJson();//使用模拟数据源
            JSONObject org = new JSONObject();
            JSONObject jsonObject = JSONObject.fromObject(jsonStr);
            String data = jsonObject.get("data").toString();
            if (StringUtils.isNotBlank(data)) {
                JSONObject data1 = JSONObject.fromObject(data);
                String data2 = data1.get("data").toString();
                if (StringUtils.isNotBlank(data2)) {
                    JSONObject data3 = JSONObject.fromObject(data2);
                    String data4 = data3.get("data").toString();
                    if (StringUtils.isNotBlank(data4)) {
                        JSONArray jsonArray = JSONArray.fromObject(data4);
                        if (jsonArray.size() > 0) {
                            List<String> ids = orgDao.selectAllOrgId();
                            for (int i = 0; i < jsonArray.size(); i++) {
                                org = jsonArray.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                                org = jsonToStringUtil.jsonToString(org);
                                String establishDate = (String) org.get("establishDate");
                                if (StringUtils.isNotBlank(establishDate))
                                    org.put("establishDate", sd.parse(establishDate));
                                String createTime = (String) org.get("createTime");
                                if (StringUtils.isNotBlank(createTime))
                                    org.put("createTime", sd.parse(createTime));
                                String updateTime = (String) org.get("updateTime");
                                if (StringUtils.isNotBlank(updateTime))
                                    org.put("updateTime", sd.parse(updateTime));
                                Org newOrg = (Org) JSONObject.toBean(org, Org.class);
                                //  newOrg.setId(id+"");
                                if(ids.contains(newOrg.getOrgId())) continue;//库中已存在该数据，无需插入
                                newOrg.setId(newOrg.getOrgId());
                                orgDao.insertOrg(newOrg);
                                //id++;
                            }
                            map.put("result", true);
                            map.put("num", jsonArray.size() + "");
                        }
                    }
                }
            }

        } catch (Exception e) {
            map.put("result", false);
            map.put("num", "存入org转换失败");
            System.out.println("存入org转换失败");
            e.printStackTrace();

        }
        return map;
    }

    //模拟数据源
    public String getJson() {
        JSONObject jsonObject = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject org = new JSONObject();
        org.put("orgId", 33);
        org.put("gridCode", "12011000300403");
        org.put("name", "天津市东丽区红艺培训学校有限公司");
        org.put("certificateType", "SOCIAL_CREDIT_CODE");
        org.put("socialCreditCode", "91120110MA06HHRH2X");
        org.put("regAddress", "天津市东丽区香邑广场101-102");
        org.put("legalPersonName", "程玉和");
        org.put("legalPersonCardType", "IDCARD");
        org.put("legalPersonCardCode", "xxxx");
        org.put("phone", "13752384329");
        org.put("establishDate", "2019-01-02");
        org.put("brand", "红艺書院艺术培训");
        org.put("image", "/vux-img/a8b5144586244d578eb6de01d7834b48.jpg||/vux-img/a8b5144586244d578eb6de01d7834b48.jpg");
        org.put("createUserId", 1950);
        org.put("createTime", "2019-01-27 10:47:56");
        org.put("updateUserId", 1950);
        org.put("updateTime", "2019-01-27 10:47:56");
        org.put("establishPartyOrg", "NO");
        org.put("industryCategory", "P,83");
        org.put("active_address", "xxxx");
        org.put("industryCategory", "P,83");
        List orgs = new ArrayList();
        orgs.add(org);
        data.put("page", 1);
        data.put("limit", 2);
        data.put("data", orgs);
        jsonObject.put("code", 200);
        jsonObject.put("status", "yes");
        jsonObject.put("data", data);
        return jsonObject.toString();
    }
}
