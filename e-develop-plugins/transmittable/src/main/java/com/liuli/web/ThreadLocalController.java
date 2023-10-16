package com.liuli.web;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程本地控制器
 *
 * @author zhanghui
 * @date 2023/10/16 14:49:08
 */
@RestController
@RequestMapping("threadLocal")
@Slf4j
@Api("错误示范1")
public class ThreadLocalController {

    private static final ThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();

    private static final ExecutorService executorService = new ThreadPoolExecutor(1,1,600L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    int i=0;

    /**
     * 错误
     * 2023-08-08 13:52:33.477  INFO 11288 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController       : 主线程开始
     * 2023-08-08 13:52:33.477  INFO 11288 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController       : 主线程set值为: 1
     * 2023-08-08 13:52:33.478  INFO 11288 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController       : 主线程结束
     * 2023-08-08 13:52:34.388  INFO 11288 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController       : 主线程开始
     * 2023-08-08 13:52:34.388  INFO 11288 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController       : 主线程set值为: 2
     * 2023-08-08 13:52:34.388  INFO 11288 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController       : 主线程结束
     * 2023-08-08 13:52:36.478  INFO 11288 --- [pool-3-thread-1] com.demo.web.ThreadLocalController       : 子线程get值为: 1
     * 2023-08-08 13:52:39.479  INFO 11288 --- [pool-3-thread-1] com.demo.web.ThreadLocalController       : 子线程get值为: 1
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 13:28:21
     * @see String
     * @since 1.0.0
     */
    @ApiOperation("错误示范")
    @GetMapping("/wrong")
    public String wrong(){
        log.info("主线程开始");
        threadLocal.set(++i);
        log.info("主线程set值为: " + threadLocal.get());
        executorService.execute(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            log.info("子线程get值为: " + threadLocal.get());
        });
        threadLocal.remove();
        log.info("主线程结束");
        return "success";
    }


    /**
     * 正确
     * 2023-08-08 13:53:00.958  INFO 11288 --- [nio-8080-exec-6] com.demo.web.ThreadLocalController       : 主线程set值为: 3
     * 2023-08-08 13:53:00.959  INFO 11288 --- [nio-8080-exec-6] com.demo.web.ThreadLocalController       : 主线程结束
     * 2023-08-08 13:53:02.090  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController       : 主线程开始
     * 2023-08-08 13:53:02.090  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController       : 主线程set值为: 4
     * 2023-08-08 13:53:02.090  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController       : 主线程结束
     * 2023-08-08 13:53:03.250  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController       : 主线程开始
     * 2023-08-08 13:53:03.250  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController       : 主线程set值为: 5
     * 2023-08-08 13:53:03.251  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController       : 主线程结束
     * 2023-08-08 13:53:03.960  INFO 11288 --- [pool-3-thread-1] com.demo.web.ThreadLocalController       : 子线程get值为: 3
     * 2023-08-08 13:53:06.960  INFO 11288 --- [pool-3-thread-1] com.demo.web.ThreadLocalController       : 子线程get值为: 4
     * 2023-08-08 13:53:09.961  INFO 11288 --- [pool-3-thread-1] com.demo.web.ThreadLocalController       : 子线程get值为: 5
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 13:29:33
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/correct")
    @ApiOperation("正确示范")
    public String correct(){
        log.info("主线程开始");
        threadLocal.set(++i);
        log.info("主线程set值为: " + threadLocal.get());
        executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            log.info("子线程get值为: " + threadLocal.get());
        })));
        threadLocal.remove();
        log.info("主线程结束");
        return "success";
    }
}
