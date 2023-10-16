package com.liuli.sql.service.impl;

import com.liuli.sql.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * xs发送服务实现类
 *
 * @author zhanghui
 * @date 2023/08/16 13:15:14
 */
@Service
public class XsSendServiceImpl implements SendService {

    @Autowired
    private LcSendServiceImpl lcSendService;
    @Override
    public boolean sendMessage(String deviceName, String deviceCode, String attributeName, Object attributeValue) {
        lcSendService.sendMessage(deviceName, deviceCode, attributeName, attributeValue);
        System.err.println("xs发送成功");
        return false;
    }
}
