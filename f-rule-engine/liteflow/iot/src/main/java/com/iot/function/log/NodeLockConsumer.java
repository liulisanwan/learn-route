package com.iot.function.log;

import com.alibaba.fastjson.JSON;
import com.iot.function.CommonBean;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 节点加锁消费者
 *
 * @author zhanghui
 * @date 2023/09/14 14:45:35
 */
public class NodeLockConsumer extends CommonBean implements Consumer<String> {

    private String orderNo;


    public NodeLockConsumer(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @param lockKey
     * @return
     * @Author hanxiaowei
     * @Description 获取锁成功后的要加锁
     * @Date 11:40 2023/9/11
     * @Param
     **/
    @Override
    public void accept(String lockKey) {
        Map<String, Object> map = new HashMap<>();
        map.put("occupation", 1);
        map.put("orderNo", orderNo);
        setLockInfo(lockKey,map);
    }
    /**
     * @Author hanxiaowei
     * @Description 设置锁信息
     * @Date 13:47 2023/9/11
     * @Param
     * @param lockKey
     * @param lockInfo
     * @return
     **/
    private void setLockInfo(String lockKey, Map<String, Object> lockInfo) {
        redisTemplate.opsForValue().set(lockKey, JSON.toJSONString(lockInfo));
    }
}
