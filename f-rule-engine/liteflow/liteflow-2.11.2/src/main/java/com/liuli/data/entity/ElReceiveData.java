package com.liuli.data.entity;

import com.yomahub.liteflow.builder.el.ELWrapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * El接收数据
 *
 * @author zhanghui
 * @date 2023/10/16 15:15:03
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElReceiveData {

    /**
     * 类型
     */
    private ElType type;

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
    private List<ElReceiveData> childrenList;






}
