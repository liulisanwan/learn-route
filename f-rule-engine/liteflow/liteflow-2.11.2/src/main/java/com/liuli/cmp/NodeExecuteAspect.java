package com.liuli.cmp;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.yomahub.liteflow.aop.ICmpAroundAspect;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class NodeExecuteAspect implements ICmpAroundAspect {

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void beforeProcess(NodeComponent component) {
        CustomDefaultContext context = component.getFirstContextBean();
        String lockKey = "test";
        String lockKey2 = "test2";
        String taskNo = context.getData("taskNo");
        while (true) {
            // 使用 Redisson 锁保证原子性
            RLock lock = redissonClient.getLock(lockKey);
            try {
                if (lock.tryLock(-1, TimeUnit.SECONDS)) {
                    // 获取锁成功后，检查 Redis 中的锁信息
                    String lockInfoJson = redisTemplate.opsForValue().get(lockKey2);

                    if (StrUtil.isBlank(lockInfoJson)) {
                        // 锁信息不存在，表示锁未被占用，设置锁信息并继续执行
                        Map<String, Object> lockInfo = new HashMap<>();
                        lockInfo.put("occupation", 1);
                        lockInfo.put("taskNo", taskNo);
                        redisTemplate.opsForValue().set(lockKey2, JSON.toJSONString(lockInfo));
                        log.info("任务编号 {} 获取锁成功", taskNo);
                        context.setData("lock", lock);
                        break;
                    } else {
                        // 锁信息存在，解析并检查 occupation 字段
                        Map<String, Object> lockInfo = JSON.parseObject(lockInfoJson, Map.class);
                        if (lockInfo.get("occupation").equals(0)) {
                            // 锁未被占用，设置锁信息并继续执行
                            lockInfo.put("occupation", 1);
                            lockInfo.put("taskNo", taskNo);
                            redisTemplate.opsForValue().set(lockKey2, JSON.toJSONString(lockInfo));
                            log.info("任务编号 {} 获取锁成功", taskNo);
                            context.setData("lock", lock);
                            break;
                        } else {
                            // 锁已被占用，释放 Redisson 锁并处理失败情况
                            log.info("任务编号 {} 获取锁失败", taskNo);
                            lock.unlock();
                            // 处理获取锁失败的情况 (例如抛出异常、重试等)
                        }
                    }
                } else {
                    log.info("任务编号 {} 正在等待中,1s后重试",taskNo);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                log.error("获取或使用锁时出错:", e);
                // 异常处理
            }
        }

    }

    @Override
    public void afterProcess(NodeComponent component) {

    }

    @Override
    public void onSuccess(NodeComponent component) {
//        CustomDefaultContext context = component.getFirstContextBean();
//        RLock lock = context.getData("lock");
//        lock.unlock();
        redisTemplate.delete("test");
        redisTemplate.delete("test2");
        log.info("执行成功");
    }

    @Override
    public void onError(NodeComponent component, Exception e) {
//        CustomDefaultContext context = component.getFirstContextBean();
//        RLock lock = context.getData("lock");
//        lock.unlock();
        redisTemplate.delete("test");
        redisTemplate.delete("test2");
        log.error("执行失败: {}", e.getMessage());
    }
}