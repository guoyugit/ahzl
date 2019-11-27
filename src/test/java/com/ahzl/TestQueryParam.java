package com.ahzl;

import com.ahzl.enums.SendStateEnum;
import com.ahzl.model.QueryInstruction;
import com.ahzl.service.QueryParamService;
import com.ahzl.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date 2018/04/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQueryParam {

    @Autowired
    private QueryParamService queryParamService;

    @Test
    public void testInsert() {
        for (int i = 1; i <= 10; i++) {
            QueryInstruction queryInstruction = new QueryInstruction();
            queryInstruction.setId(i + "");
            queryInstruction.setDataType(1);
            queryInstruction.setInstruction("www.baidu.com");
            queryInstruction.setInstructionType(2);
            queryInstruction.setSendStatus(SendStateEnum.NOT_SEND.getCode());
            queryInstruction.setDelFlag("1");
            queryInstruction.setStartTime(LocalDateTime.now());
            queryInstruction.setEndTime(LocalDateTime.now().plusWeeks(1));
            queryInstruction.setCreateTime(LocalDateTime.now());
            queryInstruction.setUpdateTime(LocalDateTime.now());
            queryParamService.insertQueryParam(queryInstruction);
        }
    }

    @Test
    public void testPageList() {
        Map<String, Object> map = new HashMap<>();
        map.put("currIndex", 3);
        map.put("pageSize", 2);
        List<QueryInstruction> lists = queryParamService.queryInstructionsByPage(map);
        lists.forEach(queryInstruction -> {
            System.out.println(queryInstruction);
            System.out.println(CommonUtils.locateToStr(queryInstruction.getCreateTime()));
        });
    }

    @Test
    public void testListById() {
        QueryInstruction ins = queryParamService.queryInstructionsById("2019112710177625233642800");
        System.out.println(ins);
        System.out.println(CommonUtils.locateToStr(ins.getCreateTime()));
    }
}
