package com.liuli.sql.service.impl;

import com.liuli.sql.service.SendService;
import org.springframework.stereotype.Service;

/**
 * lc发送服务实现类
 *
 * @author zhanghui
 * @date 2023/08/16 13:15:36
 */
@Service
public class LcSendServiceImpl implements SendService {

    @Override
    public boolean sendMessage(String deviceName, String deviceCode, String attributeName, Object attributeValue) {
        System.err.println("lc:"+deviceName + " " + deviceCode + " " + attributeName + " " + attributeValue);
        return false;
    }
}
