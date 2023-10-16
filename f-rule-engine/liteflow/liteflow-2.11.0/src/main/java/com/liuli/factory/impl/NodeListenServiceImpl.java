package com.liuli.factory.impl;


import com.liuli.factory.NodeExecuteService;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.stereotype.Service;

/**
 * 节点监听服务
 *
 * @author zhanghui
 * @date 2023/09/13 14:53:51
 */
@Service
public class NodeListenServiceImpl implements NodeExecuteService<Void> {
    private static String redisKey = "iot";

    /**
     * 获取执行类型
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/13 14:58:36
     * @see String
     * @since 1.0.0
     */
    @Override
    public String getExecuteType() {
        return "listen";
    }

    /**
     * 执行
     *
     * @param executeWrap 执行WRAP
     * @return
     * @author zhanghui
     * @date 2023/09/13 14:58:36
     * @see ScriptExecuteWrap
     * @since 1.0.0
     */
    @Override
    public Void execute(ScriptExecuteWrap executeWrap) {

        return null;
    }
}
