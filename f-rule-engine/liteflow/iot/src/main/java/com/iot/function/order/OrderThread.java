package com.iot.function.order;

import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.util.HandleFunctionRedisUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * 订单线程
 *
 * @author zhanghui
 * @date 2023/09/14 14:39:31
 */
@Slf4j
public class OrderThread implements Callable<Boolean> {

    /**
     * redis锁工具类
     */
    private HandleFunctionRedisUtil handleFunctionRedisUtil;
    /**
     * 钥匙
     */
    private String key;
    /**
     * 列表
     */
    private List<PlListen> list;
    /**
     * 常量列表
     */
    private  List<PlConstant> constantList;


    /**
     * redis可召回
     *
     * @param key           钥匙
     * @param list          列表
     * @param constantList  常量列表
     * @param handleFunctionRedisUtil redis锁工具类
     * @return {@code  }
     * @author zhanghui
     * @date 2023/09/12 14:39:44
     * @see String
     * @see List<PlListen>
     * @see List<PlConstant>
     * @see HandleFunctionRedisUtil
     * @since 1.0.0
     */
    public OrderThread(String key, List<PlListen> list, List<PlConstant> constantList, HandleFunctionRedisUtil handleFunctionRedisUtil) {
        this.key = key;
        this.list = list;
        this.handleFunctionRedisUtil = handleFunctionRedisUtil;
        this.constantList=constantList;
    }

    /**
     * 打电话
     *
     * @return {@code Boolean }
     * @throws Exception 例外情况
     * @author zhanghui
     * @date 2023/09/12 14:39:44
     * @see Boolean
     * @since 1.0.0
     */
    @Override
    public Boolean call() throws Exception{
        OrderJudgmentFunction lockAcquireFunction = new OrderJudgmentFunction();
        Boolean acquired = handleFunctionRedisUtil.orderBooleanFunction(key, lockAcquireFunction, new OrderDataVerifyFunction(list, constantList));
        return acquired;
    }
}
