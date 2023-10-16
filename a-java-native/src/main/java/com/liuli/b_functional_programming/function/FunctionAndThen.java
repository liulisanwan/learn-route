package com.liuli.b_functional_programming.function;

import java.util.function.Function;


/**
 * 函数顺序执行
 *
 * @author zhanghui
 * @date 2023/10/16 11:23:46
 */
public class FunctionAndThen {
 
    public static void main(String[] args) {
        functionAndThen();
    }
 
    /**
     * 先将字符串100转换未Integer类型，再Integer + 10得到结果 110
     */
    public static void functionAndThen() {
        Integer result = typeConvert("100", Integer::parseInt, s2 -> (s2 + 10));
        //function --> 110
        System.out.printf("function --> {%s}%n", result);
    }
 
    /**
     * 先执行fun1,再执行fun2 传入参数str
     */
    public static <R> R typeConvert(String str, Function<String, Integer> fun1, Function<Integer, R> fun2) {
        return fun1.andThen(fun2).apply(str);
    }
 
}