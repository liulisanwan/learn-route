package com.iot.function.log;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.iot.function.CommonBean;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.function.Function;

/**
 * 节点锁定功能
 *
 * @author zhanghui
 * @date 2023/09/14 14:45:39
 */
@Slf4j
public class NodeLockFunction extends CommonBean implements Function<String, Boolean> {

    private String orderNo;


    public NodeLockFunction( String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @param lockKey
     * @return java.lang.Boolean
     * @Author hanxiaowei
     * @Description
     * @Date 11:56 2023/9/11
     * @Param
     **/
    @Override
    public Boolean apply(String lockKey) {

        String lockInfoJson = redisTemplate.opsForValue().get(lockKey);
        if (StrUtil.isBlank(lockInfoJson) || JSON.parseObject(lockInfoJson, Map.class).get("occupation").equals(0)) {
            return true;
        } else {
            return false;
        }


    }


}
