package com.liuli.b_functional_programming.function;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * 函数组成
 * compose 先执行fun2,再执行fun1 跟andThen相反
 *
 * @author zhanghui
 * @date 2023/10/16 11:24:39
 */
public class FunctionCompose {
 
    public static void main(String[] args) {
        functionCompose();
    }
 
    /**
     * 先执行fun2 100*100 转换为String类型 再执行fun1把String转换为BigDecimal
     */
    public static void functionCompose() {
        BigDecimal amount = typeConvert2(100, BigDecimal::new, s2 -> String.valueOf(s2 * s2));
        //function --> 10000
        System.out.printf("function --> {%s}%n", amount);
    }
 
 
    /**
     * 传入number 先执行fun2 Integer转String 再执行fun1 String 转泛型R
     */
    public static <R> R typeConvert2(Integer number, Function<String, R> fun1, Function<Integer, String> fun2) {
        return fun1.compose(fun2).apply(number);
    }
 
}