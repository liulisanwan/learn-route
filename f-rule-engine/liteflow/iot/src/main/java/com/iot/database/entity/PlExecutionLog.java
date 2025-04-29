package com.iot.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 执行日志表
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PlExecutionLog对象", description="执行日志表")
public class PlExecutionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "日志数据")
    private String logData;

    @ApiModelProperty(value = "日志时间")
    private Date logTime;

    @ApiModelProperty(value = "订单信息")
    private String orderInfo;

    @ApiModelProperty(value = "节点id")
    private String nodeId;

    @ApiModelProperty(value = "异常信息")
    private  String exceptionMessage;

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
