package com.iot.custom_node.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.database.entity.PlScriptNode;
import com.iot.database.service.IPlListenService;
import com.iot.custom_node.CustomNodeExecuteService;
import com.iot.function.order.OrderSwitchThread;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class CustomNodeSwitchServiceImpl implements CustomNodeExecuteService<String> {

    private final static String redisKey ="iot";


    @Autowired
    private IPlListenService listenService;

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
        CustomDefaultContext context = executeWrap.cmp.getFirstContextBean();
        String nodeId = executeWrap.cmp.getNodeId();
        String chainId = executeWrap.cmp.getChainId();
        String deviceCode = context.getData("deviceCode");
        Map<String, PlScriptNode> scriptNodeMap = context.getData("scriptNodeMap");
        PlScriptNode plScriptNode = scriptNodeMap.get(nodeId);
        String nodeRedisKey = plScriptNode.getRedisKey();
        String redisKeySuffix = nodeRedisKey.contains("agv") ? chainId + nodeRedisKey : nodeRedisKey;
        String redisKeyPrefix = deviceCode + "_";
        String redisKey = redisKeyPrefix + redisKeySuffix;
        log.info(redisKey);
        LambdaQueryWrapper<PlListen> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PlListen::getNodeId, nodeId);
        List<PlListen> list = listenService.list(wrapper);
        Map<String, List<PlListen>> collect = list.stream().filter(plListen -> StrUtil.isNotBlank(plListen.getSwitchNodeId())).collect(Collectors.groupingBy(PlListen::getSwitchNodeId));
        ExecutorService executor =Executors .newSingleThreadExecutor();
        List<PlConstant> constantList = context.getData("constantList");
        OrderSwitchThread callable = new OrderSwitchThread(FUNCTION_UTIL,redisKey,collect ,constantList);
        Future<String> future = executor.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
