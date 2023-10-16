package com.liuli.connect.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * mqtt连接表
 * </p>
 *
 * @author hui-zhang
 * @since 2023-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PlMqttConnect implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 租户id
     */
    private String tenantId;

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


}
