package com.liuli.factory;

import com.yomahub.liteflow.script.ScriptExecuteWrap;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class NodeExecuteFactory implements ApplicationContextAware {

    private Map<String, NodeExecuteService> map=new ConcurrentHashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //将实现NodeExecuteService接口的bean放进map中
        Map<String, NodeExecuteService> serviceMap = applicationContext.getBeansOfType(NodeExecuteService.class);
        serviceMap.values().forEach(nodeExecuteService -> {
            map.put(nodeExecuteService.getExecuteType(), nodeExecuteService);
        });
        System.out.println(serviceMap);
        for (String s : serviceMap.keySet()) {
            System.out.println(s+"==========="+map.get(s));
        }
    }


    public Object execute(ExecuteType key, ScriptExecuteWrap params){
        NodeExecuteService executeService = map.get(key.getType());
        if (executeService==null){
            throw new RuntimeException("没有此类");
        }
        //具体执行
        Object execute = executeService.execute(params);
        return execute;
    }
}
