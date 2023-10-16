package com.liuli.data.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.liuli.cmp.bean.CheckContext;
import com.liuli.cmp.bean.Order2Context;
import com.liuli.cmp.bean.OrderContext;

import com.liuli.data.entity.Chain;
import com.liuli.data.service.ChainService;
import com.liuli.data.service.impl.XsSendServiceImpl;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hui-zhang
 * @since 2023-06-14
 */
@RestController
@RequestMapping("/chain")
@Api(tags = "执行链")
public class ChainController {

    @Autowired
    private ChainService chainService;

    @Resource
    private FlowExecutor customFlowExecutor;

    /**
     * 普通
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 16:52:33
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/ordinary")
    @ApiOperation("普通操作")
    public String ordinary() {
        customFlowExecutor.reloadRule();
        //通过flowExecutor输入参数chainId来找到对应的执行链，
        LiteflowResponse response = customFlowExecutor.execute2Resp("chain1", "arg");
        String result;
        //更改执行链顺序
        if (response.isSuccess()) {
            result = "执行成功";
        } else {
            result = "执行失败";
        }
        chainService.update(new LambdaUpdateWrapper<Chain>().set(Chain::getElData, "THEN(a, c, s1, b, d);").eq(Chain::getChainName, "chain1"));
        // 重新加载规则
        customFlowExecutor.reloadRule();
        //重新执行一遍
        response = customFlowExecutor.execute2Resp("chain1", "arg");
        //更改执行链顺序
        chainService.update(new LambdaUpdateWrapper<Chain>().set(Chain::getElData, "THEN(a, b, s1, c, d);").eq(Chain::getChainName, "chain1"));
        customFlowExecutor.reloadRule();
        return result;
    }

    /**
     * groovy1 ->执行链THEN(a,b,c,groovy1);
     * groovy1:把OrderContext、CheckContext、Order2Context的字节码文件传入进去
     *
     * @ContextBean命名的类会被扫描到，然后会被放入到上下文中,没有value值的话，就是类名首字母小写
     * @ContextBean("order")代表把OrderContext放入到上下文中，key为order
     * Order2Context不声明也可以直接加入到上下文中，但是key为order2Context
     * order.setOrderNo("order1")  给加入的order对象赋值
     * checkContext.setSign("sign1") 给加入的checkContext对象赋值
     * order2Context.setOrderNo("order2") 给加入的order2Context对象赋值
     * 所以在执行结果后通过response.getContextBean(OrderContext.class)获取到的orderNo为order1
     * 通过response.getContextBean(CheckContext.class)获取到的sign为sign1
     * 通过response.getContextBean(Order2Context.class)获取到的orderNo为order2
     *
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 16:52:24
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/groovy1")
    @ApiOperation("groovy1操作")
    public String groovy1(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("groovy_test1", "arg", OrderContext.class, CheckContext.class,
                Order2Context.class);
        OrderContext orderContext = response.getContextBean(OrderContext.class);
        CheckContext checkContext = response.getContextBean(CheckContext.class);
        Order2Context order2Context = response.getContextBean(Order2Context.class);
        System.err.println(orderContext.getOrderNo());
        System.err.println(checkContext.getSign());
        System.err.println(order2Context.getOrderNo());
        return "success";
    }


    /**
     * groovy2 -> 执行链是THEN(a,b,c,groovy2),把已经设置好值的对象放入的执行过程中，在执行过程调用对象的属性去操作
     * def orderNo = order.getOrderNo()
     * println orderNo
     * def sign = checkContext.getSign()
     * println sign
     * def orderNo2 = order2Context.getOrderNo()
     * println orderNo2
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 16:52:20
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/groovy2")
    @ApiOperation("groovy2操作")
    public String groovy2(){
        OrderContext orderContext = new OrderContext();
        orderContext.setOrderNo("order1");
        CheckContext checkContext = new CheckContext();
        checkContext.setSign("sign1");
        Order2Context orderContext2 = new Order2Context();
        orderContext2.setOrderNo("order2");
        LiteflowResponse response = customFlowExecutor.execute2Resp("groovy_test2", null, orderContext, checkContext,
                orderContext2);
        return "success";
    }


    /**
     * groovy3 -> 执行链是IF(groovy3, THEN(a, b)) groovy3返回true的话，执行THEN(a, b)
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 16:52:17
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/groovy3")
    @ApiOperation("groovy3操作")
    public String groovy3(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("groovy_test3", "arg");
        System.err.println(response.getExecuteStepStrWithoutTime());
        return "success";
    }

    /**
     * groovy4 -> 执行链是IF(x1, THEN(a, b), THEN(c, d)); x1返回true的话，执行THEN(a, b)；否则执行THEN(c, d)
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/09 16:52:14
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/groovy4")
    @ApiOperation("groovy4操作")
    public String groovy4(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("groovy_test4", "arg");
        if (response.isSuccess()) {
            System.err.println("执行成功");
        } else {
            System.err.println("执行失败");
        }
        System.err.println(response.getExecuteStepStrWithoutTime());
        return "success";
    }


    @GetMapping("/chain5555")
    @ApiOperation("chain5555")
    public String chain5555(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("chain5555", "arg");
        if (response.isSuccess()) {
            System.err.println("执行成功");
        } else {
            System.err.println("执行失败");
        }
        return "success";
    }


    @Autowired
    private XsSendServiceImpl xsSendService;

    @GetMapping("/chain6666")
    @ApiOperation("chain6666")
    public String chain6666(){
        LiteflowResponse response = customFlowExecutor.execute2Resp("chain6666", null,xsSendService);
        if (response.isSuccess()) {
            System.err.println("执行成功");
        } else {
            System.err.println("执行失败");
        }
        return "success";
    }



    @GetMapping("/chain7777")
    @ApiOperation("chain7777")
    public String chain7777(){
        //通用类 包含 customFlowExecutor，昨天说的各种dataMap，nextStepName,lastStepName
        LiteflowResponse response = customFlowExecutor.execute2Resp("chain7777", null,customFlowExecutor);
        if (response.isSuccess()) {
            System.err.println("执行成功");
        } else {
            System.err.println("执行失败");
        }
        return "success";
    }

}

