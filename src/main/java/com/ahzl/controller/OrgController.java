package com.ahzl.controller;

import com.ahzl.service.OrgService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrgController {
    @Autowired
    private OrgService orgService;
    @RequestMapping("/insertOrg")
    protected String insert(@RequestParam(value = "pageNumber") String pageNumber,@RequestParam(value = "pageSize") String pageSize){
        if(StringUtils.isBlank(pageNumber)||StringUtils.isBlank(pageSize)) return "未传递pageNumber（页码数）或pageSize（每页数据量）参数";
        int pagenumber = 0;
        int pagesize = 10;
        try {
            pagenumber = Integer.parseInt(pageNumber);
            pagesize = Integer.parseInt(pageSize);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return"参数转换异常";
        }
        Map<String,Object> map=orgService.insertOrg(pagenumber,pagesize);
        String num= map.get("num").toString();
        Boolean result=(Boolean) map.get("result");
        if(result){
            map.put("result","成功");
        }else {
            map.put("result","失败");
        }
        JSONObject jsonObject=JSONObject.fromObject(map);
        return jsonObject.toString();
    }
}
