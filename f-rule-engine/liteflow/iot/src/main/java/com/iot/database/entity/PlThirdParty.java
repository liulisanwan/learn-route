package com.iot.database.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 第三方表
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PlThirdParty对象", description="第三方表")
public class PlThirdParty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "交互类型")
    private String interactionType;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "请求类型")
    private String requestType;

    @ApiModelProperty(value = "节点id")
    private String nodeId;

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

    @TableField(exist = false)
    private List<PlThirdPartyData> plThirdPartyDataList;
}
