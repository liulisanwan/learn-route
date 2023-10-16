package com.liuli.a_b_factory;


/**
 * 中国食品厂
 *
 * @author zhanghui
 * @date 2023/10/16 14:00:18
 */
public class ChineseFoodFactory implements FoodFactory {
 
    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new ChineseFoodA();
        } else if (name.equals("B")) {
            return new ChineseFoodB();
        } else {
            return null;
        }
    }
 
}