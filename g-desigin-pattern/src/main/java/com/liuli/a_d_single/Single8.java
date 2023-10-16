package com.liuli.a_d_single;

/**
 * 枚举单例
 *
 * @author zhanghui
 * @date 2023/09/21 17:14:46
 */
public enum Single8 {
    INSTANCE("zhangsan");
    private String name;

    private  Single8(String name) {
        this.name = name;
    }

    public static Single8 getInstance() {
        return INSTANCE;
    }

}
