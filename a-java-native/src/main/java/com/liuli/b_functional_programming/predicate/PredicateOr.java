package com.liuli.b_functional_programming.predicate;

import java.util.function.Predicate;

/**
 * 谓词或
 *
 * @author zhanghui
 * @date 2023/10/16 11:29:02
 */
public class PredicateOr {
 
    public static void main(String[] args) {
        predicateOrTest();
    }
 
    public static void predicateOrTest() {
        boolean flag = predicateOr("张三", "张三"::equals, s -> s.length() == 12);
        //输出：predicateOrTest-->true
        System.out.printf("predicateAndTest-->{} {%s}%n", flag);
 
        flag = predicateOr("张三", "张三"::equals, s -> s.length() == 2);
        //输出：predicateOrTest-->true
        System.out.printf("predicateAndTest-->{} {%s}%n", flag);
    }
 
    /**
     * 先执行predicate，再执行predicate2 or连接 其中有一个是true 结果就是true
     */
    public static <T> boolean predicateOr(T t, Predicate<T> predicate, Predicate<T> predicate2) {
        return predicate.or(predicate2).test(t);
    }
 
}