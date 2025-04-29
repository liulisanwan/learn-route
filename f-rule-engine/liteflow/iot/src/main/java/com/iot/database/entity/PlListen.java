package com.iot.database.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 监听表
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PlListen对象", description = "监听表")
public class PlListen implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private String id;

    @ApiModelProperty(value = "监听属性名称")
    private String attributeName;

    @ApiModelProperty(value = "监听属性类型")
    private String attributeType;

    @ApiModelProperty(value = "监听属性值")
    private String attributeValue;

    @ApiModelProperty(value = "节点id")
    private String nodeId;

    @ApiModelProperty(value = "选择节点id")
    private String switchNodeId;

    @ApiModelProperty(value = "是否忽略检查")
    private String ignoreCheck;

    @ApiModelProperty(value = "状态（0正常;1删除 2停用 3冻结）")
    private String status;

    @ApiModelProperty(value = "创建者")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "更新者")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "租户代码")
    private String corpCode;

    @ApiModelProperty(value = "租户名称")
    private String corpName;


    @ApiModelProperty(value = "别名id")
    @TableField(exist = false)
    private String aliasId;


}
