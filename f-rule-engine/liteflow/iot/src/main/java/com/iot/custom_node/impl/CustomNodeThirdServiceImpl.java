package com.iot.custom_node.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.iot.aspect.CustomDefaultContext;
import com.iot.database.entity.PlThirdParty;
import com.iot.database.entity.PlThirdPartyData;
import com.iot.custom_node.CustomNodeExecuteService;
import com.iot.util.InteractingThirdPartiesTool;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 节点第三方服务
 *
 * @author zhanghui
 * @date 2023/09/13 15:01:37
 */
@Service
public class CustomNodeThirdServiceImpl implements CustomNodeExecuteService<Void> {


    /**
     * 获取执行类型
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/13 15:01:37
     * @see String
     * @since 1.0.0
     */
    @Override
    public String getExecuteType() {
        return "third";
    }

    /**
     * 执行
     *
     * @param executeWrap 执行WRAP
     * @return
     * @author zhanghui
     * @date 2023/09/13 15:01:56
     * @see ScriptExecuteWrap
     * @since 1.0.0
     */
    @Override
    public Void execute(ScriptExecuteWrap executeWrap) {
        CustomDefaultContext context = executeWrap.cmp.getFirstContextBean();
        String nodeId = executeWrap.cmp.getNodeId();
        MPJLambdaWrapper<PlThirdParty> wrapper = new MPJLambdaWrapper<>();
        wrapper.eq(PlThirdParty::getNodeId, nodeId);
        wrapper.selectCollection(PlThirdPartyData.class,PlThirdParty::getPlThirdPartyDataList)
                .leftJoin(PlThirdPartyData.class,PlThirdPartyData::getThirdPartyId,PlThirdParty::getId);
        PlThirdParty plThirdParty = thirdPartyService.selectJoinOne(PlThirdParty.class, wrapper);
        if (plThirdParty!=null){
            Map<String, Object> resultMap = InteractingThirdPartiesTool.httpInteractingThirdParties(plThirdParty, context);
            System.err.println(resultMap);
        }
        return null;
    }



}
