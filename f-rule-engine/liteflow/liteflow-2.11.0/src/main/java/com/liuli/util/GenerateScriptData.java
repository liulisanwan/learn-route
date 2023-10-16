package com.liuli.util;

import cn.hutool.extra.spring.SpringUtil;
import com.liuli.entity.DeviceConfig;
import org.springframework.stereotype.Component;

/**
 * 生成脚本数据
 *
 * @author zhanghui
 * @date 2023/08/16 13:10:11
 */
@Component
public class GenerateScriptData {


    /**
     * 生成脚本数据
     *
     * @param deviceConfig 设备配置
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/16 13:27:44
     * @see DeviceConfig
     * @see String
     * @since 1.0.0
     */

    public String generateScriptData(DeviceConfig deviceConfig) {
        SendUtil sendUtil=SpringUtil.getBean(deviceConfig.getBeanName());
        String allName = sendUtil.getClass().getName();
        String beanName = allName.substring(allName.lastIndexOf(".") + 1);
        StringBuffer sb = new StringBuffer();
        sb.append("import "+allName+"\n");
        sb.append("def f2 = "+beanName+".sendMessage(\""+deviceConfig.getDeviceName()+"\",\""+deviceConfig.getDeviceCode()+"\",\""+deviceConfig.getAttributeName()+"\","+deviceConfig.getAttributeValue()+")\n");
        sb.append("println(f2)\n");
        return sb.toString();
    }


}
