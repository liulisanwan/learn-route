package com.liuli.b_functional_programming.function;

import java.util.function.Function;


/**
 * 函数应用
 *
 * @author zhanghui
 * @date 2023/10/16 11:23:23
 */
public class FunctionApply {
 
    public static void main(String[] args) {
        functionApply();
    }
 
    /**
     * 传入参数字符串，返回参数字符串。字符串转大小写
     */
    public static void functionApply () {
        String result = typeConvert("amy", String::toUpperCase);
        //function --> AMY
        System.out.printf("function --> {%s}%n",  result);
    }
 
    /***
     * Function apply接口  传入一个参数，返回值一个参数
     */
    public static <R> R typeConvert (String str, Function<String, R> function) {
        return function.apply(str);
    }
 
}