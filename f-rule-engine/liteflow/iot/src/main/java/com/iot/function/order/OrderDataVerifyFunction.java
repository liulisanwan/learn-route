package com.iot.function.order;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.function.CommonBean;
import com.iot.util.OrderDataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;


/**
 * 订单数据验证功能
 *
 * @author zhanghui
 * @date 2023/09/14 14:45:10
 */
@Slf4j
public class OrderDataVerifyFunction extends CommonBean implements Function<String,Boolean> {

    /**
     * 列表
     */
    private List<PlListen> list;
    /**
     * 常量列表
     */
    private List<PlConstant> constantList;
    /**
     * redis对象
     */
    private JSONObject redisObject;


    /**
     * 应用
     *
     * @param lockKey 锁键
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/09/12 14:41:17
     * @see String
     * @see Boolean
     * @since 1.0.0
     */
    @Override
    public Boolean apply(String lockKey) {
        Assert.state(StrUtil.isNotBlank(lockKey), "lockKey不能为空");
        //第二次调用
        OrderJudgmentFunction function = new OrderJudgmentFunction();
        Boolean apply = function.apply(lockKey);
        if (apply){
            if (lockKey.contains("agv")){
//                redisTemplate.delete(lockKey);  todo 暂时测试 所以 临时注掉
            }
            JSONObject functionRedisObject = function.getRedisObject();
            if (redisObject.equals(functionRedisObject)){
                String stringData = functionRedisObject.get("data").toString();
                JSONObject jsonData = JSON.parseObject(stringData, JSONObject.class);
                Integer resultCount = OrderDataUtil.checkRedisData(jsonData,list);
                if (resultCount == list.size()) {
                    log.info("数据匹配成功");
                    if (!CollectionUtils.isEmpty(constantList)){
                        //如果匹配一致需要将数据改到上下文对象中
                        OrderDataUtil.checkConstant(jsonData,constantList);
                        return true;
                    }
                    return true;
                } else {
                    log.error("数据匹配失败");
                    return false;
                }
            }
        }else {
            throw new RuntimeException("数据失效");
        }
        return false;
    }



    /**
     * 订单数据验证功能
     *
     * @param list         列表
     * @param constantList 常量列表
     * @return {@code  }
     * @author zhanghui
     * @date 2023/09/14 15:53:27
     * @see List<PlListen>
     * @see List<PlConstant>
     * @since 1.0.0
     */
    public OrderDataVerifyFunction(List<PlListen> list, List<PlConstant> constantList) {
        this.list = list;
        this.constantList = constantList;

    }

    /**
     * 获取列表
     *
     * @return {@code List<PlListen> }
     * @author zhanghui
     * @date 2023/09/12 14:41:14
     * @see List<PlListen>
     * @since 1.0.0
     */
    public List<PlListen> getList() {
        return list;
    }

    /**
     * 集合列表
     *
     * @param list 列表
     * @author zhanghui
     * @date 2023/09/12 14:41:14
     * @see List<PlListen>
     * @since 1.0.0
     */
    public void setList(List<PlListen> list) {
        this.list = list;
    }

    /**
     * 获取常量列表
     *
     * @return {@code List<PlConstant> }
     * @author zhanghui
     * @date 2023/09/12 14:41:14
     * @see List<PlConstant>
     * @since 1.0.0
     */
    public List<PlConstant> getConstantList() {
        return constantList;
    }

    /**
     * 设置常量列表
     *
     * @param constantList 常量列表
     * @author zhanghui
     * @date 2023/09/12 14:41:14
     * @see List<PlConstant>
     * @since 1.0.0
     */
    public void setConstantList(List<PlConstant> constantList) {
        this.constantList = constantList;
    }

    /**
     * 到达redis对象
     *
     * @return {@code JSONObject }
     * @author zhanghui
     * @date 2023/09/12 14:41:15
     * @see JSONObject
     * @since 1.0.0
     */
    public JSONObject getRedisObject() {
        return redisObject;
    }

    /**
     * 集redis对象
     *
     * @param redisObject redis对象
     * @author zhanghui
     * @date 2023/09/12 14:41:16
     * @see JSONObject
     * @since 1.0.0
     */
    public void setRedisObject(JSONObject redisObject) {
        this.redisObject = redisObject;
    }


}
