package com.liuli;



import com.yomahub.liteflow.script.ScriptExecuteWrap;
import com.yomahub.liteflow.script.body.JaninoCommonScriptBody;


public class ListenDemo2 implements JaninoCommonScriptBody {
    public Void body(ScriptExecuteWrap executeWrap) {
        String nodeId = executeWrap.cmp.getNodeId();
        System.err.println(nodeId+"执行了");
        return null;
    }
}
