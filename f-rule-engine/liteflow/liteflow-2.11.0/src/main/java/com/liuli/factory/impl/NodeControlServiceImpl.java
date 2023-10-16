package com.liuli.factory.impl;

import com.liuli.factory.NodeExecuteService;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 节点控制服务实现类
 *
 * @author zhanghui
 * @date 2023/09/18 08:15:53
 */
@Service
@Slf4j
public class NodeControlServiceImpl implements NodeExecuteService<Void> {
    @Override
    public String getExecuteType() {
        return "control";
    }

    @Override
    public Void execute(ScriptExecuteWrap executeWrap) {

        return null;
    }
}
