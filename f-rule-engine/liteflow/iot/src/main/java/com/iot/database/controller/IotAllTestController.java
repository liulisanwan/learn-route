package com.iot.database.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.iot.database.entity.PlChain;
import com.iot.database.entity.PlListen;
import com.iot.database.entity.PlScriptNode;
import com.iot.database.service.IPlChainService;
import com.iot.database.service.IPlListenService;
import com.iot.database.service.IPlScriptNodeService;
import com.iot.custom_node.CustomNodeExecuteType;
import com.iot.function.order.PlaceOrderThread;
import com.iot.litelfow.enums.ScriptType;
import com.iot.util.ExecutorUtil;
import com.iot.util.ScriptNodeDataToStringUtil;
import com.yomahub.liteflow.core.FlowExecutor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 物联网全部测试控制器
 *
 * @author zhanghui
 * @date 2023/10/18 14:23:59
 */
@RestController
@RequestMapping("/iot")
@Api(tags = "iot所有测试接口")
public class IotAllTestController {

    /**
     * 链服务
     */
    @Autowired
    IPlChainService chainService;
    /**
     * 流执行人
     */
    @Resource
    FlowExecutor flowExecutor;


    @Autowired
    private IPlListenService plListenService;


    @GetMapping("/listByChain")
    @ApiOperation(value = "根据chainId查询监听表")
    public List<PlListen> listByChain(String chainId) {
        MPJLambdaWrapper<PlListen> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(PlListen.class).select(PlScriptNode::getAliasId)
                .leftJoin(PlScriptNode.class, PlScriptNode::getScriptNodeId, PlListen::getNodeId)
                .leftJoin(PlChain.class, PlChain::getChainName, PlScriptNode::getChainId)
                .eq(PlChain::getChainName, chainId);
        List<PlListen> list = plListenService.list(wrapper);
        return list;
    }


    @Autowired
    IPlScriptNodeService plScriptNodeService;

    /**
     * 保存
     *
     * @author zhanghui
     * @date 2023/09/12 14:26:17
     * @since 1.0.0
     */
    @GetMapping("/save")
    @ApiOperation(value = "保存")
    public void save(){

        PlScriptNode plScriptNode = new PlScriptNode();
        plScriptNode.setApplicationName("demo");
        String scriptNodeId = "iot" + RandomUtil.randomInt(4, 200);
        plScriptNode.setScriptNodeId(scriptNodeId);
        plScriptNode.setScriptNodeName(scriptNodeId);
        plScriptNode.setScriptNodeType(ScriptType.SCRIPT.getType());
        plScriptNode.setScriptNodeData(ScriptNodeDataToStringUtil.voidTestData());
        plScriptNode.setScriptLanguage("java");
        plScriptNode.setStatus("0");
        plScriptNode.setChainId("chain7");
        plScriptNode.setIdle("0");
        plScriptNode.setSubProductName("test2");
        plScriptNode.setReleaseRedis("1");
        plScriptNode.setAliasId(scriptNodeId);
        plScriptNodeService.save(plScriptNode);
    }




    @GetMapping("/execute")
    @ApiOperation(value = "执行")
    public void execute(String orderNo,String deviceCode,String productModel,String executionKey) {
        MPJLambdaWrapper<PlChain> chainMPJLambdaWrapper = new MPJLambdaWrapper<>();
        chainMPJLambdaWrapper.eq(PlChain::getProductModel, productModel).eq(PlChain::getExecutionKey, executionKey);
        PlChain chain = chainService.getOne(chainMPJLambdaWrapper);
        ThreadPoolExecutor threadPoolExecutor = ExecutorUtil.executorMap.get(deviceCode);
        if (threadPoolExecutor==null){
            Integer count;
            if (chain.getSingleMultiple().equals("1")){
                count=1;
            }else {
                MPJLambdaWrapper<PlScriptNode> scriptNodeMPJLambdaWrapper = new MPJLambdaWrapper<>();
                scriptNodeMPJLambdaWrapper.eq(PlScriptNode::getChainId, chain.getId()).groupBy(PlScriptNode::getSubProductName);
                List<PlScriptNode> scriptNodeList = plScriptNodeService.list(scriptNodeMPJLambdaWrapper);
                count= CollectionUtil.isEmpty(scriptNodeList)?1:scriptNodeList.size();
            }
            threadPoolExecutor=ExecutorUtil.createThreadPool(count,deviceCode);
            ExecutorUtil.executorMap.put(deviceCode,threadPoolExecutor);
        }
        threadPoolExecutor.submit(new PlaceOrderThread(orderNo,productModel,executionKey,deviceCode,chain));
    }


    @GetMapping("/testQuery")
    @ApiOperation(value = "测试查询")
    public void testQuery(){
        MPJLambdaWrapper<PlScriptNode> scriptNodeMPJLambdaWrapper = new MPJLambdaWrapper<>();
        scriptNodeMPJLambdaWrapper.eq(PlScriptNode::getChainId, "chain1").groupBy(PlScriptNode::getSubProductName);
        List<PlScriptNode> scriptNodeList = plScriptNodeService.list(scriptNodeMPJLambdaWrapper);
        System.err.println(scriptNodeList);
    }


    @GetMapping("/wms")
    @ApiOperation("wms测试插入")
    public void wms(){
        PlScriptNode node1 = PlScriptNode.builder().scriptNodeId("br1").scriptNodeData(ScriptNodeDataToStringUtil.switchScriptNodeData()).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("判断自动").build();
        PlScriptNode node2 = PlScriptNode.builder().scriptNodeId("br2").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("监听rfid").build();
        PlScriptNode node3 = PlScriptNode.builder().scriptNodeId("br3").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("订单查询").build();
        PlScriptNode node4 = PlScriptNode.builder().scriptNodeId("br4").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("plc物料通过").build();
        PlScriptNode node5 = PlScriptNode.builder().scriptNodeId("br5").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("agv调用1").build();
        PlScriptNode node6 = PlScriptNode.builder().scriptNodeId("br6").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("agv回调1").build();
        PlScriptNode node7 = PlScriptNode.builder().scriptNodeId("br7").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("avg皮带线内转1").build();
        PlScriptNode node8 = PlScriptNode.builder().scriptNodeId("br8").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("plc皮带线转1").build();
        PlScriptNode node9 = PlScriptNode.builder().scriptNodeId("br9").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("0").aliasId("blrk").scriptNodeName("avg内转回调1").build();
        PlScriptNode node10 = PlScriptNode.builder().scriptNodeId("br10").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("0").releaseRedis("1").aliasId("blrk").scriptNodeName("plc复位1").build();
        PlScriptNode node11 = PlScriptNode.builder().scriptNodeId("br11").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("调用AGV小车到达库房输送平台2").build();
        PlScriptNode node12 = PlScriptNode.builder().scriptNodeId("br12").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("agv到达输送平台2回调mes").build();
        PlScriptNode node13 = PlScriptNode.builder().scriptNodeId("br13").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("监听plc输送平台2是否空闲允许agv小车入料").build();
        PlScriptNode node14 = PlScriptNode.builder().scriptNodeId("br14").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("允许入料，调用agv小车皮带线外传,送料到plc2").build();
        PlScriptNode node15 = PlScriptNode.builder().scriptNodeId("br15").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("给plc输送2 一个内转的信号，让皮带线转动").build();
        PlScriptNode node16 = PlScriptNode.builder().scriptNodeId("br16").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("小车外传回调").build();
        PlScriptNode node17 = PlScriptNode.builder().scriptNodeId("br17").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("plc复位2").build();
        PlScriptNode node18 = PlScriptNode.builder().scriptNodeId("br18").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("监听Rfid是否可以入库").build();
        PlScriptNode node19 = PlScriptNode.builder().scriptNodeId("br19").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("mes给排行列").build();
        PlScriptNode node20 = PlScriptNode.builder().scriptNodeId("br20").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("plc物料通过").build();
        PlScriptNode node21 = PlScriptNode.builder().scriptNodeId("br21").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("控制入库").build();
        PlScriptNode node22 = PlScriptNode.builder().scriptNodeId("br22").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.LISTEN)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("监听入库完成信号").build();
        PlScriptNode node23 = PlScriptNode.builder().scriptNodeId("br23").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.CONTROL)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("入库完成").build();
        PlScriptNode node24 = PlScriptNode.builder().scriptNodeId("br24").scriptNodeData(ScriptNodeDataToStringUtil.voidScriptNodeData(CustomNodeExecuteType.THIRD)).subProductName("wms库房").idle("1").aliasId("blrk").scriptNodeName("封闭mes订单任务").build();
        List<PlScriptNode> list = Arrays.asList(node1,node2,node3,node4,node5,node6,node7,node8,node9,node10,node11, node12, node13, node14, node15, node16, node17, node18, node19, node20,node21,node22,node23,node24);
        for (PlScriptNode plScriptNode : list) {
            plScriptNode.setScriptLanguage("java");
            plScriptNode.setScriptNodeType(ScriptType.SCRIPT.getType());
            if (plScriptNode.getScriptNodeData().contains("switch")){
                plScriptNode.setScriptNodeType(ScriptType.SWITCH_SCRIPT.getType());
            }

        }
        plScriptNodeService.saveBatch(list);
    }
}
