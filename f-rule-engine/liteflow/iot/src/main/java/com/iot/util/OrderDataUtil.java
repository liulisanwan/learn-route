package com.iot.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 订单数据工具类
 *
 * @author hanxiaowei
 * @date 2023/9/15 11:33
 */
@Slf4j
public class OrderDataUtil {
    /**
     * 检查类型
     *
     * @param o             redis key 对应的value
     * @param attributeType 数据库中的 字段值
     * @return boolean
     * @author zhanghui
     * @date 2023/09/12 14:41:16
     * @see Object
     * @see String
     * @see boolean
     * @since 1.0.0
     */
    public  static boolean checkType(Object o, String attributeType) {
        switch (attributeType) {
            case "Long":
                long l = Long.parseLong(o.toString());
                return true;
            case "Double":
                Double.parseDouble(o.toString());
                return true;
            case "Boolean":
                Boolean.parseBoolean(o.toString());
                return true;
            case "String":
                String string = o.toString();
                return true;
            default:
                log.error("没有一种数据类型");
                return false;
        }
    }

    /**
     * 检查常量
     *
     * @param jsonData
     * @param constantList
     * @author zhanghui
     * @date 2023/09/12 14:41:16
     * @see JSONObject
     * @see List < PlConstant >
     * @since 1.0.0
     */
    public static void checkConstant(JSONObject jsonData, List<PlConstant> constantList) {
        if (CollectionUtil.isNotEmpty(jsonData) && !CollectionUtils.isEmpty(constantList)) {
            for (PlConstant plConstant : constantList) {
                if (null != jsonData.get(plConstant.getConstantName())) {
                    plConstant.setConstantValue(jsonData.get(plConstant.getConstantName()).toString());
                }
            }
        }
    }

    /**
     * @Author hanxiaowei
     * @Description 检查redis和数据库中的数据
     * @Date 11:42 2023/9/15
     * @Param
     * @param jsonData
     * @param list
     * @return
     * @return java.lang.Integer
     **/
    public static Integer checkRedisData(JSONObject jsonData,List<PlListen> list) {
        Integer resultCount = 0;
        for (PlListen plListen : list) {
            String attributeName = plListen.getAttributeName();
            Object o = jsonData.get(attributeName);
            boolean equals = StrUtil.isNotBlank(plListen.getIgnoreCheck())&&plListen.getIgnoreCheck().equals("1")?
                    true:o.toString().equals(plListen.getAttributeValue());
            boolean checkType = OrderDataUtil.checkType(o, plListen.getAttributeType());
            if (o != null && checkType && equals) {
                resultCount++;
            } else {
                throw new RuntimeException(plListen.getAttributeValue()+"redis中的数据和数据库中的数据不一致");
            }
        }
        ignore:
        return resultCount;
    }
}
