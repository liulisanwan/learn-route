package com.liuli.topic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 主题表
 * </p>
 *
 * @author hui-zhang
 * @since 2023-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PlTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主题id
     */
    private String id;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 主题
     */
    private String topic;

    /**
     * 主题类型
     */
    private String topicType;

    /**
     * 设备主题类型
     */
    private String deviceType;

    /**
     * 状态（0正常;1删除;2停用 3冻结）
     */
    private String status;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 租户代码
     */
    private String corpCode;

    /**
     * 租户名称
     */
    private String corpName;


}
