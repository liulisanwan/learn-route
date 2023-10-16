package com.liuli.a_c_abstractFactory;


/**
 * 华为工厂
 *
 * @author zhanghui
 * @date 2023/10/16 14:01:01
 */
public class HuaWeiFactory implements Factory {
    @Override
    public TV getInstanceTV() {
        return new TV();
    }

    @Override
    public Phone getInstancePhone() {
        return new Phone();
    }

    @Override
    public Computer getInstanceComputer() {
        return new Computer();
    }
}
