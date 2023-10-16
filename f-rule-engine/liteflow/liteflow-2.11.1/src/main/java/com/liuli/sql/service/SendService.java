package com.liuli.sql.service;

/**
 * 发送服务
 *
 * @author zhanghui
 * @date 2023/08/16 13:14:50
 */
public interface SendService {

    /**
     * 发送消息
     *
     * @param deviceName     设备名称
     * @param deviceCode     设备状态码
     * @param attributeName  属性名称
     * @param attributeValue 属性值
     * @return boolean
     * @author zhanghui
     * @date 2023/08/16 13:18:10
     * @see String
     * @see String
     * @see String
     * @see Object
     * @see boolean
     * @since 1.0.0
     */

    boolean sendMessage(String deviceName, String deviceCode, String attributeName, Object attributeValue);
}
