package com.iot.chain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 链构建器树数据
 *
 * @author zhanghui
 * @date 2023/10/18 13:55:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChainBuilderTreeData {
    /**
     * 类型
     */
    private ELWrapperType type;

    /**
     * 节点id列表
     */
    private List<String> nodeIdList;

    /**
     * id
     */
    private String id;

    /**
     * 标签
     */
    private String tag;

    /**
     * 真节点id
     */
    private String trueNodeId;

    /**
     * 假节点id
     */
    private String falseNodeId;

    /**
     * 孩子列表
     */
    private List<ChainBuilderTreeData> childrenList;
}
