package com.iot.database.controller;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.iot.database.entity.PlChain;
import com.iot.database.entity.PlScriptNode;
import com.iot.database.service.IPlChainService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Pl链控制器
 * <p>
 * 执行链表 前端控制器
 * </p>
 *
 * @author zhanghui
 * @date 2023/09/12 14:26:39
 * @since 2023-09-06
 */
@RestController
@RequestMapping("/plChain")
public class PlChainController {



}

