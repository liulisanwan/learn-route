package com.liuli.a_a_easyFactory;

/**
 * 黄门鸡
 *
 * @author zhanghui
 * @date 2023/10/16 13:59:25
 */
public class HuangMenChicken implements Food{
    @Override
    public void addSpicy(String spicy) {
        System.out.println("add spicy:" + spicy);
    }

    @Override
    public void addCondiment(String condiment) {
        System.out.println("addCondiment:" + condiment);
    }
}
