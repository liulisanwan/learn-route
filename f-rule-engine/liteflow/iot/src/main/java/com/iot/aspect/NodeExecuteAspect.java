package com.iot.aspect;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.iot.database.entity.PlExecutionLog;
import com.iot.database.entity.PlScriptNode;
import com.iot.database.service.IPlExecutionLogService;
import com.iot.custom_node.HandleDeviceErrorService;
import com.iot.function.log.NodeLockConsumer;
import com.iot.function.log.NodeLockFunction;
import com.iot.function.log.NodeUnLockConsumer;
import com.iot.function.log.NodeUnLockFunction;
import com.iot.function.order.PlaceOrderThread;
import com.iot.util.ExecutorUtil;
import com.iot.util.HandleFunctionRedisUtil;
import com.iot.util.UUIDUtils;
import com.yomahub.liteflow.aop.ICmpAroundAspect;
import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * 节点执行切面
 *
 * @Author hanxiaowei
 * @Description 记录全局切面 针对于所有的组件，进行切面
 * @Date 11:47 2023/9/8
 **/
@Component
@Slf4j
public class NodeExecuteAspect implements ICmpAroundAspect {

    @Autowired
    private IPlExecutionLogService logService;

    @Autowired
    private HandleFunctionRedisUtil handleFunctionRedisUtil;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    HandleDeviceErrorService handleDeviceErrorService;


    /**
     * @param cmp
     * @return
     * @Author hanxiaowei
     * @Description //进入 node节点信息之前
     * @Date 11:48 2023/9/8
     * @Param
     **/
    @Override
    public void beforeProcess(NodeComponent cmp) {
        String nodeId = cmp.getNodeId();
        CustomDefaultContext defaultContext = cmp.getFirstContextBean();
        //先记录日志
        PlExecutionLog executionLogEntity = new PlExecutionLog();
        executionLogEntity.setNodeId(nodeId);
        executionLogEntity.setRemarks("进入 " + executionLogEntity.getNodeId() + "节点之前");
        saveLog(executionLogEntity, defaultContext);
        //后加锁
        /*
         * 1.获取额外加锁标识
         * 2.获取当前节点信息
         * 3.如果额外加锁为空或者额外加锁List不为空,节点的子产品名称不为空,list不包含此节点的子产品名称时正常枷锁
         * 4.获取当前节点的子产品名称，从list中移除，如果list长度为0，从上下文中移除额外加锁标识
         * */
        List<String> subProductNameList = defaultContext.getData("orderNodeLockFlag");
        Map<String, PlScriptNode> scriptNodeMap = defaultContext.getData("scriptNodeMap");
        Assert.state(CollectionUtil.isNotEmpty(scriptNodeMap), "scriptNodeMap为空");
        PlScriptNode node = scriptNodeMap.get(nodeId);
        Assert.state(ObjectUtil.isNotEmpty(node), "node为空");
        if ((CollectionUtil.isEmpty(subProductNameList)) ||
                (CollectionUtil.isNotEmpty(subProductNameList) &&
                        StrUtil.isNotBlank(node.getSubProductName()) &&
                        !subProductNameList.contains(node.getSubProductName()))) {
            try {
                lock(node, defaultContext);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return;
        }
        log.info("当前节点不需要加锁:{}", node.getSubProductName());
        subProductNameList.remove(node.getSubProductName());
        if (subProductNameList.size() == 0) {
            defaultContext.removeData("orderNodeLockFlag");
        }


    }


    /**
     * @param cmp
     * @return
     * @Author hanxiaowei
     * @Description
     * @Date 11:48 2023/9/8
     * @Param
     **/
    @Override
    public void afterProcess(NodeComponent cmp) {
        String nodeId = cmp.getNodeId();
        CustomDefaultContext defaultContext = cmp.getFirstContextBean();
        //先解锁
        try {
            unlock(cmp, defaultContext, nodeId, false);
            // 1.判断orderNodeLockMap为不为空 为空跳出
            // 2.判断map中获取节点id是否为空 为空跳出
            // 3.判断list是否为空 为空跳出
            // 4.循环加锁结束后,往上下文加入orderNodeLockFlag对象,value为list
            Map<String, List<String>> orderNodeLockMap = defaultContext.getData("orderNodeLockMap");
            if (CollectionUtil.isNotEmpty(orderNodeLockMap) && orderNodeLockMap.containsKey(nodeId) &&
                    CollectionUtil.isNotEmpty(orderNodeLockMap.get(nodeId))){
                List<String> subProductNameList = orderNodeLockMap.get(nodeId);
                for (String subProductName : subProductNameList) {
                    try {
                        log.info("after加锁子产品名称:{}", subProductName);
                        lockBySubProductName(cmp, defaultContext, subProductName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                //value 为   子产品名称List
                defaultContext.setData("orderNodeLockFlag", subProductNameList);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //后记录日志
            PlExecutionLog executionLogEntity = new PlExecutionLog();
            executionLogEntity.setNodeId(nodeId);
            executionLogEntity.setRemarks("执行完毕 " + executionLogEntity.getNodeId() + "节点之后");
            saveLog(executionLogEntity, defaultContext);
        }

    }

    @Override
    public void onSuccess(NodeComponent cmp) {

    }

    @Override
    public void onError(NodeComponent cmp, Exception e) {
        String nodeId = cmp.getNodeId();
        CustomDefaultContext defaultContext = cmp.getFirstContextBean();
        //先解锁,释放所有锁 ->如果chain是单实例释放chain锁，如果是多实例释放subProductName锁
        try {
            unlock(cmp, defaultContext, nodeId, true);
            errReleaseLock(cmp, defaultContext, nodeId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            //在redis设置当前设备编码为异常
            String deviceCode = defaultContext.getData("deviceCode");
            handleDeviceErrorService.recordDataToRedis(deviceCode);
            if (StrUtil.isNotBlank(deviceCode)) {
                List<Runnable> list = ExecutorUtil.releaseExecutor(deviceCode);
                List<String> orderNoList = list.stream().map(runnable -> {
                    if (runnable instanceof PlaceOrderThread) {
                        PlaceOrderThread placeOrderThread = (PlaceOrderThread) runnable;
                        String orderNo = placeOrderThread.getOrderNo();
                        return orderNo;
                    }
                    return null;
                }).collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(orderNoList)) {
                    //调用异常订单处理接口
                    handleDeviceErrorService.recordDataToThird(orderNoList);
                }
            }
            //后记录日志
            PlExecutionLog executionLogEntity = new PlExecutionLog();
            executionLogEntity.setNodeId(nodeId);
            executionLogEntity.setRemarks("执行 " + executionLogEntity.getNodeId() + "节点报错");
            executionLogEntity.setExceptionMessage(e.toString());
            defaultContext.setData("Exception", e.toString());
            saveLog(executionLogEntity, defaultContext);
        }
    }

    /**
     * 锁
     *
     * @param node           节点
     * @param defaultContext 默认上下文
     * @throws Exception 例外情况
     * @author zhanghui
     * @date 2023/09/15 15:39:22
     * @see PlScriptNode
     * @see CustomDefaultContext
     * @since 1.0.0
     */
    private void lock(PlScriptNode node, CustomDefaultContext defaultContext) throws Exception {
        String chainId = node.getChainId();
        String deviceCode = defaultContext.getData("deviceCode");
        String orderNo = defaultContext.getData("orderNo");
        Boolean chainModel = defaultContext.getData("chainModel");
        if (node != null && StrUtil.isNotBlank(orderNo) && StrUtil.isNotBlank(deviceCode)) {
            //必须是多例情况并且node节点加锁标识是需要加锁的才进行加锁
            //加锁的key为设备编码,chainId,子产品名称
            if (node.getIdle().equals("1") && !chainModel) {
                String lockKey = deviceCode + "_" + chainId + "_" + node.getSubProductName();
                NodeLockFunction function = new NodeLockFunction(orderNo);
                NodeLockConsumer consumer = new NodeLockConsumer(orderNo);
                log.info("before加锁子产品名称:{}", node.getSubProductName());
                handleFunctionRedisUtil.redisLock(lockKey, chainId, function, consumer);
            }
        } else {
            throw new RuntimeException("上下文数据异常");
        }
    }

    /**
     * 错误释放锁
     *
     * @param cmp            化学机械抛光
     * @param defaultContext 默认上下文
     * @param nodeId         节点ID
     * @author zhanghui
     * @date 2023/09/15 15:39:59
     * @see NodeComponent
     * @see CustomDefaultContext
     * @see String
     * @since 1.0.0
     */
    private void errReleaseLock(NodeComponent cmp, CustomDefaultContext defaultContext, String nodeId) {
        String chainId = cmp.getChainId();
        String deviceCode = defaultContext.getData("deviceCode");
        Boolean flag;
        try {
            flag = handleFunctionRedisUtil.releaseRedisLockByChainId(chainId, deviceCode);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
        if (flag) {
            log.info("释放锁成功");
        } else {
            //TODO 提交到一个静态队列，后期补偿机制去重试删除锁 redis设置 成功的话就删除 定时任务去删除
            log.info("释放锁失败,请手动释放");
        }
    }

    private void unlock(NodeComponent cmp, CustomDefaultContext defaultContext, String nodeId, boolean flag) throws Exception {
        String chainId = cmp.getChainId();
        String orderNo = defaultContext.getData("orderNo");
        Map<String, PlScriptNode> scriptNodeMap = defaultContext.getData("scriptNodeMap");
        PlScriptNode node = scriptNodeMap.get(nodeId);
        Boolean chainModel = defaultContext.getData("chainModel");
        String deviceCode = defaultContext.getData("deviceCode");
        if (node != null && StrUtil.isNotBlank(orderNo) && StrUtil.isNotBlank(deviceCode)) {
            //当前node节点具有释放锁的标识或者flag为true，且chain不是单实例
            if ((node.getReleaseRedis().equals("1") || flag) && !chainModel) {
                String lockKey = deviceCode + "_" + chainId + "_" + node.getSubProductName();
                log.info("解锁子产品名称:{}", node.getSubProductName());
                releaseLock(orderNo, chainId, lockKey);

            }
        } else {
            throw new RuntimeException("上下文数据异常");
        }
    }


    private void releaseLock(String orderNo, String chainId, String lockKey) throws Exception {
        NodeUnLockFunction nodeUnLockFunction = new NodeUnLockFunction(orderNo);
        NodeUnLockConsumer unLockConsumer = new NodeUnLockConsumer();
        handleFunctionRedisUtil.releaseRedisLock(lockKey, chainId, nodeUnLockFunction, unLockConsumer);
    }


    /**
     * @param cmp
     * @param defaultContext
     * @param subProductName
     * @return
     * @Author hanxiaowei
     * @Description 通过子产品名称加锁
     * @Date 14:02 2023/9/15
     * @Param
     **/
    private void lockBySubProductName(NodeComponent cmp, CustomDefaultContext defaultContext, String subProductName) throws Exception {
        Map<String, PlScriptNode> scriptNodeMap = defaultContext.getData("scriptNodeMap");
        PlScriptNode node = scriptNodeMap.get(cmp.getNodeId());
        String chainId = cmp.getChainId();
        String deviceCode = defaultContext.getData("deviceCode");
        String orderNo = defaultContext.getData("orderNo");
        if (node != null && StrUtil.isNotBlank(orderNo) && StrUtil.isNotBlank(deviceCode)) {
            String lockKey = deviceCode + "_" + chainId + "_" + subProductName;
            NodeLockFunction function = new NodeLockFunction(orderNo);
            NodeLockConsumer consumer = new NodeLockConsumer(orderNo);
            handleFunctionRedisUtil.redisLock(lockKey, chainId, function, consumer);
        } else {
            throw new RuntimeException("上下文数据异常");
        }
    }

    /**
     * @param executionLogEntity 日志对象
     * @param defaultContext     上下文
     * @return
     * @Author hanxiaowei
     * @Description
     * @Date 14:38 2023/9/8
     * @Param
     **/
    private void saveLog(PlExecutionLog executionLogEntity, CustomDefaultContext defaultContext) {

        executionLogEntity.setId(UUIDUtils.getUUID());
        ConcurrentHashMap<String, Object> dataMap = CustomDefaultContext.getDataMap();

        Map<String, String> constantMap = NeedRemoveConstant.getConstantMap();
        constantMap.forEach((k, v) -> {
            if (defaultContext.hasData(v)) {
                dataMap.remove(v);
            }
        });

        executionLogEntity.setLogData(JSON.toJSONString(dataMap));
        executionLogEntity.setLogTime(new Date());
        if (StringUtils.isNotEmpty(defaultContext.getData("orderInfo"))) {
            executionLogEntity.setOrderInfo(defaultContext.getData("orderInfo"));
        }
        //todo 暂时冗余字段 到时候集成到jeesite iot版本中需要去掉，或者使用UserUtils工具类
        executionLogEntity.setCreateBy("1");
        executionLogEntity.setCreateDate(new Date());
        executionLogEntity.setUpdateBy("1");
        executionLogEntity.setUpdateDate(new Date());
        executionLogEntity.setCorpCode("0");
        executionLogEntity.setCorpName("0");
        executionLogEntity.setStatus("0");
        logService.save(executionLogEntity);
    }

}
