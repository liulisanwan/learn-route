package com.iot.custom_node;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理设备错误服务
 *
 * @author zhanghui
 * @date 2023/09/19 08:50:03
 */
@Service
@Slf4j
public class HandleDeviceErrorService {

    @Autowired
    StringRedisTemplate redisTemplate;





    public void recordDataToRedis(String deviceCode){
        Map<String,Object> map = new HashMap<>();
        map.put("error","error");
        map.put("time", DateUtil.formatDateTime(DateUtil.date()));
        redisTemplate.opsForValue().set(deviceCode+"error", JSON.toJSONString(map));
    }

    public void recordDataToThird(List<String> orderNoList) {
        //TODO 调用三方接口来返回订单数据
        log.info(JSON.toJSONString(orderNoList));
    }
}
