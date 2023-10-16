package com.liuli.data.controller;

import com.liuli.entity.DeviceConfig;
import com.liuli.util.GenerateScriptData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "生成数据")
@RequestMapping("/generate")
public class GenerateController {

    @Autowired
    private GenerateScriptData generateScriptData;



    @GetMapping("/generateScriptData")
    @ApiOperation(value = "生成脚本数据")
    public String generateScriptData(DeviceConfig deviceConfig) {
        return generateScriptData.generateScriptData(deviceConfig);
    }
}
