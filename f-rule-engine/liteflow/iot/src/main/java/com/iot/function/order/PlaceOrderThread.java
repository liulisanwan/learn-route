package com.iot.function.order;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlChain;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlOtherNodeLock;
import com.iot.database.entity.PlScriptNode;
import com.iot.function.CommonBean;
import com.iot.function.log.NodeUnLockConsumer;
import com.iot.function.log.NodeUnLockFunction;
import com.yomahub.liteflow.flow.LiteflowResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 下置订单线程
 *
 * @author zhanghui
 * @date 2023/09/14 14:43:45
 */
public class PlaceOrderThread extends CommonBean implements Runnable {

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 产品型号
     */
    private String productModel;
    /**
     * 执行密钥
     */
    private String executionKey;

    /**
     * 装置，装置状态码
     */
    private String deviceCode;

    private PlChain chain;


    public PlaceOrderThread(String orderNo, String productModel, String executionKey, String deviceCode, PlChain chain) {
        this.orderNo = orderNo;
        this.productModel = productModel;
        this.executionKey = executionKey;
        this.deviceCode = deviceCode;
        this.chain = chain;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getExecutionKey() {
        return executionKey;
    }

    public void setExecutionKey(String executionKey) {
        this.executionKey = executionKey;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public PlChain getChain() {
        return chain;
    }

    public void setChain(PlChain chain) {
        this.chain = chain;
    }

    /**
     * 跑
     *
     * @author zhanghui
     * @date 2023/09/12 14:45:12
     * @since 1.0.0
     */
    @Override
    public void run() {

        try {
            CustomDefaultContext context = new CustomDefaultContext();
            context.setData("age", 1);
            context.setData("name", "4");
            if (ObjectUtil.isEmpty(chain)) {
                throw new RuntimeException("链路不存在");
            }
            context.setData("myChain", chain);
            //此处应变为枚举或者常量  1为单例 0为多例
            Boolean chainModel = chain.getSingleMultiple().equals("1") ? true : false;
            //chan的模式  singModel 为 单例   MultipleExamples为多例
            context.setData("chainModel", chainModel);
            context.setData("orderNo", orderNo);
            context.setData("productModel", productModel);
            context.setData("executionKey", executionKey);
            context.setData("deviceCode", deviceCode);
            MPJLambdaWrapper<PlConstant> wrapper = new MPJLambdaWrapper<>();
            wrapper.eq(PlConstant::getChainId, chain.getChainName());
            List<PlConstant> constantList = constantService.list(wrapper);
            if (CollectionUtil.isNotEmpty(constantList)) {
                context.setData("constantList", constantList);
            }
            List<PlScriptNode> scriptNodeList = plScriptNodeService.list();
            if (CollectionUtil.isNotEmpty(scriptNodeList)) {
                Map<String, PlScriptNode> scriptNodeMap = scriptNodeList.stream().collect(Collectors.toMap(PlScriptNode::getScriptNodeId, Function.identity()));
                context.setData("scriptNodeMap", scriptNodeMap);
            }
            MPJLambdaWrapper<PlOtherNodeLock> otherNodeLockMPJLambdaWrapper = new MPJLambdaWrapper<>();
            otherNodeLockMPJLambdaWrapper.selectAll(PlOtherNodeLock.class)
                    .eq(PlChain::getChainName, chain.getChainName())
                    .leftJoin(PlScriptNode.class, PlScriptNode::getScriptNodeId, PlOtherNodeLock::getNodeId)
                    .leftJoin(PlChain.class, PlChain::getChainName, PlScriptNode::getChainId);
            List<PlOtherNodeLock> otherNodeLocks = plOtherNodeLockService.list(otherNodeLockMPJLambdaWrapper);
            if (CollectionUtil.isNotEmpty(otherNodeLocks)) {
                Map<String, List<String>> orderNodeLockMap = new HashMap<>();
                for (PlOtherNodeLock otherNodeLock : otherNodeLocks) {
                    if (orderNodeLockMap.get(otherNodeLock.getNodeId()) == null) {
                        List<String> list = CollectionUtil.newArrayList();
                        list.add(otherNodeLock.getSubProductName());
                        orderNodeLockMap.put(otherNodeLock.getNodeId(), list);
                    }
                }

                Map<String, List<String>> orderNodeLockMap2 = new HashMap<>();

                orderNodeLockMap2 = otherNodeLocks.stream()
                        .collect(Collectors.groupingBy(
                                PlOtherNodeLock::getNodeId, // 分组键是节点ID
                                Collectors.mapping(
                                        PlOtherNodeLock::getSubProductName, // 映射子产品名称
                                        Collectors.toList() // 将子产品名称收集到列表中
                                )
                        ));
                //key 为 nodeId value为  子产品名称
                context.setData("orderNodeLockMap", orderNodeLockMap);
            }

            LiteflowResponse response = flowExecutor.execute2Resp(chain.getChainName(), "args", context);
            context = response.getFirstContextBean();
            constantList = context.getData("constantList");
            System.err.println(constantList);

            //TODO 不用获取上下文的数据,直接获取scriptNode表的所有subProductName然后拼接在一起最后再去redis中删除
            Map<String, List<String>> orderNodeLockMap = context.getData("orderNodeLockMap");
            List<String> subProductNameList = scriptNodeList.stream().map(scriptNode -> scriptNode.getSubProductName()).collect(Collectors.toList());
            System.out.println(subProductNameList);
            System.out.println("orderNodeLockMap"+orderNodeLockMap);
            System.out.println("subProductNameList"+subProductNameList);
            if (!CollectionUtil.isEmpty(subProductNameList)){
                for (String subProductName : subProductNameList) {
                    String lockKey = deviceCode + "_" + chain.getChainName() + "_" + subProductName;
                    NodeUnLockFunction nodeUnLockFunction = new NodeUnLockFunction(orderNo);
                    NodeUnLockConsumer unLockConsumer = new NodeUnLockConsumer();
                    handleFunctionRedisUtil.releaseRedisLock(lockKey, chain.getChainName(), nodeUnLockFunction, unLockConsumer);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
