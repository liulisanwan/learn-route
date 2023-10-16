package com.liuli.web;


import com.google.common.util.concurrent.*;
import com.liuli.MyCallable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;


/**
 * 监听线程池控制器
 *
 * @author zhanghui
 * @date 2023/08/24 16:46:02
 */
@RestController
@RequestMapping("/listeningExecutor")
@Api(tags = "监听线程池")
@Slf4j
public class ListeningExecutorController {


    /**
     * 正常执行
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:29:21
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/normalFuture")
    @ApiOperation(value = "普通Future")
    public Integer normalFuture() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new MyCallable(3, 10));
        // get方法会阻塞，直至获取结果
        Integer result = future.get();
        log.info("result: {}", result);
        executor.shutdown();
        return result;
    }

    ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());

    /**
     * 监听器
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:29:37
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureOne")
    @ApiOperation(value = "监听器")
    public String listeningFutureOne(){
        ListenableFuture<Integer> listenableFuture = executorService.submit(new MyCallable(3, 10));
        listenableFuture.addListener(() -> {
            log.info("listen success");
            log.info("doSomeThing");
        }, executorService);
        return "1";
    }


    /**
     * 回调函数
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:29:48
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureTwo")
    @ApiOperation(value = "回调函数")
    public String listeningFutureTwo(){
        ListenableFuture<Integer> listenableFuture = executorService.submit(new MyCallable(3, 10));
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(@Nullable Object result) {
                log.info("res: {}", result);
            }

            @Override
            public void onFailure(Throwable t) {}
        }, executorService);
        return "1";
    }


    /**`
     * 合并后同时获取执行结果或处理异常
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:29:56
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureThree")
    @ApiOperation(value = "合并")
    public String listeningFutureThree(){
        ListenableFuture<Integer> future1 = executorService.submit(() -> 1 + 2);
        ListenableFuture<Integer> future2 = executorService.submit(() -> Integer.parseInt("3q"));
        ListenableFuture<List<Object>> futures = Futures.successfulAsList(future1, future2);

        Futures.addCallback(futures, new FutureCallback<List<Object>>() {
            @Override
            public void onSuccess(@Nullable List<Object> result) {
                log.info("res: {}", result);
            }
            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        }, executorService);
        return "1";
    }


    /**
     * 同步结果转换类似于future.get().stream().mapToInt(String::length);
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:30:17
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureFour")
    @ApiOperation(value = "同步结果转换")
    public String listeningFutureFour() {

        ListenableFuture<String> future3 = executorService.submit(() -> "hello, future");
        log.info("future3执行结束");
        ListenableFuture<Integer> future5 = Futures.transform(future3, s -> {
            try {
                log.info("future5执行开始");
                Thread.sleep(1000);
                log.info("future5执行结束");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return s.length();
        }, executorService);
        return "1";
    }




    @GetMapping("/listeningFutureFive")
    @ApiOperation(value = "异步结果转换")
    public String listeningFutureFive(){
        log.info("future3执行开始");
        ListenableFuture<String> future3 = executorService.submit(() -> "hello, future");
        ListenableFuture<Integer> future6 = Futures.transformAsync(future3, s -> {
            log.info("future3执行结束");
            return executorService.submit(() ->{
                log.info("future6开始执行");
                Thread.sleep(2000);
                log.info("future6执行结束");
                return s.length();
            });
        }, executorService);
        return "1";
    }

    /**
     * 类型转换
     *
     * @return {@code String }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @author zhanghui
     * @date 2023/08/24 17:40:22
     * @see String
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureSix")
    @ApiOperation(value = "类型转换")
    public String listeningFutureSix() throws ExecutionException, InterruptedException {
        Future<String> stringFuture = Executors.newCachedThreadPool().submit(() -> "hello,world");
        ListenableFuture<String> future7 = JdkFutureAdapters.listenInPoolThread(stringFuture);
        return future7.get();
    }

    /**
     * 异步转同步 通过同步方式获取异步内数据
     *
     * @return {@code Integer }
     * @throws ExecutionException   执行异常
     * @throws InterruptedException 中断异常
     * @throws TimeoutException     超时异常
     * @author zhanghui
     * @date 2023/08/24 17:40:34
     * @see Integer
     * @since 1.0.0
     */

    @GetMapping("/listeningFutureSeven")
    @ApiOperation(value = "异步转同步")
    public Integer listeningFutureSeven() throws ExecutionException, InterruptedException, TimeoutException {
        SettableFuture<Integer> settableFuture = SettableFuture.create();
        ListenableFuture<Integer> future11 = executorService.submit(() -> {
            int sum = 5 + 6;
            settableFuture.set(sum);
            Thread.sleep(5000);
            log.info("5s后结果为: {}", sum);
            return sum;
        });
        log.info("settableFuture.get()开始执行");
        settableFuture.get(2, TimeUnit.SECONDS);
        log.info("settableFuture.get()执行结束");
        return settableFuture.get();
    }
}
