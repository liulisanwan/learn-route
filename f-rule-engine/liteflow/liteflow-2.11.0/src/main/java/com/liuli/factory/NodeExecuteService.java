package com.liuli.factory;


import com.yomahub.liteflow.script.ScriptExecuteWrap;

/**
 * 节点执行服务
 *
 * @author zhanghui
 * @date 2023/09/13 14:40:01
 */
public interface NodeExecuteService<T> {

    /**
     * 获取执行类型
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/13 15:02:12
     * @see String
     * @since 1.0.0
     */
    String getExecuteType();


    /**
     * 执行
     *
     * @param executeWrap 执行WRAP
     * @author zhanghui
     * @date 2023/09/13 15:02:09
     * @see ScriptExecuteWrap
     * @since 1.0.0
     */
     T execute(ScriptExecuteWrap executeWrap);
}
