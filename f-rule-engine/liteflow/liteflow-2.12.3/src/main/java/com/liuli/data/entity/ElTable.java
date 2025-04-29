package com.liuli.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangHui
 * @since 2024-09-23
 */
@TableName("el_table")
@ApiModel(value = "ElTable对象", description = "")
public class ElTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("application_name")
    private String applicationName;

    @TableField("chain_name")
    private String chainName;

    @TableField("el_data")
    private String elData;

    @TableField("route")
    private String route;

    @TableField("namespace")
    private String namespace;

    @TableField("enable")
    private Boolean enable;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getElData() {
        return elData;
    }

    public void setElData(String elData) {
        this.elData = elData;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ElTable{" +
        "id=" + id +
        ", applicationName=" + applicationName +
        ", chainName=" + chainName +
        ", elData=" + elData +
        ", route=" + route +
        ", namespace=" + namespace +
        ", enable=" + enable +
        "}";
    }
}
