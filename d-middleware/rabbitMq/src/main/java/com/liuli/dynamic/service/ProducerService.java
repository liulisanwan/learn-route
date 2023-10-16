package com.liuli.dynamic.service;

/**
 * 生产者服务
 * <p> 生产者接口 </p>
 *
 * @author zhanghui
 * @description
 * @date 2023/7/10 15:59
 */
public interface ProducerService {

    /**
     * 发送消息
     *
     * @param message 消息
     * @author zhanghui
     * @date 2023/07/17 10:10:49
     * @see Object
     * @since 1.0.0
     */
    void send(Object message);


    /**
     * 发送延迟
     *
     * @param message   消息
     * @param delayTime 延迟时间
     * @author zhanghui
     * @date 2023/07/17 10:10:50
     * @see Object
     * @see long
     * @since 1.0.0
     */
    void sendDelay(Object message, Integer delayTime);

}
