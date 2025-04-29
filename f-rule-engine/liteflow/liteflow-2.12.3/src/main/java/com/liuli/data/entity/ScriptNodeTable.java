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
@TableName("script_node_table")
@ApiModel(value = "ScriptNodeTable对象", description = "")
public class ScriptNodeTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("application_name")
    private String applicationName;

    @TableField("script_node_id")
    private String scriptNodeId;

    @TableField("script_node_name")
    private String scriptNodeName;

    @TableField("script_node_type")
    private String scriptNodeType;

    @TableField("script_node_data")
    private String scriptNodeData;

    @TableField("script_language")
    private String scriptLanguage;

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

    public String getScriptNodeId() {
        return scriptNodeId;
    }

    public void setScriptNodeId(String scriptNodeId) {
        this.scriptNodeId = scriptNodeId;
    }

    public String getScriptNodeName() {
        return scriptNodeName;
    }

    public void setScriptNodeName(String scriptNodeName) {
        this.scriptNodeName = scriptNodeName;
    }

    public String getScriptNodeType() {
        return scriptNodeType;
    }

    public void setScriptNodeType(String scriptNodeType) {
        this.scriptNodeType = scriptNodeType;
    }

    public String getScriptNodeData() {
        return scriptNodeData;
    }

    public void setScriptNodeData(String scriptNodeData) {
        this.scriptNodeData = scriptNodeData;
    }

    public String getScriptLanguage() {
        return scriptLanguage;
    }

    public void setScriptLanguage(String scriptLanguage) {
        this.scriptLanguage = scriptLanguage;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ScriptNodeTable{" +
        "id=" + id +
        ", applicationName=" + applicationName +
        ", scriptNodeId=" + scriptNodeId +
        ", scriptNodeName=" + scriptNodeName +
        ", scriptNodeType=" + scriptNodeType +
        ", scriptNodeData=" + scriptNodeData +
        ", scriptLanguage=" + scriptLanguage +
        ", enable=" + enable +
        "}";
    }
}
