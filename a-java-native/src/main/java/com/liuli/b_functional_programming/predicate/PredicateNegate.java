package com.liuli.b_functional_programming.predicate;

import java.util.function.Predicate;

/**
 * 谓语否定
 *
 * @author zhanghui
 * @date 2023/10/16 11:28:31
 */
public class PredicateNegate {
 
    public static void main(String[] args) {
        predicateNegateTest();
    }
 
    public static void predicateNegateTest() {
        boolean flag = predicateNegate("张三", "张三"::equals);
        //输出：predicateNegateTest-->false
        System.out.printf("predicateNegateTest-->{} {%s}%n", flag);
    }
 
    /**
     * 执行predicate 取反操作
     */
    public static <T> boolean predicateNegate(T t, Predicate<T> predicate) {
        return predicate.negate().test(t);
    }
 
}