package com.demo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试模块")
public class TestController {


    @Autowired
    NacosConfigReader nacosConfigReader;

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public String test(String data,String dataName){
        return nacosConfigReader.getParameterValue(data,dataName);
    }
}
