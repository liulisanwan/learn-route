package com.iot.custom_node;

import com.iot.exception.CustomNodeNotFoundException;
import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义节点执行工厂
 *
 * @author zhanghui
 * @date 2023/10/19 10:38:45
 */
@Component
public class CustomNodeExecuteFactory implements ApplicationContextAware {

    private Map<String, CustomNodeExecuteService> map=new ConcurrentHashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //将实现NodeExecuteService接口的bean放进map中
        Map<String, CustomNodeExecuteService> serviceMap = applicationContext.getBeansOfType(CustomNodeExecuteService.class);
        serviceMap.values().forEach(nodeExecuteService -> {
            map.put(nodeExecuteService.getExecuteType(), nodeExecuteService);
        });
        System.out.println(serviceMap);
        for (String s : serviceMap.keySet()) {
            System.out.println(s+"==========="+map.get(s));
        }
    }


    public Object execute(CustomNodeExecuteType key, ScriptExecuteWrap params){
        CustomNodeExecuteService executeService = map.get(key.getType());
        if (executeService==null){
            throw new CustomNodeNotFoundException("没有此类");
        }
        //具体执行
        Object execute = executeService.execute(params);
        return execute;
    }
}
