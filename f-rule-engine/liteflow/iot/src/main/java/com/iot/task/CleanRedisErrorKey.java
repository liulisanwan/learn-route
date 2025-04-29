package com.iot.task;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.iot.util.HandleFunctionRedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 清洁redis错误关键
 *
 * @author zhanghui
 * @date 2023/10/18 14:11:20
 */
@EnableScheduling
@Component
public class CleanRedisErrorKey {

    @Autowired
    StringRedisTemplate redisTemplate;


    @Scheduled(cron = "0/20 * * * * ?")
    public void cleanRedisErrorKey(){
        //TODO 定时清理redis中的错误key
        String keys = redisTemplate.opsForValue().get(HandleFunctionRedisUtil.ERROR_KEY);
        List<String> parse = JSON.parseObject(keys, List.class);
        if (CollectionUtil.isNotEmpty(parse)) {
            Integer count=0;
            for (String key : parse) {
                redisTemplate.delete(key);
                if (redisTemplate.hasKey(key)) {
                    count++;
                }
            }
            if (count==0) {
                redisTemplate.delete(HandleFunctionRedisUtil.ERROR_KEY);
            }
        }

    }
}
