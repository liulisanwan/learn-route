package com.iot.custom_node;

import cn.hutool.extra.spring.SpringUtil;
import com.iot.database.service.IPlControlService;
import com.iot.database.service.IPlListenService;
import com.iot.database.service.IPlScriptNodeService;
import com.iot.database.service.IPlThirdPartyService;
import com.iot.util.HandleFunctionRedisUtil;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 节点执行服务
 *
 * @author zhanghui
 * @date 2023/09/13 14:40:01
 */
public interface CustomNodeExecuteService<T> {

    Logger log = LoggerFactory.getLogger(CustomNodeExecuteService.class);

    StringRedisTemplate redisTemplate = SpringUtil.getBean(StringRedisTemplate.class);
    IPlScriptNodeService scriptNodeService = SpringUtil.getBean(IPlScriptNodeService.class);
    HandleFunctionRedisUtil FUNCTION_UTIL = SpringUtil.getBean(HandleFunctionRedisUtil.class);
    IPlListenService listenService = SpringUtil.getBean(IPlListenService.class);
    IPlControlService controlService = SpringUtil.getBean(IPlControlService.class);
    IPlThirdPartyService thirdPartyService = SpringUtil.getBean(IPlThirdPartyService.class);

    /**
     * 获取执行类型
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/09/13 15:02:12
     * @see String
     * @since 1.0.0
     */
    String getExecuteType();


    /**
     * 执行
     *
     * @param executeWrap 执行WRAP
     * @author zhanghui
     * @date 2023/09/13 15:02:09
     * @see ScriptExecuteWrap
     * @since 1.0.0
     */
     T execute(ScriptExecuteWrap executeWrap);
}
