package com.liuli.data.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.liuli.data.entity.ElReceiveData;
import com.yomahub.liteflow.builder.el.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 将数据转换为字符串
 *
 * @author zhanghui
 * @date 2023/10/16 15:28:15
 */
public class ElDataToString {


    public static ELWrapper dataToString(ElReceiveData receiveData) {
        List<ELWrapper> childrenWrapper = new ArrayList<>();
        List<ElReceiveData> childrenList = receiveData.getChildrenList();
        if (CollectionUtil.isNotEmpty(childrenList)) {
            for (ElReceiveData data : childrenList) {
                ELWrapper elWrapper = dataToString(data); // 递归构建子级的 ELWrapper
                childrenWrapper.add(elWrapper);
            }
        }
        ELWrapper elWrapper = getWrapper(receiveData, childrenWrapper);
        return elWrapper;
    }


    private static ELWrapper getWrapper(ElReceiveData receiveData, List<ELWrapper> childrenWrapper) {
        ELWrapper elWrapper;
        String id = receiveData.getId();
        String tag = receiveData.getTag();
        String typeName = receiveData.getType().getName();
        List<String> nodeIdList = receiveData.getNodeIdList();
        switch (typeName) {
            case "ForELWrapper":
                elWrapper = ELBus.forOpt(receiveData.getNodeId());
                break;
            case "IteratorELWrapper":
                elWrapper = ELBus.iteratorOpt(receiveData.getNodeId());
                break;
            case "NodeELWrapper":
                elWrapper = ELBus.node(receiveData.getNodeId());
                break;
            case "SwitchELWrapper":
                elWrapper = ELBus.switchOpt(receiveData.getNodeId());
                break;
            case "WhileELWrapper":
                elWrapper = ELBus.whileOpt(receiveData.getNodeId());
                break;
            case "CatchELWrapper":
                elWrapper = ELBus.catchException(receiveData.getNodeId());
                break;
            case "IfELWrapper":
                elWrapper = ELBus.ifOpt(receiveData.getNodeId(), receiveData.getTrueNodeId(), receiveData.getFalseNodeId());
                break;
            case "NotELWrapper":
                elWrapper = ELBus.not(receiveData.getNodeId());
                break;
            case "OrELWrapper":
                elWrapper = createOrWrapper(nodeIdList, receiveData.getNodeId());
                addChildOrWrappers(elWrapper, childrenWrapper);
                break;
            case "AndELWrapper":
                elWrapper = createAndWrapper(nodeIdList, receiveData.getNodeId());
                addChildAndWrappers(elWrapper, childrenWrapper);
                break;
            case "WhenELWrapper":
                elWrapper = createWhenWrapper(nodeIdList, receiveData.getNodeId());
                addChildWhenWrappers(elWrapper, childrenWrapper);
                break;
            default:
            case "ThenELWrapper":
                elWrapper = createThenWrapper(nodeIdList, receiveData.getNodeId());
                addChildThenWrappers(elWrapper, childrenWrapper);
                break;

        }
        if (StrUtil.isNotBlank(id) && !typeName.equals("NodeELWrapper")) {
            elWrapper.id(id);
        }
        if (StrUtil.isNotBlank(tag)) {
            elWrapper.tag(tag);
        }
        return elWrapper;
    }

    private static void addChildThenWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            childrenWrapper.forEach(wrapper -> {
                ((ThenELWrapper) elWrapper).then(wrapper);
            });
        }
    }

    private static void addChildWhenWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            childrenWrapper.forEach(wrapper -> {
                ((WhenELWrapper) elWrapper).when(wrapper);
            });
        }
    }

    private static void addChildAndWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            childrenWrapper.forEach(wrapper -> {
                ((AndELWrapper) elWrapper).and(wrapper);
            });
        }
    }

    private static void addChildOrWrappers(ELWrapper elWrapper, List<ELWrapper> childrenWrapper) {
        if (CollectionUtil.isNotEmpty(childrenWrapper)) {
            childrenWrapper.forEach(wrapper -> {
                ((OrELWrapper) elWrapper).or(wrapper);
            });
        }
    }


    private static ELWrapper createOrWrapper(List<String> nodeIdList, String receiveNodeId) {
        OrELWrapper orWrapper;
        if (CollectionUtil.isNotEmpty(nodeIdList)) {
            orWrapper = ELBus.or(nodeIdList.get(0));
            for (int i = 1; i < nodeIdList.size(); i++) {
                orWrapper.or(nodeIdList.get(i));
            }
        } else {
            orWrapper = ELBus.or(receiveNodeId);
        }
        return orWrapper;
    }

    private static ELWrapper createAndWrapper(List<String> nodeIdList, String receiveNodeId) {
        AndELWrapper andWrapper;
        if (CollectionUtil.isNotEmpty(nodeIdList)) {
            andWrapper = ELBus.and(nodeIdList.get(0));
            for (int i = 1; i < nodeIdList.size(); i++) {
                andWrapper.and(nodeIdList.get(i));
            }
        } else {
            andWrapper = ELBus.and(receiveNodeId);
        }
        return andWrapper;
    }

    private static ELWrapper createWhenWrapper(List<String> nodeIdList, String receiveNodeId) {
        WhenELWrapper whenWrapper;
        if (CollectionUtil.isNotEmpty(nodeIdList)) {
            whenWrapper = ELBus.when(nodeIdList.get(0));
            for (int i = 1; i < nodeIdList.size(); i++) {
                whenWrapper.when(nodeIdList.get(i));
            }
        } else {
            whenWrapper = ELBus.when(receiveNodeId);
        }
        return whenWrapper;
    }

    private static ELWrapper createThenWrapper(List<String> nodeIdList, String receiveNodeId) {
        ThenELWrapper thenWrapper;
        if (CollectionUtil.isNotEmpty(nodeIdList)) {
            thenWrapper = ELBus.then(nodeIdList.get(0));
            for (int i = 1; i < nodeIdList.size(); i++) {
                thenWrapper.then(nodeIdList.get(i));
            }
        } else {
            thenWrapper = ELBus.then(receiveNodeId);
        }
        return thenWrapper;
    }




    private static ELWrapper getWrapper2(ElReceiveData receiveData, List<ELWrapper> childrenWrapper) {
        ELWrapper elWrapper;
        String id = receiveData.getId();
        String tag = receiveData.getTag();
        List<String> nodeIdList = receiveData.getNodeIdList();
        switch (receiveData.getType().getName()) {
            case "ForELWrapper":
                elWrapper = ELBus.forOpt(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((ForELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((ForELWrapper) elWrapper).tag(tag);
                break;
            case "IteratorELWrapper":
                elWrapper = ELBus.iteratorOpt(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((IteratorELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((IteratorELWrapper) elWrapper).tag(tag);
                break;
            case "NodeELWrapper":
                elWrapper = ELBus.node(receiveData.getNodeId());
                if (StrUtil.isNotBlank(tag)) ((NodeELWrapper) elWrapper).tag(tag);
                break;
            case "SwitchELWrapper":
                elWrapper = ELBus.switchOpt(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((SwitchELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((SwitchELWrapper) elWrapper).tag(tag);
                break;
            case "WhileELWrapper":
                elWrapper = ELBus.whileOpt(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((WhileELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((WhileELWrapper) elWrapper).tag(tag);
                break;
            case "CatchELWrapper":
                elWrapper = ELBus.catchException(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((CatchELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((CatchELWrapper) elWrapper).tag(tag);
                break;
            case "IfELWrapper":
                elWrapper = ELBus.ifOpt(receiveData.getNodeId(), receiveData.getTrueNodeId(), receiveData.getFalseNodeId());
                if (StrUtil.isNotBlank(id)) ((IfELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((IfELWrapper) elWrapper).tag(tag);
                break;
            case "NotELWrapper":
                elWrapper = ELBus.not(receiveData.getNodeId());
                if (StrUtil.isNotBlank(id)) ((NotELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((NotELWrapper) elWrapper).tag(tag);
                break;

            case "OrELWrapper":
                elWrapper = createOrWrapper(nodeIdList, receiveData.getNodeId());
                addChildOrWrappers(elWrapper, childrenWrapper);
                if (StrUtil.isNotBlank(id)) ((OrELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((OrELWrapper) elWrapper).tag(tag);
                break;
            case "AndELWrapper":
                elWrapper = createAndWrapper(nodeIdList, receiveData.getNodeId());
                addChildAndWrappers(elWrapper, childrenWrapper);
                if (StrUtil.isNotBlank(id)) ((AndELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((AndELWrapper) elWrapper).tag(tag);
                break;
            case "WhenELWrapper":
                elWrapper = createWhenWrapper(nodeIdList, receiveData.getNodeId());
                addChildWhenWrappers(elWrapper, childrenWrapper);
                if (StrUtil.isNotBlank(id)) ((WhenELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((WhenELWrapper) elWrapper).tag(tag);
                break;
            default:
            case "ThenELWrapper":
                elWrapper = createThenWrapper(nodeIdList, receiveData.getNodeId());
                addChildThenWrappers(elWrapper, childrenWrapper);
                if (StrUtil.isNotBlank(id)) ((ThenELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((ThenELWrapper) elWrapper).tag(tag);
                break;
        }
        return elWrapper;
    }


}
