package com.liuli;



import com.liuli.factory.ExecuteType;
import com.liuli.factory.NodeExecuteFactory;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoSwitchScriptBody;
import com.yomahub.liteflow.spi.holder.ContextAwareHolder;

public class SwitchDemo implements JaninoSwitchScriptBody {

    public String body(ScriptExecuteWrap executeWrap) {
        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);
        String key = nodeExecuteFactory.execute(ExecuteType.SWITCH, executeWrap).toString();
        return key;
    }
}
