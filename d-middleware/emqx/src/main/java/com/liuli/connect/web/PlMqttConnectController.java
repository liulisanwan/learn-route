package com.liuli.connect.web;


import com.liuli.entity.MqttConnectEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("sendMessage")
    @ApiOperation("发送消息")
    public void sendMessage(){
        try {
            connect.pub("test","123");
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/saveOnline")
    @ApiOperation("保存上线")
    public void saveOnline(@RequestBody String clientId){
        //{"clientId":${clientId}}
        System.err.println(clientId);
    }


    @PostMapping("removeOffline")
    @ApiOperation("移除离线")
    public void removeOffline(@RequestBody String clientId){
        System.err.println(clientId);
    }
}

