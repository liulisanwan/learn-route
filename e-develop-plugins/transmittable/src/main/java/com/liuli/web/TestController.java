package com.liuli.web;

import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
@ApiModel("测试")
public class TestController {


    @RequestMapping("/test")
    public Map<String, Object> test(@RequestBody Map<String, Object> map) {
        System.err.println(map);
        return map;
    }
}
