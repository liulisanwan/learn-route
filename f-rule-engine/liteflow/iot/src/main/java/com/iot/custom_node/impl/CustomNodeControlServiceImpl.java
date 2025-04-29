package com.iot.custom_node.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlControl;
import com.iot.database.entity.PlScriptNode;
import com.iot.custom_node.CustomNodeExecuteService;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 节点控制服务实现类
 *
 * @author zhanghui
 * @date 2023/09/18 08:15:53
 */
@Service
@Slf4j
public class CustomNodeControlServiceImpl implements CustomNodeExecuteService<Void> {
    @Override
    public String getExecuteType() {
        return "control";
    }

    @Override
    public Void execute(ScriptExecuteWrap executeWrap) {
        CustomDefaultContext context = executeWrap.cmp.getFirstContextBean();
        Map<String, PlScriptNode> scriptNodeMap = context.getData("scriptNodeMap");
        Assert.state(CollectionUtil.isNotEmpty(scriptNodeMap), "脚本节点信息为空");
        String nodeId = executeWrap.cmp.getNodeId();
        PlScriptNode plScriptNode = scriptNodeMap.get(nodeId);
        Assert.state(plScriptNode != null, "脚本节点信息为空");
        LambdaQueryWrapper<PlControl> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PlControl::getNodeId, nodeId);
        List<PlControl> controlList = controlService.list(wrapper);
        Assert.state(CollectionUtil.isNotEmpty(controlList), "控制节点信息为空");
        log.info("mqtt数据已下发,数据为:"+JSON.toJSONString(controlList));
        return null;
    }
}
