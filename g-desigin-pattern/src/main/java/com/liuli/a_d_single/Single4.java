package com.liuli.a_d_single;

/**
 * 线程安全的懒加载单例
 *
 * @author zhanghui
 * @date 2023/09/21 17:13:35
 */
public class Single4 {

    private static Single4 instance;

    private Single4(){}

    public static synchronized Single4 getInstance(){
        if(instance==null){
            return instance =new Single4();
        }
        return instance;
    }
}
