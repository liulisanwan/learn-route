package com.liuli.a_b_factory;


/**
 * 美国食品厂
 *
 * @author zhanghui
 * @date 2023/10/16 13:59:53
 */
public class AmericanFoodFactory implements FoodFactory {
 
    @Override
    public Food makeFood(String name) {
        if (name.equals("A")) {
            return new AmericanFoodA();
        } else if (name.equals("B")) {
            return new AmericanFoodB();
        } else {
            return null;
        }
    }
 
}