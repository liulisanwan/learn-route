package com.liuli.a_d_single;

/**
 * 静态属性变种
 *
 * @author zhanghui
 * @date 2023/09/21 17:13:05
 */
public class Single2 {

    public static final Single2 singleTwo;

    static {
        singleTwo = new Single2();
    }

    private Single2() {

    }

    public static Single2 getInstance() {
        return singleTwo;
    }
}
