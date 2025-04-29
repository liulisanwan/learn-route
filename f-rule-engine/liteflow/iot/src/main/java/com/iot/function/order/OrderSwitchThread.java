package com.iot.function.order;

import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.util.HandleFunctionRedisUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class OrderSwitchThread implements Callable<String> {

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
    private Map<String, List<PlListen>> map;
    /**
     * 常量列表
     */
    private List<PlConstant> constantList;

    public OrderSwitchThread(HandleFunctionRedisUtil handleFunctionRedisUtil, String key, Map<String, List<PlListen>> map, List<PlConstant> constantList) {
        this.handleFunctionRedisUtil = handleFunctionRedisUtil;
        this.key = key;
        this.map = map;
        this.constantList = constantList;
    }

    @Override
    public String call() throws Exception {
        OrderJudgmentFunction lockAcquireFunction = new OrderJudgmentFunction();
        String result = handleFunctionRedisUtil.orderStringFunction(key, lockAcquireFunction, new OrderDataVerifySwitchFunction(map, constantList));
        return result;
    }
}
