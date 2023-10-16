package com.liuli.aspect;

import com.yomahub.liteflow.aop.ICmpAroundAspect;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;


/**
 * @Author hanxiaowei
 * @Description 记录全局切面 针对于所有的组件，进行切面
 * @Date 11:47 2023/9/8
 **/
@Component
@Slf4j
public class NodeLogAspect implements ICmpAroundAspect {


    @Override
    public void beforeProcess(NodeComponent nodeComponent) {

    }

    @Override
    public void afterProcess(NodeComponent nodeComponent) {

    }

    @Override
    public void onSuccess(NodeComponent nodeComponent) {

    }

    @Override
    public void onError(NodeComponent nodeComponent, Exception e) {

    }
}
