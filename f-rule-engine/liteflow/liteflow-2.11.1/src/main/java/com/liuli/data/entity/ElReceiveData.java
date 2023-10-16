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

    private ElType type;

    private String NodeId;

    private String id;

    private String tag;

    private String trueNodeId;

    private String falseNodeId;

    private List<ElReceiveData> childrenList;






}
