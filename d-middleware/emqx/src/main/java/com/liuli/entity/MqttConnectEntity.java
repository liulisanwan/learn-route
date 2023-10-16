package com.liuli.entity;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * mqtt连接实体
 *
 * @author zhanghui
 * @date 2023/08/23 09:21:18
 */
@Slf4j
public class MqttConnectEntity {


    private MqttClient mqttClient;



    public static MqttConnectEntity mqttConnect(String topic, String url) {
        try {
            //创建mqtt客户端
            MqttConnectEntity mqttConnectEntity = new MqttConnectEntity();
            //设置客户端id
            String clientId = RandomUtil.randomString(12);
            //设置连接的url，客户端id，用户名，密码，下线是否清除标识，连接成功后的callback对象
            mqttConnectEntity.setMqttClient(url, clientId, "chlrob", "admin", true, new MqttCallBackEntity(topic,mqttConnectEntity),topic);
            //放入客户端id跟连接对象的map集合
            log.info("{}--连接成功！！订阅主题--{}", clientId,topic);
            return mqttConnectEntity;
        } catch (Exception e) {
            log.error("连接设备mqtt失败", e);
        }
        return null;
    }

    public void setMqttClient(String host, String clientId, String userName, String password, boolean cleanSession, MqttCallBackEntity mqttCallback, String topic) throws MqttException {
        //配置连接参数
        MqttConnectOptions options = mqttConnectOptions(host, clientId, userName, password, cleanSession);
        //配置连接成功后的callback对象
        if (mqttCallback == null) {
            mqttClient.setCallback(new MqttCallBackEntity(topic, this));
        } else {
            mqttClient.setCallback(mqttCallback);
        }
        //执行连接
        mqttClient.connect(options);
    }

    /**
     * MQTT连接参数设置
     *
     * @param host         宿主
     * @param clientId     客户端id
     * @param userName     用户名
     * @param password     密码
     * @param cleanSession 清除会话
     * @return {@code MqttConnectOptions }
     * @throws MqttException mqtt例外
     * @author zhanghui
     * @date 2023/04/11 14:12:56
     * @see String
     * @see String
     * @see String
     * @see String
     * @see boolean
     * @see MqttConnectOptions
     * @since 1.0.0
     */
    private MqttConnectOptions mqttConnectOptions(String host, String clientId, String userName, String password, boolean cleanSession) throws MqttException {
        mqttClient = new MqttClient(host, clientId, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(60);///默认：30
        options.setAutomaticReconnect(true);//默认：false
        options.setCleanSession(cleanSession);//默认：true
        options.setKeepAliveInterval(60);
        return options;
    }

    /**
     * 关闭MQTT连接
     *
     * @throws MqttException mqtt例外
     * @author zhanghui
     * @date 2023/04/11 14:12:32
     * @since 1.0.0
     */
    public void close() throws MqttException {
//        mqttClient.close();
        mqttClient.disconnect();
    }


    public void sub(String topic) throws MqttException {
        mqttClient.subscribe(topic, 0);
    }



    public void pub(String topic, String msg) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }
}
