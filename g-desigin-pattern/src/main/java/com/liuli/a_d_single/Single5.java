package com.liuli.a_d_single;

/**
 * 线程安全的懒加载 单例-改进
 *
 * @author zhanghui
 * @date 2023/09/21 17:14:00
 */
public class Single5 {
    private static Single5 instance;

    private Single5(){}

    public static Single5 getInstance(){
        if(instance == null){
            synchronized(Single5.class){
                instance = new Single5();
            }
        }
        return instance;
    }
}
