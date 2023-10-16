package com.liuli.controller;


import com.liuli.constant.OrderInfo;
import com.liuli.controller.retry.DelayProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 延时控制器
 *
 * @author zhanghui
 * @date 2023/07/17 10:00:53
 */
@RestController
@RequestMapping("/api/mq/delay")
@Api
@Slf4j
@RequiredArgsConstructor
public class DelayController {

    private final DelayProducerService delayProducerService;

    @ApiOperation("发送延时消息")
    @GetMapping("producer")
    public String producer() {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNo("1");
        orderInfo.setOrderName("订单名称");
        this.delayProducerService.sendDelay(orderInfo, 1000);
        return "SUCCESS";
    }
}
