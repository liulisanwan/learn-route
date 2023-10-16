package com.liuli.dynamic.retry;

import cn.hutool.core.date.DateTime;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * <p> 消息重试失败 -- 业务补偿机制 </p>
 *
 * @author zhanghui
 * @description
 * @date 2022/7/8 10:32
 */
@Slf4j
@Component
public class RetryFailConsumer {

    /**
     * 消息重试失败 -- 重新发布
     */
    public static final String RETRY_EXCHANGE = "retry_exchange";
    public static final String RETRY_FAILURE_KEY = "retry_fail_routing_key";
    public static final String RETRY_FAILURE_QUEUE = "retry_fail_queue";

    /**
     * 重试失败消费者->记录重试后仍然失败的消息以便后期执行补偿策略
     *
     * @param message 消息
     * @param channel 通道
     * @throws Exception 异常
     * @author zhanghui
     * @date 2023/08/08 17:16:37
     * @see Message
     * @see Channel
     * @since 1.0.0
     */
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(value = RETRY_FAILURE_QUEUE, durable = "true"),
                    exchange = @Exchange(value = RETRY_EXCHANGE, type = "direct", durable = "true"),
                    key = RETRY_FAILURE_KEY
            )
    )
    public void retryFailConsumer(Message message, Channel channel) throws Exception {
        log.info("[消息重试失败] 接收时间: {} 接收消息: {}", DateTime.now(), new String(message.getBody(), StandardCharsets.UTF_8));
    }

}
