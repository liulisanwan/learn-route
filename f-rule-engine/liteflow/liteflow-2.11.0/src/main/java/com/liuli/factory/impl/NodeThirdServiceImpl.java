package com.liuli.factory.impl;


import com.liuli.factory.NodeExecuteService;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.stereotype.Service;

/**
 * 节点第三方服务
 *
 * @author zhanghui
 * @date 2023/09/13 15:01:37
 */
@Service
public class NodeThirdServiceImpl implements NodeExecuteService<Void> {


    /**
     * 获取执行类型
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/13 15:01:37
     * @see String
     * @since 1.0.0
     */
    @Override
    public String getExecuteType() {
        return "third";
    }

    /**
     * 执行
     *
     * @param executeWrap 执行WRAP
     * @return
     * @author zhanghui
     * @date 2023/09/13 15:01:56
     * @see ScriptExecuteWrap
     * @since 1.0.0
     */
    @Override
    public Void execute(ScriptExecuteWrap executeWrap) {

        return null;
    }



}
