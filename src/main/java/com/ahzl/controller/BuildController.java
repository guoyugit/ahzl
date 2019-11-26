package com.ahzl.controller;

import com.ahzl.service.BuildService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BuildController {
    @Autowired
    private BuildService buildingsService;
    @RequestMapping("/insertBuild")
    protected String insert(){
       Map<String,Object> map= buildingsService.insertBuild();
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

    @RequestMapping("/updateBuild")
    protected String update(){
        Map<String,Object> map =  buildingsService.updateNums(4000);
        JSONObject jsonObject=JSONObject.fromObject(map);
        return jsonObject.toString();
    }
}
