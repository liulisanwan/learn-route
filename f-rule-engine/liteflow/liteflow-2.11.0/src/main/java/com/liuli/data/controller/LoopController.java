package com.liuli.data.controller;


import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 循环控制器
 *
 * @author zhanghui
 * @date 2023/08/09 17:22:48
 */
@RestController
@Api(tags = "循环")
@RequestMapping("/loop")
public class LoopController {

    @Resource
    private FlowExecutor customFlowExecutor;


    /**
     * loop1 -> 执行链
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:11:21
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop1")
    @ApiOperation("loop1操作")
    public String loop1(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop1", "arg");
        return "success";
    }


    /**
     * loop2
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:26:24
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop2")
    @ApiOperation("loop2操作")
    public String loop2(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop2", "arg");
        return "success";
    }


    /**
     * loop3
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:26:27
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop3")
    @ApiOperation("loop3操作")
    public String loop3(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop3", "arg");
        return "success";
    }


    /**
     * loop4
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:26:29
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop4")
    @ApiOperation("loop4操作")
    public String loop4(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop4", "arg");
        return "success";
    }

    /**
     * loop5
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:26:32
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop5")
    @ApiOperation("loop5操作")
    public String loop5(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop5", "arg");
        return "success";
    }

    /**
     * 学习
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 17:26:34
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/loop6")
    @ApiOperation("loop6操作")
    public String loop6(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("loop6", "arg");
        return "success";
    }
}
