package com.liuli.a_a_easyFactory;

/**
 * 兰州面
 *
 * @author zhanghui
 * @date 2023/10/16 13:59:29
 */
public class LanZhouNoodle implements Food{
    @Override
    public void addSpicy(String spicy) {
        System.out.println("add spicy:" + spicy);
    }

    @Override
    public void addCondiment(String condiment) {
        System.out.println("addCondiment:" + condiment);
    }
}
