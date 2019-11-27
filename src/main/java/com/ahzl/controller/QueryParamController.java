package com.ahzl.controller;

import com.ahzl.constant.Constants;
import com.ahzl.enums.ResultCodeEnum;
import com.ahzl.model.QueryInstruction;
import com.ahzl.model.ResultEntity;
import com.ahzl.service.QueryParamService;
import com.ahzl.utils.CommonUtils;
import com.ahzl.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/queryIns")
public class QueryParamController {

    @Autowired
    private QueryParamService queryParamService;

    @PostMapping("/insertInstruction")
    public ResultEntity insertQueryParam(@RequestBody Map<String, Object> params) {
        if (params.containsKey(Constants.DATA_TYPE) && params.containsKey(Constants.INSTRUCTION) && params.containsKey(Constants.INSTRUCTION_TYPE) && params.containsKey(Constants.START_TIME) && params.containsKey(Constants.END_TIME)) {
            queryParamService.insertQueryInstruction(CommonUtils.convertMap2MultiValueMap(params), 12);
            return ResultUtils.success();
        }
        return ResultUtils.fail(ResultCodeEnum.PARAMETER_ERROR);
    }

    @GetMapping(path = "/{id}")
    public ResultEntity queryInsById(@NotEmpty(message = "id不能为空")
                                     @PathVariable(name = "id") String id) {

        QueryInstruction queryInstruction = queryParamService.queryInstructionsById(id);
        if (null != queryInstruction) {
            return ResultUtils.success(queryInstruction);
        }
        return ResultUtils.fail(ResultCodeEnum.PARAMETER_ERROR);
    }

    @PostMapping("/queryInsByPage")
    public ResultEntity queryInsByPage(@RequestBody Map<String, Object> params) {
        if (params.containsKey(Constants.PAGE_NUM) && params.containsKey(Constants.PAGE_SIZE)) {
            List<QueryInstruction> queryInstructions = queryParamService.queryInstructionsByPage(params);
            return ResultUtils.success(queryInstructions);
        }
        return ResultUtils.fail(ResultCodeEnum.PARAMETER_ERROR);
    }
}
