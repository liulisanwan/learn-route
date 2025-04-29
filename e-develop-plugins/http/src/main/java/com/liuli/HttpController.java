package com.liuli;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * http控制器
 *
 * @author zhanghui
 * @date 2024/10/10
 */
@RestController
@Api(tags = "http控制器")
@Slf4j
@RequestMapping("/http")
public class HttpController {

    @Autowired
    HttpTestService testService;
    @Autowired
    HttpTestService2 testService2;


    @GetMapping("/test1")
    @ApiOperation("测试1")
    public String test1() throws IOException {
        Map<Boolean, String> map = testService.releaseProduction2WithOkHttp3();
        return null;
    }

    @GetMapping("/test2")
    @ApiOperation("测试2")
    public String test2() throws IOException {
        Map<Boolean, String> map = testService.releaseProduction2WithHutool();
        return null;
    }

    @GetMapping("/test3")
    @ApiOperation("测试3")
    public String test3() throws IOException {
        Map<Boolean, String> map = testService2.releaseProduction2WithRetrofit();
        return null;
    }

    @PostMapping("/test")
    public void test (@RequestBody RequestData requestData) {
        log.error("数据:"+ JSON.toJSONString(requestData));

    }
}
