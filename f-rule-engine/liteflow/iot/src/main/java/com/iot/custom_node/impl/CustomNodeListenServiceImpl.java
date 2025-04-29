package com.iot.custom_node.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.database.entity.PlScriptNode;
import com.iot.custom_node.CustomNodeExecuteService;
import com.iot.function.order.OrderThread;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 节点监听服务
 *
 * @author zhanghui
 * @date 2023/09/13 14:53:51
 */
@Service
public class CustomNodeListenServiceImpl implements CustomNodeExecuteService<Void> {
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
        CustomDefaultContext context = executeWrap.cmp.getFirstContextBean();
        String nodeId = executeWrap.cmp.getNodeId();
        System.err.println("正在执行...."+nodeId);
        String deviceCode = context.getData("deviceCode");
        Map<String, PlScriptNode> scriptNodeMap = context.getData("scriptNodeMap");
        PlScriptNode plScriptNode = scriptNodeMap.get(nodeId);
        String nodeRedisKey = plScriptNode.getRedisKey();
        String redisKeySuffix = nodeRedisKey.contains("agv") ? executeWrap.cmp.getChainId() + nodeRedisKey : nodeRedisKey;
        String redisKeyPrefix = deviceCode + "_";
        String redisKey = redisKeyPrefix + redisKeySuffix;
        log.info(redisKey);
        LambdaQueryWrapper<PlListen> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PlListen::getNodeId, nodeId);
        List<PlListen> list = listenService.list(wrapper);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<PlConstant> constantList = context.getData("constantList");
        OrderThread callable = new OrderThread(redisKey, list, constantList, FUNCTION_UTIL);
        Future<Boolean> future = executor.submit(callable);
        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
