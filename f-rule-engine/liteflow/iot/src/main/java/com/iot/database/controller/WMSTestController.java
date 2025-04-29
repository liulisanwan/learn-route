package com.iot.database.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wmsTest")
public class WMSTestController {




    @GetMapping("/order")
    @ApiOperation(value = "订单")
    public Map<String,Object> order(String orderNo){
        Map<String,Object> map=new HashMap<>();
        map.put("flag",1);
        return map;
    }


    @GetMapping("/agv")
    @ApiOperation(value = "订单")
    public Map<String,Object> agv(String orderNo){
        Map<String,Object> map=new HashMap<>();
        map.put("status","done");
        return map;
    }
}
