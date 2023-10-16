package com.liuli.a_b_factory;


/**
 * 食品工厂
 *
 * @author zhanghui
 * @date 2023/10/16 14:00:25
 */
public interface FoodFactory {
 
    Food makeFood(String name);
 
}