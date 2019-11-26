package com.ahzl.controller;


import com.ahzl.service.PeopleService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PeopleController {
    @Autowired
    private PeopleService peopleService;
    @RequestMapping("/insertPeople")
    protected String insert(){
        Map<String,Object> map= peopleService.insertPeople();
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
