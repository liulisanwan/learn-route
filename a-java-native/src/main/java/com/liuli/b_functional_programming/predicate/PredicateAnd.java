package com.liuli.b_functional_programming.predicate;

import java.util.function.Predicate;

/**
 * 谓词和
 *
 * @author zhanghui
 * @date 2023/10/16 11:28:49
 */
public class PredicateAnd {
 
    public static void main(String[] args) {
        predicateAndTest();
    }
 
    public static void predicateAndTest() {
        boolean flag = predicateAnd("张三", "张三"::equals, s -> s.length() == 12);
        //输出：predicateAndTest-->true
        System.out.printf("predicateAndTest-->{} {%s}%n", flag);
 
        flag = predicateAnd("张三", "张三"::equals, s -> s.length() == 2);
        //输出：predicateAndTest-->false
        System.out.printf("predicateAndTest-->{} {%s}%n", flag);
    }
 
    /**
     * 先执行predicate，再执行predicate2 and连接 两者必须都是true 结果才是true
     */
    public static <T> boolean predicateAnd(T t, Predicate<T> predicate, Predicate<T> predicate2) {
        return predicate.and(predicate2).test(t);
    }
 
}