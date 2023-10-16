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
                elWrapper = ELBus.or(receiveData.getNodeId());
                if (CollectionUtil.isNotEmpty(childrenWrapper)) {
                    childrenWrapper.forEach(wrapper -> {
                        ((OrELWrapper) elWrapper).or(wrapper);
                    });
                }
                if (StrUtil.isNotBlank(id)) ((OrELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((OrELWrapper) elWrapper).tag(tag);
                break;
            case "AndELWrapper":
                elWrapper = ELBus.and(receiveData.getNodeId());
                if (CollectionUtil.isNotEmpty(childrenWrapper)) {
                    childrenWrapper.forEach(wrapper -> {
                        ((AndELWrapper) elWrapper).and(wrapper);
                    });
                }
                if (StrUtil.isNotBlank(id)) ((AndELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((AndELWrapper) elWrapper).tag(tag);
                break;
            case "WhenELWrapper":
                elWrapper = ELBus.when(receiveData.getNodeId());
                if (CollectionUtil.isNotEmpty(childrenWrapper)) {
                    childrenWrapper.forEach(wrapper -> {
                        ((WhenELWrapper) elWrapper).when(wrapper);
                    });
                }
                if (StrUtil.isNotBlank(id)) ((WhenELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((WhenELWrapper) elWrapper).tag(tag);
                break;
            default:
            case "ThenELWrapper":
                elWrapper = ELBus.then(receiveData.getNodeId());
                if (CollectionUtil.isNotEmpty(childrenWrapper)) {
                    childrenWrapper.forEach(wrapper -> {
                        ((ThenELWrapper) elWrapper).then(wrapper);
                    });
                }
                if (StrUtil.isNotBlank(id)) ((ThenELWrapper) elWrapper).id(id);
                if (StrUtil.isNotBlank(tag)) ((ThenELWrapper) elWrapper).tag(tag);
                break;
        }
        return elWrapper;
    }


}
