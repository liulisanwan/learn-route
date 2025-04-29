package com.iot.function.order;

import com.alibaba.fastjson.JSONObject;
import com.iot.function.CommonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.function.Function;


/**
 * 订单判断功能
 *
 * @author zhanghui
 * @date 2023/09/14 14:45:29
 */
@Slf4j
public class OrderJudgmentFunction extends CommonBean implements Function<String, Boolean> {

    /**
     * redis对象
     */
    private JSONObject redisObject;

    /**
     * 应用
     *
     * @param dataKey 数据密钥
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/09/12 14:41:32
     * @see String
     * @see Boolean
     * @since 1.0.0
     */
    @Override
    public Boolean apply(String dataKey) {
        String redisData = redisTemplate.opsForValue().get(dataKey);
        if (redisData != null) {
            redisObject = JSONObject.parseObject(redisData, JSONObject.class);
//            Boolean time = isTime(redisObject);
//            if (!time){
//                throw new RuntimeException("数据超过有效期");
//            }
            return true;
        }
        return false;
    }

    /**
     * 到达redis对象
     *
     * @return {@code JSONObject }
     * @author zhanghui
     * @date 2023/09/12 14:41:32
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
     * @date 2023/09/12 14:41:32
     * @see JSONObject
     * @since 1.0.0
     */
    public void setRedisObject(JSONObject redisObject) {
        this.redisObject = redisObject;
    }


    /**
     * 就是时间
     *
     * @param jsonObject JSON对象
     * @return {@code Boolean }
     * @author zhanghui
     * @date 2023/09/12 14:41:32
     * @see JSONObject
     * @see Boolean
     * @since 1.0.0
     */
    public Boolean isTime(JSONObject jsonObject) {
        if (ObjectUtils.isEmpty(jsonObject)) {
            log.error("JsonObject为null或者为空");
            return false;
        }
        long ts = jsonObject.getLongValue("ts");
        long timeDifference = System.currentTimeMillis() - ts;
        return timeDifference <= 10000;
    }
}
