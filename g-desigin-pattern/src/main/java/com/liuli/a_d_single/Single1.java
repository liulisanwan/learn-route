package com.liuli.a_d_single;

/**
 * 静态属性单例模式
 *
 * @author zhanghui
 * @date 2023/09/21 17:12:50
 */
public class Single1 {
    private static final Single1 singleton = new Single1();

    // 核心点是私有化构造器，让外部无法进行新对象创建
    private Single1() {
    }
    
    public static Single1 getInstance() {
        return singleton;
    }
}
