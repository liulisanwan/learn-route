package com.liuli.dynamic.service;

import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

/**
 * <p> 消费者接口 </p>
 *
 * @author zhanghui
 * @description
 * @date 2023/7/10 15:59
 */
public interface ConsumerService extends ChannelAwareMessageListener {

}
