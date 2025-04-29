package com.iot.database.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 执行节点表
 * </p>
 *
 * @author zhanghui
 * @since 2023-09-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="PlScriptNode对象", description="执行节点表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlScriptNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "应用名称")
    private String applicationName="demo";

    @ApiModelProperty(value = "节点id")
    private String scriptNodeId;

    @ApiModelProperty(value = "节点名称")
    private String scriptNodeName;

    @ApiModelProperty(value = "节点类型")
    @TableField("script_node_type")
    private String scriptNodeType="script";

    @ApiModelProperty(value = "节点数据")
    private String scriptNodeData;

    @ApiModelProperty(value = "节点语言")
    private String scriptLanguage="java";

    @ApiModelProperty(value = "子产品名称")
    private String subProductName;

    @ApiModelProperty(value = "空闲")
    private String idle;

    @ApiModelProperty(value = "是否释放")
    private String releaseRedis="0";

    @ApiModelProperty(value = "执行链id")
    private String chainId="swms1";

    @ApiModelProperty(value = "别名id")
    private String aliasId;

    @ApiModelProperty(value = "redis中的key")
    private String redisKey;

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
