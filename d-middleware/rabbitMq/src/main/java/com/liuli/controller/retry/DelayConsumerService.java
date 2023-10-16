package com.liuli.controller.retry;


import com.liuli.constant.OrderInfo;
import com.liuli.dynamic.service.impl.AbsConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DelayConsumerService extends AbsConsumerService<OrderInfo> {
    @Override
    public void onConsumer(OrderInfo data) throws Exception {
      log.info("延迟消费消息: {}", data);
      int a=1/0;
    }
}
