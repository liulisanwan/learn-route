package com.liuli.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 设备配置
 *
 * @author zhanghui
 * @date 2023/08/16 13:12:40
 */
@Data
@ApiModel(value = "设备配置")
public class DeviceConfig {
    /**
     * bean名字
     */
    @ApiModelProperty(value = "bean名字")
    private String beanName;
    /**
     * 设备名称
     */
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    /**
     * 设备状态码
     */
    @ApiModelProperty(value = "设备状态码")
    private String deviceCode;
    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称")
    private String attributeName;
    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值")
    private Object attributeValue;
}
