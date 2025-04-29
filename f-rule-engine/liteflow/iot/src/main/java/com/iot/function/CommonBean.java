package com.iot.function;

import cn.hutool.extra.spring.SpringUtil;
import com.iot.database.service.IPlConstantService;
import com.iot.database.service.IPlOtherNodeLockService;
import com.iot.database.service.IPlScriptNodeService;
import com.iot.util.HandleFunctionRedisUtil;
import com.yomahub.liteflow.core.FlowExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;


/**
 * 通用bean对象
 *
 * @author zhanghui
 * @date 2023/09/14 14:35:50
 */
public abstract class CommonBean {
    /**
     * redis模板
     */
    public final StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    public final IPlScriptNodeService plScriptNodeService = SpringUtil.getBean(IPlScriptNodeService.class);
    public final IPlConstantService constantService = SpringUtil.getBean(IPlConstantService.class);
    public final FlowExecutor flowExecutor = SpringUtil.getBean(FlowExecutor.class);
    public final IPlOtherNodeLockService plOtherNodeLockService = SpringUtil.getBean(IPlOtherNodeLockService.class);
    public final HandleFunctionRedisUtil handleFunctionRedisUtil = SpringUtil.getBean(HandleFunctionRedisUtil.class);

}
