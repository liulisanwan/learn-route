package com.liuli.sql.controller;


import com.liuli.cmp.CustomDefaultContext;
import com.liuli.sql.service.ChainService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.Future;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hui-zhang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/chain")
@Api(tags = "执行链")
public class ChainController {

    @Autowired
    ChainService chainService;

    @Resource
    FlowExecutor flowExecutor;


    @GetMapping("/test")
    @ApiOperation("测试")
    public void test(){
        for (int i = 0; i < 5; i++) {
            CustomDefaultContext context = new CustomDefaultContext();
            context.setData("taskNo", String.valueOf(i));
            Future<LiteflowResponse> future = flowExecutor.execute2Future("test", "arg", context);
        }
    }

}

