package com.ahzl.controller;

import com.ahzl.constant.Constants;
import com.ahzl.enums.ResultCodeEnum;
import com.ahzl.model.ResultEntity;
import com.ahzl.service.QueryParamService;
import com.ahzl.utils.CommonUtils;
import com.ahzl.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * <p>Title:QueryParamController.java</p >
 * <p>Description: </p >
 * <p>Copyright: 公共服务与应急管理战略业务本部 Copyright(c)2019</p >
 * <p>Date:2019/11/26 9:42</p >
 *
 * @author guoyu (guoyu@mail.taiji.com.cn)
 * @version 1.0
 */
@RestController
public class QueryParamController {

    @Autowired
    private QueryParamService queryParamService;

    @PostMapping("/insertInstruction")
    public ResultEntity insertQueryParam(@RequestBody Map<String, Object> params) {
        if (params.containsKey(Constants.DATA_TYPE) && params.containsKey(Constants.INSTRUCTION) && params.containsKey(Constants.INSTRUCTION_TYPE) && params.containsKey(Constants.START_TIME) && params.containsKey(Constants.END_TIME)) {
            queryParamService.insertQueryInstruction(CommonUtils.convertMap2MultiValueMap(params));
            return ResultUtils.success();
        }
        System.out.println("11");
        return ResultUtils.fail(ResultCodeEnum.PARAMETER_ERROR);
    }
}
