package com.chlrob;

import cn.hutool.http.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(tags = "测试接口")
public class TestController {


    @GetMapping("/test")
    @ApiOperation("测试接口")
    public String test() {
        HttpRequest request = HttpRequest.get("http://10.102.0.129:8988/PQWMS/a/flow/task/testException");
        String body = request.execute().body();
        System.err.println(body);
        return body;
    }


    @GetMapping("/test2")
    @ApiOperation("测试接口2")
    @ResponseBody
    public String test2() {
        throw new NullPointerException("test2 error");
    }
}
