package com.liuli.util;

import org.springframework.stereotype.Component;

/**
 * 发送工具类
 *
 * @author zhanghui
 * @date 2023/08/16 13:41:51
 */
@Component
public class SendUtil {


    public static boolean sendMessage(String deviceName, String deviceCode, String attributeName, Object attributeValue) {
        System.err.println("lc:"+deviceName + " " + deviceCode + " " + attributeName + " " + attributeValue);
        return false;
    }
}
