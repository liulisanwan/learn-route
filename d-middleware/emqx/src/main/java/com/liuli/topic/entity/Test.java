package com.liuli.topic.entity;


import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("result", "success");
        map.put("imageFilePath","123");
        map.put("msg","{'check':'有占用','color':'red'}");
        System.err.println(JSON.toJSONString(map));
    }
}
