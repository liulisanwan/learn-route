package com.liuli.b_functional_programming.function;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 函数调用其本身
 * identity 数值为本身 s -> s 替换为 Function.identity()
 *
 * @author zhanghui
 * @date 2023/10/16 11:25:16
 */
public class FunctionIdentity {
 
    public static void main(String[] args) {
        identity();
    }
 
    /**
     * 把List转为Map, s -> s 表示map的key值，key的数值为本身，可以简写为
     */
    public static void identity() {
        List<String> strings = Arrays.asList("abc", "de");
        //数值为本身 Function.identity() 替换 s -> s，Collectors.toMap(s -> s, String::length));
        Map<String, Integer> map = strings.stream().collect(Collectors.toMap(Function.identity(), String::length));
        //function --> {de=2, abc=3}
        System.out.printf("function --> {%s}%n", map);
    }
 
}