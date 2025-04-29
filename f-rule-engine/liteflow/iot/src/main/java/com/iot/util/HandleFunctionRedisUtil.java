package com.iot.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.iot.function.order.OrderDataVerifyFunction;
import com.iot.function.order.OrderDataVerifySwitchFunction;
import com.iot.function.order.OrderJudgmentFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 处理函数redis工具类
 *
 * @author zhanghui
 * @date 2023/09/12 14:41:56
 */
@Component
public class HandleFunctionRedisUtil {

    @Autowired
    StringRedisTemplate redisTemplate;

    // 用于存储锁定键和与之关联的链键信息的映射。
    /**
     * redis钥匙
     */
    private static Map<String, List<String>> redisKeys = new ConcurrentHashMap<>();

    /**
     * 获取锁
     *
     * @param lockKey             锁键
     * @param chainKey            链键
     * @param lockAcquireFunction 锁定获取功能
     * @param action              动作
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/09/12 14:41:58
     * @see String
     * @see String
     * @see Function<String, Boolean>
     * @see Consumer<String>
     * @see Boolean
     * @since 1.0.0
     */
    public Boolean redisLock(String lockKey, String chainKey, Function<String, Boolean> lockAcquireFunction, Consumer<String> action) throws Exception {
        Assert.state(StrUtil.isNotBlank(lockKey), "lockKey不能为空");
        Assert.state(lockAcquireFunction != null, "lockAcquireFunction不能为空");
        Assert.state(action != null, "action不能为空");
        while (true) {
            Boolean lockAcquired = lockAcquireFunction.apply(lockKey);
            try {
                if (lockAcquired) {
                    action.accept(lockKey);
                    if (StrUtil.isNotBlank(chainKey)) {
                        redisKeys.computeIfAbsent(chainKey, s -> Lists.newArrayList()).add(lockKey);
                    }
                    return true;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                // 处理异常情况
                throw e;
            }
        }
    }

    //因为redisKeys是string,list<String> 所以这个function是 <String,List<String>>
//    default V computeIfAbsent(K key,
//                              Function<? super K, ? extends V> mappingFunction) {
//        Objects.requireNonNull(mappingFunction);
//        V v;
          //第一次初始化的时候,他是null值所以去创建一个新的值
//        if ((v = get(key)) == null) {
//            V newValue;
            //如果(newValue = mappingFunction.apply(key))是等同于Lists.newArrayList()
//            if ((newValue = mappingFunction.apply(key)) != null) {
//                put(key, newValue);
//                return newValue;
//            }
//        }
//
//        return v;
//    }

    /**
     * 释放锁
     *
     * @param lockKey             锁键
     * @param chainKey            链键
     * @param lockAcquireFunction 锁定获取功能
     * @param action              动作
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/09/12 14:41:58
     * @see String
     * @see String
     * @see Function<String, Boolean>
     * @see Consumer<String>
     * @see Boolean
     * @since 1.0.0
     */
    public Boolean releaseRedisLock(String lockKey, String chainKey, Function<String, Boolean> lockAcquireFunction, Consumer<String> action) throws Exception {
        Assert.state(StrUtil.isNotBlank(lockKey), "lockKey不能为空");
        Assert.state(lockAcquireFunction != null, "lockAcquireFunction不能为空");
        Assert.state(action != null, "action不能为空");
        while (true) {
            Boolean lockAcquired = lockAcquireFunction.apply(lockKey);
            try {
                if (lockAcquired) {
                    action.accept(lockKey);
                    if (StrUtil.isNotBlank(chainKey)) {
                        List<String> lockKeys = redisKeys.get(chainKey);
                        if (lockKeys != null && lockKeys.contains(lockKey)) {
                            // 如果锁键存在于链键列表中，则移除它
                            lockKeys.remove(lockKey);

                            // 可选：如果列表为空，从映射中删除该条目
                            if (lockKeys.isEmpty()) {
                                redisKeys.remove(chainKey);
                            }
                        }
                    }
                    return true;
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                // 处理异常情况
                throw e;
            }
        }
    }


    /**
     * 订单布尔型功能
     *
     * @param redisKey         redis钥匙
     * @param judgmentFunction 判断函数
     * @param action           动作
     * @return {@code Boolean }
     * @throws Exception 例外情况
     * @author zhanghui
     * @date 2023/09/15 08:33:46
     * @see String
     * @see Function<String, Boolean>
     * @see Function<String, Boolean>
     * @see Boolean
     * @since 1.0.0
     */
    public Boolean orderBooleanFunction(String redisKey, Function<String, Boolean> judgmentFunction, Function<String, Boolean> action) throws Exception {
        Assert.state(StrUtil.isNotBlank(redisKey), "lockKey不能为空");
        Assert.state(judgmentFunction != null, "lockAcquireFunction不能为空");
        Assert.state(action != null, "action不能为空");
        while (true) {
            //第一次调用
            Boolean lockAcquired = judgmentFunction.apply(redisKey);
            try {
                if (lockAcquired) {
                    if (action instanceof OrderDataVerifyFunction) {
                        OrderJudgmentFunction callableFunction = (OrderJudgmentFunction) judgmentFunction;
                        ((OrderDataVerifyFunction) action).setRedisObject(callableFunction.getRedisObject());
                    }
                    return action.apply(redisKey);
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                throw e;
            }
        }
    }


    /**
     * 订单返回字符串功能
     *
     * @param key              钥匙
     * @param judgmentFunction 判断函数
     * @param action           动作
     * @return {@code String }
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/09/15 08:32:21
     * @see String
     * @see Function<String, Boolean>
     * @see Function<String, String>
     * @see String
     * @since 1.0.0
     */
    public String orderStringFunction(String key, Function<String, Boolean> judgmentFunction, Function<String, String> action) throws InterruptedException {
        Assert.state(StrUtil.isNotBlank(key), "key不能为空");
        Assert.state(judgmentFunction != null, "lockAcquireFunction不能为空");
        Assert.state(action != null, "action不能为空");
        while (true) {
            //第一次调用
            Boolean lockAcquired = judgmentFunction.apply(key);
            try {
                if (lockAcquired) {
                    if (action instanceof OrderDataVerifySwitchFunction) {
                        OrderJudgmentFunction callableFunction = (OrderJudgmentFunction) judgmentFunction;
                        ((OrderDataVerifySwitchFunction) action).setRedisObject(callableFunction.getRedisObject());
                    }
                    return action.apply(key);
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    public Boolean releaseRedisLockByChainId(String chainId, String deviceCode) throws Exception{
        List<String> newKeyList = Lists.newArrayList();
        try {
            //获取chain的所有keysList
            List<String> keyList = redisKeys.get(chainId);
            //循环判断key是否包含设备编码
            for (String key : keyList) {
                if (key.contains(deviceCode)) {
                    keyList.remove(key);
                    newKeyList.add(key);
                }
            }
            if (keyList.isEmpty()) {
                redisKeys.remove(chainId);
            }
            if (CollectionUtil.isNotEmpty(newKeyList)) {
                redisTemplate.delete(newKeyList);
            }
        } catch (Exception e) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                List<String> parse=new ArrayList<>(newKeyList);
                if (redisTemplate.hasKey(ERROR_KEY)){
                    String keys = redisTemplate.opsForValue().get(ERROR_KEY);
                    parse = JSON.parseObject(keys, List.class);
                    parse.addAll(newKeyList);
                }
                redisTemplate.opsForValue().set(ERROR_KEY, JSON.toJSONString(parse));
            });
            return false;
        }
        return true;
    }

    public static final String ERROR_KEY = "liteflow-error-key";
}
