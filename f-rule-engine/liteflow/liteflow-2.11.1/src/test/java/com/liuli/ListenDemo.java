package com.liuli;




import com.liuli.factory.ExecuteType;
import com.liuli.factory.NodeExecuteFactory;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;
import com.yomahub.liteflow.spi.holder.ContextAwareHolder;

public class ListenDemo implements JaninoCommonScriptBody {
    public Void body(ScriptExecuteWrap executeWrap) {
        NodeExecuteFactory nodeExecuteFactory = (NodeExecuteFactory) ContextAwareHolder.loadContextAware().getBean(NodeExecuteFactory.class);
        nodeExecuteFactory.execute(ExecuteType.LISTEN,executeWrap);
        return null;
    }
}
