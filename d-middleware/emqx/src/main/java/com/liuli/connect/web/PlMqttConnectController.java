package com.liuli.connect.web;


import com.liuli.entity.MqttConnectEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * mqtt连接表 前端控制器
 * </p>
 *
 * @author hui-zhang
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/plMqttConnect")
@Api(tags = "mqtt连接表")
public class PlMqttConnectController {

    @Value("${spring.mqtt.url}")
    String url;

    MqttConnectEntity connect;

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public void test(){
        connect=MqttConnectEntity.mqttConnect("test",url);
    }


    @GetMapping("/close")
    @ApiOperation(value = "关闭连接")
    public void close() throws MqttException {
        connect.close();
        connect=null;
    }
}

