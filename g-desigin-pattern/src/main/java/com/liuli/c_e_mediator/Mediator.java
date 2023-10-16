package com.liuli.c_e_mediator;



import java.util.HashMap;
import java.util.Map;

/**
 * @author pcc
 * 这是中介者模式的中介：房产中介
 */
public class Mediator {

    // 中介者需要存储被注册的房客和房主的信息
    Map<String,String> map = new HashMap<>();

    // 注册房客和房主
    public void register(String key,String value){
        if(map.containsKey(key)){
            return;
        }
        map.put(key,value);
    }

    // 获取房客和房主的信息
    public String get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }
        return null;
    }
}





