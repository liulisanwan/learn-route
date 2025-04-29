package com.iot.database.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 常量表
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PlConstant对象", description="常量表")
public class PlConstant implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "常量名称")
    private String constantName;

    @ApiModelProperty(value = "常量类型")
    private String constantType;

    @ApiModelProperty(value = "常量value")
    private String constantValue;

    @ApiModelProperty(value = "节点id")
    private String chainId;

    @ApiModelProperty(value = "状态（0正常;1删除;2停用;3冻结）")
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


}
