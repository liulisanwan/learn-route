package com.iot.function.log;

import com.iot.function.CommonBean;

import java.util.function.Consumer;

/**
 * 节点解锁消费者
 *
 * @author zhanghui
 * @date 2023/09/14 14:45:42
 */
public class NodeUnLockConsumer extends CommonBean implements Consumer<String> {

    @Override
    public void accept(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}
