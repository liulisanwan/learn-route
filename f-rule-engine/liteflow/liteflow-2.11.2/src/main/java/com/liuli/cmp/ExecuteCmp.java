package com.liuli.cmp;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.annotation.LiteflowMethod;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.enums.LiteFlowMethodEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * execute cmp ->声明式组件1 : 使用@LiteComponent注解声明
 * 在方法加入@LiteFlowMethod注解,value=LiteFlowMethodEnum.PROCESS,nodeId=a来声明这是a的执行方法
 * 同类型可以在一个类里声明多个节点
 *
 * @author zhanghui
 * @date 2023/08/09 10:06:14
 */
@LiteflowComponent
@Slf4j
public class ExecuteCmp {
    /**
     * 过程acmp
     *
     * @param bindCmp 绑定cmp
     * @author zhanghui
     * @date 2023/08/09 10:06:40
     * @see NodeComponent
     * @since 1.0.0
     */
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS,nodeId = "a")
    public void processAcmp(NodeComponent bindCmp) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("a 执行完毕");
    }

    /**
     * 过程bcmp
     *
     * @param bindCmp 绑定cmp
     * @author zhanghui
     * @date 2023/08/09 10:06:40
     * @see NodeComponent
     * @since 1.0.0
     */
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS,nodeId = "b")
    public void processBcmp(NodeComponent bindCmp) {
        log.info("b 执行完毕");
    }

    /**
     * ccmp过程
     *
     * @param bindCmp 绑定cmp
     * @author zhanghui
     * @date 2023/08/09 10:06:40
     * @see NodeComponent
     * @since 1.0.0
     */
    @LiteflowMethod(value = LiteFlowMethodEnum.PROCESS,nodeId = "c")
    public void processCcmp(NodeComponent bindCmp) {
        log.info("c 执行完毕");
    }


}
