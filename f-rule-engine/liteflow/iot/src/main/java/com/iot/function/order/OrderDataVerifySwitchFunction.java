package com.iot.function.order;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.database.entity.PlConstant;
import com.iot.database.entity.PlListen;
import com.iot.function.CommonBean;
import com.iot.util.OrderDataUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 订单数据验证选择功能
 *
 * @author zhanghui
 * @date 2023/09/14 15:14:28
 */
public class OrderDataVerifySwitchFunction extends CommonBean implements Function<String,String> {


    /**
     * 地图 -> id 此id是liteflow执行返回的id去选择那条链路  node有id别名,有可能同id有多个node
     * 分组以chainId为分组标准,map<id,list<PLNode>> node去找对应listen数据  map <id,list<PlListen>>
     *
     *  select * from plChain chain left join plNode node on chain.id = node.chainId
     *  left join plListen listen on node.id = listen.nodeId where chain.id = iot1
     *  dto -> List<DTO>.stream..map(dto->{
     *
     *  }
     *
     *  <chain name="chain1">
     *     THEN(
     *         a,
     *         SWITCH(b).to(
     *             c,
     *             THEN(d, e).id("t1")
     *         ),
     *         f
     *     );
     * </chain>
     *
     *  String ->Alias id t1
     */
    private Map<String, List<PlListen>> map;

    private List<PlConstant> constantList;

    private JSONObject redisObject;


    public OrderDataVerifySwitchFunction(Map<String, List<PlListen>> map, List<PlConstant> constantList) {
        this.map = map;
        this.constantList = constantList;
    }



    @Override
    public String apply(String dataKey) {
        Assert.state(StrUtil.isNotBlank(dataKey), "dataKey不能为空");
        //订单判断是否超过有效期
        OrderJudgmentFunction function = new OrderJudgmentFunction();
        Boolean apply = function.apply(dataKey);
        if (apply) {
            JSONObject functionRedisObject = function.getRedisObject();
            if (redisObject.equals(functionRedisObject)) {
                String stringData = functionRedisObject.get("data").toString();
                JSONObject jsonData = JSON.parseObject(stringData, JSONObject.class);
                for (Map.Entry<String, List<PlListen>> entry : map.entrySet()) {
                    List<PlListen> listenList = entry.getValue();
                    Integer resultCount = OrderDataUtil.checkRedisData(jsonData,listenList);
                    if (resultCount == listenList.size()) {
                        OrderDataUtil.checkConstant(jsonData, constantList);
                        return entry.getKey();
                    }
                }
                throw new RuntimeException("redis中的数据和数据库中的数据不一致");
            }
        }
        return null;
    }


    public Map<String, List<PlListen>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<PlListen>> map) {
        this.map = map;
    }

    public List<PlConstant> getConstantList() {
        return constantList;
    }

    public void setConstantList(List<PlConstant> constantList) {
        this.constantList = constantList;
    }

    public JSONObject getRedisObject() {
        return redisObject;
    }

    public void setRedisObject(JSONObject redisObject) {
        this.redisObject = redisObject;
    }



}
