package com.iot.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.iot.chain.ChainBuilderTreeData;
import com.iot.exception.LiteFlowParseException;
import com.yomahub.liteflow.builder.el.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 树数据构建el表达式包装类工具类
 *
 * @author zhanghui
 * @date 2023/10/16 15:28:15
 */
public class TreeDataBuildELWrapperUtil {


    /**
     * 将otree数据转换为el包装器
     *
     * @param treeData 接收数据
     * @return {@code ELWrapper }
     * @author zhanghui
     * @date 2023/10/18 14:01:45
     * @see ChainBuilderTreeData
     * @see ELWrapper
     * @since 1.0.0
     */
    public static ELWrapper convertOTreeDataToELWrapper(ChainBuilderTreeData treeData) {
        List<ELWrapper> childrenWrapper = new ArrayList<>(); // 用于存储子级ELWrapper
        List<ChainBuilderTreeData> childrenList = treeData.getChildrenList(); // 获取子级数据列表
        if (CollectionUtil.isNotEmpty(childrenList)) { // 如果子级数据不为空，进行递归转换
            for (ChainBuilderTreeData data : childrenList) {
                ELWrapper elWrapper = convertOTreeDataToELWrapper(data); // 递归构建子级的 ELWrapper
                childrenWrapper.add(elWrapper);
            }
        }
        ELWrapper elWrapper = getWrapper(treeData, childrenWrapper); // 获取包装好的ELWrapper对象
        return elWrapper;
    }


    /**
     * 得到包装
     *
     * @param treeData     接收数据
     * @param childrenWrapper 儿童包装
     * @return {@code ELWrapper }
     * @author zhanghui
     * @date 2023/10/17 15:47:09
     * @see ChainBuilderTreeData
     * @see List<ELWrapper>
     * @see ELWrapper
     * @since 1.0.0
     */
    private static ELWrapper getWrapper(ChainBuilderTreeData treeData, List<ELWrapper> childrenWrapper) {
        ELWrapper elWrapper; // 要创建的ELWrapper对象
        String id = treeData.getId(); // 从ElReceiveData获取id
        String tag = treeData.getTag(); // 从ElReceiveData获取tag
        String typeName = treeData.getType().getName(); // 从ElReceiveData获取typeName
        List<String> nodeIdList = treeData.getNodeIdList(); // 从ElReceiveData获取nodeIdList

        // 根据typeName字段的值选择相应的ELWrapper类型
        switch (typeName) {
            case "ForELWrapper":
                elWrapper = ELBus.forOpt(nodeIdList.get(0)); // 创建ForELWrapper
                break;
            case "IteratorELWrapper":
                elWrapper = ELBus.iteratorOpt(nodeIdList.get(0)); // 创建IteratorELWrapper
                break;
            case "NodeELWrapper":
                elWrapper = ELBus.node(nodeIdList.get(0)); // 创建NodeELWrapper
                break;
            case "SwitchELWrapper":
                elWrapper = ELBus.switchOpt(nodeIdList.get(0)); // 创建SwitchELWrapper
                break;
            case "WhileELWrapper":
                elWrapper = ELBus.whileOpt(nodeIdList.get(0)); // 创建WhileELWrapper
                break;
            case "CatchELWrapper":
                elWrapper = ELBus.catchException(nodeIdList.get(0)); // 创建CatchELWrapper
                break;
            case "IfELWrapper":
                if(StrUtil.isNotBlank(treeData.getTrueNodeId())){
                    if (StrUtil.isNotBlank(treeData.getFalseNodeId())){
                        elWrapper = ELBus.ifOpt(nodeIdList.get(0), treeData.getTrueNodeId(),treeData.getFalseNodeId());
                    }else {
                        elWrapper = ELBus.ifOpt(nodeIdList.get(0), treeData.getTrueNodeId());
                    }
                }else {
                    throw new RuntimeException("类型为IfELWrapper的trueNodeId不能为空");
                }
                break;
            case "NotELWrapper":
                elWrapper = ELBus.not(nodeIdList.get(0)); // 创建NotELWrapper
                break;
            case "OrELWrapper":
                elWrapper = createOrWrapper(nodeIdList); // 创建OrELWrapper并添加子级
                addChildOrWrappers(elWrapper, childrenWrapper); // 为OrELWrapper添加子级
                break;
            case "AndELWrapper":
                elWrapper = createAndWrapper(nodeIdList); // 创建AndELWrapper并添加子级
                addChildAndWrappers(elWrapper, childrenWrapper); // 为AndELWrapper添加子级
                break;
            case "WhenELWrapper":
                elWrapper = createWhenWrapper(nodeIdList); // 创建WhenELWrapper并添加子级
                addChildWhenWrappers(elWrapper, childrenWrapper); // 为WhenELWrapper添加子级
                break;
            default:
            case "ThenELWrapper":
                elWrapper = createThenWrapper(nodeIdList); // 创建ThenELWrapper并添加子级
                addChildThenWrappers(elWrapper, childrenWrapper); // 为ThenELWrapper添加子级
                break;
        }

        // 如果id不为空，并且typeName不是"NodeELWrapper"，为ELWrapper设置id
        if (StrUtil.isNotBlank(id) && !typeName.equals("NodeELWrapper")) {
            elWrapper.id(id);
        }

        // 如果tag不为空，为ELWrapper设置tag
        if (StrUtil.isNotBlank(tag)) {
            elWrapper.tag(tag);
        }

        return elWrapper; // 返回创建的ELWrapper对象
    }


    /**
     * 为 ThenELWrapper 添加子级 ELWrapper 列表。
     * @param elWrapper 要添加子级的 ELWrapper 对象
     * @param childrenWrapper 子级 ELWrapper 列表
     */
    private static void addChildThenWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            if (elWrapper instanceof ThenELWrapper){
                childrenWrapper.forEach(wrapper -> {
                    ((ThenELWrapper) elWrapper).then(wrapper);
                });
            }else {
                throw new LiteFlowParseException("顺序执行包装器类型无法解析");
            }
        }
    }

    /**
     * 为 WhenELWrapper 添加子级 ELWrapper 列表。
     * @param elWrapper 要添加子级的 ELWrapper 对象
     * @param childrenWrapper 子级 ELWrapper 列表
     */
    private static void addChildWhenWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            if (elWrapper instanceof WhenELWrapper){
                childrenWrapper.forEach(wrapper -> {
                    ((WhenELWrapper) elWrapper).when(wrapper);
                });
            }else {
                throw new LiteFlowParseException("并行执行包装器类型无法解析");
            }
        }
    }

    /**
     * 为 AndELWrapper 添加子级 ELWrapper 列表。
     * @param elWrapper 要添加子级的 ELWrapper 对象
     * @param childrenWrapper 子级 ELWrapper 列表
     */
    private static void addChildAndWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            if (elWrapper instanceof AndELWrapper){
                childrenWrapper.forEach(wrapper -> {
                    ((AndELWrapper) elWrapper).and(wrapper);
                });
            }else {
                throw new LiteFlowParseException("与执行包装器类型无法解析");
            }
        }
    }

    /**
     * 为 OrELWrapper 添加子级 ELWrapper 列表。
     * @param elWrapper 要添加子级的 ELWrapper 对象
     * @param childrenWrapper 子级 ELWrapper 列表
     */
    private static void addChildOrWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            if (elWrapper instanceof OrELWrapper){
                childrenWrapper.forEach(wrapper -> {
                    ((OrELWrapper) elWrapper).or(wrapper);
                });
            }else {
                throw new LiteFlowParseException("或执行包装器类型无法解析");
            }

        }
    }

    /**
     * 创建包含多个节点的 OrELWrapper 对象。
     * @param nodeIdList 节点 ID 列表
     * @return 创建的 OrELWrapper 对象
     */
    private static ELWrapper createOrWrapper(List<String> nodeIdList) {
        OrELWrapper orWrapper = ELBus.or(nodeIdList.get(0));
        if (nodeIdList.size() > 1) {
            for (int i = 1; i < nodeIdList.size(); i++) {
                orWrapper.or(nodeIdList.get(i));
            }
        }
        return orWrapper;
    }

    /**
     * 创建包含多个节点的 AndELWrapper 对象。
     * @param nodeIdList 节点 ID 列表
     * @return 创建的 AndELWrapper 对象
     */
    private static ELWrapper createAndWrapper(List<String> nodeIdList) {
        AndELWrapper andWrapper = ELBus.and(nodeIdList.get(0));
        if (nodeIdList.size() > 1) {
            for (int i = 1; i < nodeIdList.size(); i++) {
                andWrapper.and(nodeIdList.get(i));
            }
        }
        return andWrapper;
    }

    /**
     * 创建包含多个节点的 WhenELWrapper 对象。
     * @param nodeIdList 节点 ID 列表
     * @return 创建的 WhenELWrapper 对象
     */
    private static ELWrapper createWhenWrapper(List<String> nodeIdList) {
        WhenELWrapper whenWrapper = ELBus.when(nodeIdList.get(0));
        if (nodeIdList.size() > 1) {
            for (int i = 1; i < nodeIdList.size(); i++) {
                whenWrapper.when(nodeIdList.get(i));
            }
        }
        return whenWrapper;
    }

    /**
     * 创建包含多个节点的 ThenELWrapper 对象。
     * @param nodeIdList 节点 ID 列表
     * @return 创建的 ThenELWrapper 对象
     */
    private static ELWrapper createThenWrapper(List<String> nodeIdList) {
        ThenELWrapper thenWrapper = ELBus.then(nodeIdList.get(0));
        if (nodeIdList.size() > 1) {
            for (int i = 1; i < nodeIdList.size(); i++) {
                thenWrapper.then(nodeIdList.get(i));
            }
        }
        return thenWrapper;
    }





}
