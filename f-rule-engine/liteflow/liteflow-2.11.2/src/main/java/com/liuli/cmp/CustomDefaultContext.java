package com.liuli.cmp;

import cn.hutool.core.util.ObjectUtil;
import com.yomahub.liteflow.exception.NullParamException;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hanxiaowei
 * 自定义上下文对象
 * @date 2023/9/8 14:25
 */
@Data
public class CustomDefaultContext {
    public ConcurrentHashMap<String, Object> dataMap = new ConcurrentHashMap<>();

    public <T> void putDataMap(String key, T t) {
        if (ObjectUtil.isNull(t)) {
            throw new NullParamException("数据不能接受null参数");
        }
        dataMap.put(key, t);
    }

    public boolean hasData(String key) {
        return dataMap.containsKey(key);
    }

    public <T> T getData(String key) {
        T t = (T) dataMap.get(key);
        return t;
    }

    public <T> void setData(String key, T t) {
        putDataMap(key, t);
    }

    public void removeData(String key) {
        if (dataMap.contains(key)){
            dataMap.remove(key);
        }
    }


    public ConcurrentHashMap<String, Object> getDataMap() {
        return dataMap;
    }

}
