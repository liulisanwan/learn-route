package com.liuli.b_functional_programming.consumer;

import java.util.function.Consumer;

/**
 * 消费者顺序执行
 *
 * @author zhanghui
 * @date 2023/10/16 11:27:36
 */
public class ConsumerAndThen {
 
    public static void main(String[] args) {
        consumerAndThen();
    }
 
    /**
     * 消费接口。消费的内容是一个函数式接口。s + s = 246  c - 1 = 122
     */
    public static void consumerAndThen() {
        /* 输出结果 consumerAndThen1 --> 246 consumerAndThen2 --> 122 */
        handler(123, s -> System.out.printf("consumerAndThen1 --> {%s}%n", (s + s)), c -> System.out.printf("consumerAndThen2 --> {%s}%n", (c - 1)));
    }
 
    /**
     * 它直接return一个匿名Consumer，这个匿名Consumer实现了accept方法，然后调用其accept方法。
     * 咋实现的呢？
     * 就是把consumer2传进去，先调用原本的consumer1的accept，再调用的consumer2的accept
     *
     * Consumer 消费接口，传入一个泛型参数
     * Consumer接口的默认方法andThen()
     * 作用:需要两个consumer接口,可以把两个consumer接口组合到一起,在对数据进行消费
     * 谁写前边谁先消费
     * 此方法返回一个组合的Consumer，该Consumer先执行原始的Consumer操作，然后按照从左到右的顺序执行给定的andThen操作
     */
    public static <T> void handler(T t, Consumer<T> consumer1, Consumer<T> consumer2) {
        consumer1.andThen(consumer2).accept(t);
    }
 
}