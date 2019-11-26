package com.ahzl.controller;


import com.ahzl.service.DoorService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DoorController {
    @Autowired
    private DoorService doorService;
    @RequestMapping("/insertDoor")
    protected String insert(){
        Map<String,Object> map=doorService.insertDoor();
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

    @RequestMapping("/updateDoor")
    protected String updateDoor(){
        Map<String,Object> map=doorService.updateDoor();
        JSONObject jsonObject=JSONObject.fromObject(map);
        return jsonObject.toString();
    }
}
