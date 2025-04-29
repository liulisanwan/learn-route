package com.liuli;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "分布式锁")
public class LockController {

    @Autowired
    private IDistributedLock distributedLock;

    @GetMapping("/lock/{key}")
    @ApiOperation("获取锁")
    public String lock(@PathVariable("key") String key) {
        try {
            // 尝试获取锁，最多等待30秒
            ILock lock = distributedLock.lock(key);
            if (lock != null) {
                try {
                    // 执行业务逻辑
                    // ...
                    return "执行成功";
                } finally {
                    // 释放锁
                    distributedLock.unLock(lock);
                }
            } else {
                return "获取锁失败";
            }
        } catch (Exception e) {
            return "获取锁异常：" + e.getMessage();
        }
    }

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/tryLock2/{key}")
    @ApiOperation("尝试获取锁")
    public String lock2(@PathVariable("key") String key) {
        redissonClient.getBucket(key).set(key);
        String test = (String) redissonClient.getBucket(key).get();
        System.out.println(test);
        return test;
    }
}
