package com.liuli.cmp;

import com.yomahub.liteflow.annotation.LiteflowComponent;
import com.yomahub.liteflow.core.NodeComponent;
import com.yomahub.liteflow.slot.DefaultContext;
import lombok.extern.slf4j.Slf4j;

/**
 * dcmp -> 声明式组件方式2:继承NodeComponent类加入容器
 *
 * @author zhanghui
 * @date 2023/08/09 10:10:59
 */
@LiteflowComponent("d")
@Slf4j
public class DCmp extends NodeComponent {
    @Override
    public void process() throws Exception {
        DefaultContext context = this.getFirstContextBean();
        String key = "test";
        if (context.hasData(key)) {
            int count = context.getData(key);
            context.setData(key, ++count);
        }
        else {
            context.setData(key, 1);
        }
        log.info("d 执行完毕");
    }
}
