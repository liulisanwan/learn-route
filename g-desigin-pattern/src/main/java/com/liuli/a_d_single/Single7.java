package com.liuli.a_d_single;

/**
 * 静态内部类
 *
 * @author zhanghui
 * @date 2023/09/21 17:14:25
 */
public class Single7 {

    private Single7(){}
    private static class Singleton{
        private static Single7 single7 = new Single7();
    }

    public static Single7 getInstance(){
        return Singleton.single7;
    }

}
