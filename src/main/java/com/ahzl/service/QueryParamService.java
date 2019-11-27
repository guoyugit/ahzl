package com.ahzl.service;


import com.ahzl.constant.Constants;
import com.ahzl.dao.QueryParamDao;
import com.ahzl.enums.DelFlagEnum;
import com.ahzl.enums.SendStateEnum;
import com.ahzl.model.QueryInstruction;
import com.ahzl.utils.CommonUtils;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QueryParamService {
    @Autowired
    private QueryParamDao queryParamDao;

    //存入查询指令数据(测试类)
    @Transactional(rollbackFor = Exception.class)
    public void insertQueryParam(QueryInstruction queryInstruction) {
        queryParamDao.insertQueryParam(queryInstruction);
    }

    //通过id获取数据
    public QueryInstruction queryInstructionsById(String id) {
        return queryParamDao.queryInstructionsById(id);
    }

    //分页查询
    public List<QueryInstruction> queryInstructionsByPage(Map<String, Object> page) {
        String num = (String) page.get(Constants.PAGE_NUM);
        String size = (String) page.get(Constants.PAGE_SIZE);
        String currIndex = ((Integer.parseInt(num) - 1) * Integer.parseInt(size)) + "";
        page.put(Constants.CURR_INDEX,Integer.parseInt(currIndex));
        page.put(Constants.PAGE_SIZE,Integer.parseInt(size));
        return queryParamDao.queryInstructionsByPage(page);
    }

    //存入查询指令数据
    @Transactional(rollbackFor = Exception.class)
    public void insertQueryInstruction(MultiValueMap<String, Object> params) {
        QueryInstruction queryInstruction = handleParams(params);
        queryParamDao.insertQueryParam(queryInstruction);
    }

    private QueryInstruction handleParams(MultiValueMap<String, Object> params) {
        QueryInstruction queryInstruction = new QueryInstruction();
        queryInstruction.setId(CommonUtils.getInsId());
        queryInstruction.setDataType(Integer.parseInt(params.getFirst(Constants.DATA_TYPE).toString()));
        queryInstruction.setInstruction(params.getFirst(Constants.INSTRUCTION).toString());
        queryInstruction.setInstructionType(Integer.parseInt(params.getFirst(Constants.INSTRUCTION_TYPE).toString()));
        queryInstruction.setStartTime(CommonUtils.StrToLocate(params.getFirst(Constants.START_TIME).toString()));
        queryInstruction.setEndTime(CommonUtils.StrToLocate(params.getFirst(Constants.END_TIME).toString()));
        queryInstruction.setCreateTime(LocalDateTime.now());
        queryInstruction.setUpdateTime(LocalDateTime.now());
        queryInstruction.setDelFlag(DelFlagEnum.NORMAL.getCode());
        queryInstruction.setSendStatus(SendStateEnum.NOT_SEND.getCode());
        return queryInstruction;
    }

    //存入查询指令数据(测试)
    @Transactional(rollbackFor = Exception.class)
    public void insertQueryInstruction(MultiValueMap<String, Object> params, int size) {
        for (int i = 0; i < size; i++) {
            QueryInstruction queryInstruction = new QueryInstruction();
            queryInstruction.setId(CommonUtils.getInsId());
            queryInstruction.setDataType(Integer.parseInt(params.getFirst(Constants.DATA_TYPE).toString()));
            queryInstruction.setInstruction(params.getFirst(Constants.INSTRUCTION).toString() + i);
            queryInstruction.setInstructionType(Integer.parseInt(params.getFirst(Constants.INSTRUCTION_TYPE).toString()));
            queryInstruction.setStartTime(CommonUtils.StrToLocate(params.getFirst(Constants.START_TIME).toString()));
            queryInstruction.setEndTime(CommonUtils.StrToLocate(params.getFirst(Constants.END_TIME).toString()));
            queryInstruction.setCreateTime(LocalDateTime.now());
            queryInstruction.setUpdateTime(LocalDateTime.now());
            queryInstruction.setDelFlag(DelFlagEnum.NORMAL.getCode());
            queryInstruction.setSendStatus(SendStateEnum.NOT_SEND.getCode());
            queryParamDao.insertQueryParam(queryInstruction);
        }
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
