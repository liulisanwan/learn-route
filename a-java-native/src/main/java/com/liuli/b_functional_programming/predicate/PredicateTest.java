package com.liuli.b_functional_programming.predicate;

import java.util.function.Predicate;

/**
 * 谓词测试
 *
 * @author zhanghui
 * @date 2023/10/16 11:29:08
 */
public class PredicateTest {
 
    public static void main(String[] args) {
        predicateTest();
    }
 
    public static void predicateTest() {
        boolean flag = predicateTrueOrFalse("张三", "张三"::equals);
        //输出：predicateTest-->true
        System.out.printf("predicateTest--> {%s}%n", flag);
    }
 
    /**
     * Predicate接口：
     * java.util.function.Predicate接口
     * 作用：对某种数据类型的数据进行判断，结果返回一个boolean值
     *
     * 抽象方法：test:
     * Predicate接口中包含了一个抽象方法：
     * boolean test(T t):用来对指定数据类型数据进行判断的方法
     * 结果;
     * 符合条件，返回true
     * 不符合条件，返回false
     */
    public static <T> boolean predicateTrueOrFalse(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }
   
}