package com.liuli;


import com.liuli.factory.ExecuteType;
import com.liuli.factory.NodeExecuteFactory;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoIfScriptBody;
import com.yomahub.liteflow.spi.holder.ContextAwareHolder;

public class IfDemo implements JaninoIfScriptBody {

    public Boolean body(ScriptExecuteWrap executeWrap) {
        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);
        Boolean execute = (Boolean)nodeExecuteFactory.execute(ExecuteType.IF, executeWrap);
        return execute;
    }
}
