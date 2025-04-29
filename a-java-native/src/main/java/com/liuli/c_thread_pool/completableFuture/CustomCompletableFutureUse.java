package com.liuli.c_thread_pool.completableFuture;


import com.liuli.c_thread_pool.thread_pool_executor.CustomThreadPoolExecutorUse;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义可完成未来使用
 *
 * @author zhanghui
 * @date 2023/10/16 11:34:46
 */
public class CustomCompletableFutureUse {

    public ThreadPoolExecutor EXECUTOR_SERVICE= CustomThreadPoolExecutorUse.createThreadPool(Integer.MAX_VALUE,"CustomCompletableFutureUse");

    public void runAsync(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, EXECUTOR_SERVICE);
    }

    /**
     * 完成时异步供电
     * supplyAsync：有返回值
     * 「whenComplete：能感知异常，能感知结果，但没办法给返回值
     * exceptionally：能感知异常，不能感知结果，能给返回值。相当于，如果出现异常就返回这个值」
     * @author zhanghui
     * @date 2023/10/16 11:43:26
     * @since 1.0.0
     */
    public void supplyAsyncWhenComplete(){
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 0;
            System.out.println("运行结果：" + i);
            return i;
        }, EXECUTOR_SERVICE).whenComplete((res, exception) -> {
            //whenComplete虽然能得到异常信息，但是没办法修改返回值
            System.out.println("异步任务成功完成...结果是：" + res + ";异常是：" + exception);
        }).exceptionally(throwable -> {
            //exceptionally能感知异常，而且能返回一个默认值，相当于，如果出现异常就返回这个值
            return 10;
        });
    }

    /**
     * 供给异步句柄
     * supplyAsync有返回值
     * handle能拿到返回结果，也能得到异常信息，也能修改返回值
     * @author zhanghui
     * @date 2023/10/16 11:43:22
     * @since 1.0.0
     */
    public void supplyAsyncHandle(){
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程" + Thread.currentThread().getId());
            int i = 10 / 4;
            System.out.println("运行结果：" + i);
            return i;
        }, EXECUTOR_SERVICE).handle((res, exception) -> {
            if (exception != null) {
                return 0;
            } else {
                return res * 2;
            }
        });
    }
}
