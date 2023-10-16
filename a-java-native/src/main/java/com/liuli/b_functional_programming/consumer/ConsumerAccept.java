package com.liuli.b_functional_programming.consumer;

import java.util.function.Consumer;

/**
 * 消费者接收
 *
 * @author zhanghui
 * @date 2023/10/16 11:27:21
 */
public class ConsumerAccept {
 
    public static void main(String[] args) {
        consumerAccept();
    }
 
    /**
     * 消费接口。消费的内容是一个函数式接口。s + s = 246
     */
    public static void consumerAccept() {
        /* 输出结果 consumerAccept --> 246 */
        handler(123, s -> System.out.printf("consumerAccept--> {%s}%n", (s + s)));
    }
 
    /**
     * Consumer 消费接口，传入一个泛型参数
     *
     * @param t        传入参数
     * @param consumer 消费
     * @param <T>      泛型
     */
    public static <T> void handler(T t, Consumer<T> consumer) {
        consumer.accept(t);
    }
 
}