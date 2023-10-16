package com.liuli.entity;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * mqtt打来实体
 *
 * @author zhanghui
 * @date 2023/08/23 09:21:37
 */
@Slf4j
public class MqttCallBackEntity implements MqttCallback, MqttCallbackExtended {



    Map<String,Object> map =new ConcurrentHashMap<>();

    String topic;

    MqttConnectEntity mqttConnectEntity;

    public MqttCallBackEntity(String topic, MqttConnectEntity mqttConnectEntity) {
        this.topic = topic;
        this.mqttConnectEntity = mqttConnectEntity;
    }

    @Override
    public void connectComplete(boolean b, String s) {
        //获取监听主题
        if (!b) {
            log.info("第一次连接成功");
        } else {
            log.info(s + "重连成功");
        }
        try {
            mqttConnectEntity.sub(topic);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
        log.info("订阅主题--{}", topic);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开了MQTT连接", throwable);
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        SymLinkReadEntity readEntity = JSON.parseObject(new String(mqttMessage.getPayload()), SymLinkReadEntity.class);
        map= readEntity.getDatas().stream().collect(Collectors.toMap(SymLinkReadEquipmentParam::getNm, SymLinkReadEquipmentParam::getV));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发布消息成功");
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public MqttConnectEntity getMqttConnectEntity() {
        return mqttConnectEntity;
    }

    public void setMqttConnectEntity(MqttConnectEntity mqttConnectEntity) {
        this.mqttConnectEntity = mqttConnectEntity;
    }
}
