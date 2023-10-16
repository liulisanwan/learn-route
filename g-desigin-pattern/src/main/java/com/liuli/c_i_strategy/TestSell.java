package com.liuli.c_i_strategy;

/**
 * 试销
 *
 * @author zhanghui
 * @date 2023/09/21 17:39:26
 */
public class TestSell {
    public static void main(String[] args) {
        Supplier supplier1 = new Supplier(new Strategy618());
        Supplier supplier2 = new Supplier(new Strategy1111());
        supplier1.sell();
        supplier2.sell();
    }
}
