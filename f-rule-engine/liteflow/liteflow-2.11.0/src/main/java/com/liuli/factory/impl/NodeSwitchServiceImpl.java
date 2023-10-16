package com.liuli.factory.impl;


import com.liuli.factory.NodeExecuteService;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.stereotype.Service;

@Service
public class NodeSwitchServiceImpl implements NodeExecuteService<String> {


    @Override
    public String getExecuteType() {
        return "switch";
    }

    /**
     * @Author hanxiaowei
     * @Description 需要返回 对应的node节点别名为
     * @Date 16:08 2023/9/14
     * @Param
     * @param executeWrap
     * @return
     * @return java.lang.String
     **/
    @Override
    public String execute(ScriptExecuteWrap executeWrap) {
        return null;
    }
}
