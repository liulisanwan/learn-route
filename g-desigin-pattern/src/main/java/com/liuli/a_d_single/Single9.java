package com.liuli.a_d_single;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册表单例->spring中的单例模式
 *
 * @author zhanghui
 * @date 2023/09/21 17:15:12
 */
public class Single9 {
    private static ConcurrentHashMap<String,Object> hashMap = new ConcurrentHashMap<>();

    public static Object getInstance(String key) throws Exception{
        if(hashMap.get(key) == null){
            hashMap.put(key,Class.forName(key).newInstance());
        }
        return hashMap.get(key);
    }

    // 测试
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                try {
                    System.out.println(getInstance("com.example.design.pattern.Single.Single9"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
