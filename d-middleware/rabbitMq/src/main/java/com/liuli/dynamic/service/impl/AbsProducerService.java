package com.liuli.dynamic.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.liuli.dynamic.service.ProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * <p> 生产者实现类 </p>
 *
 * @author zhanghui
 * @description
 * @date 2023/7/10 15:36
 */
@Slf4j
public class AbsProducerService implements ProducerService {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 交换机
     */
    private String exchange;
    /**
     * 路由
     */
    private String routingKey;

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    /**
     * 发送消息
     *
     * @param msg 消息体
     * @author zhanghui
     * @date 2023/08/08 17:10:53
     * @see Object
     * @since 1.0.0
     */
    @Override
    public void send(Object msg) {
        MessagePostProcessor messagePostProcessor = (message) -> {
            //设置消息属性
            MessageProperties messageProperties = message.getMessageProperties();
            //设置消息id
            messageProperties.setMessageId(IdUtil.randomUUID());
            //设置消息时间戳
            messageProperties.setTimestamp(new Date());
            return message;
        };
        MessageProperties messageProperties = new MessageProperties();
        //设置编码
        messageProperties.setContentEncoding("UTF-8");
        //设置消息类型
        messageProperties.setContentType("text/plain");
        //转换消息为json字符串
        String data = JSONUtil.toJsonStr(msg);
        //创建消息
        Message message = new Message(data.getBytes(StandardCharsets.UTF_8), messageProperties);
        //发送消息到指定交换机和路由并设置相应的消息后置处理器
        rabbitTemplate.convertAndSend(this.exchange, this.routingKey, message, messagePostProcessor);
    }

    /**
     * 发送延迟消息
     *
     * @param msg       消息体
     * @param delayTime 延迟时间
     * @author zhanghui
     * @date 2023/08/08 17:10:45
     * @see Object
     * @see Integer
     * @since 1.0.0
     */
    @Override
    public void sendDelay(Object msg, Integer delayTime) {
        //同上
        MessagePostProcessor messagePostProcessor = (message) -> {
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setMessageId(IdUtil.randomUUID());
            messageProperties.setTimestamp(new Date());
            //设置延迟时间
            messageProperties.setDelay(delayTime);
            return message;
        };
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentEncoding("UTF-8");
        messageProperties.setContentType("text/plain");
        String data = JSONUtil.toJsonStr(msg);
        Message message = new Message(data.getBytes(StandardCharsets.UTF_8), messageProperties);
        rabbitTemplate.convertAndSend(this.exchange, this.routingKey, message, messagePostProcessor);
    }


}




