package com.iot.function.log;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.iot.function.CommonBean;

import java.util.Map;
import java.util.function.Function;

/**
 * 节点解锁功能
 *
 * @author zhanghui
 * @date 2023/09/14 14:46:11
 */
public class NodeUnLockFunction extends CommonBean implements Function<String, Boolean> {

    /***
     * 订单号
     **/
    private String orderNo;

    /**
     * 构造方法，初始化订单号。
     *
     * @param orderNo 订单号
     */
    public NodeUnLockFunction(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 尝试获取锁并判断是否应该释放锁。
     *
     * @param lockKey 锁定的键
     * @return 是否应该释放锁
     */
    @Override
    public Boolean apply(String lockKey) {
        // 获取锁信息
        Map<String, Object> lockInfo = getLockInfo(lockKey);

        // 判断是否应该释放锁
        return shouldReleaseLock(lockInfo, orderNo);
    }

    /**
     * 根据锁信息判断是否应该释放锁。
     *
     * @param lockInfo 锁信息
     * @param orderNo  订单号
     * @return 是否应该释放锁
     */
    private boolean shouldReleaseLock(Map<String, Object> lockInfo, String orderNo) {
        // 如果锁不为空   且  包含订单号且订单号匹配，则应该释放锁   或者锁信为空也应该释放锁。
        return (CollectionUtil.isNotEmpty(lockInfo) && lockInfo.containsKey("orderNo") && lockInfo.get("orderNo").equals(orderNo))
                || CollectionUtil.isEmpty(lockInfo);
    }

    /**
     * 获取锁信息。
     *
     * @param lockKey 锁定的键
     * @return 锁信息
     */
    public Map<String, Object> getLockInfo(String lockKey) {
        // 从Redis中获取锁信息的JSON字符串并解析为Map
        String lockInfoJson = redisTemplate.opsForValue().get(lockKey);
        return JSON.parseObject(lockInfoJson, Map.class);
    }
}
