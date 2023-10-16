package com.liuli.web;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程本地控制器2
 *
 * @author zhanghui
 * @date 2023/10/16 14:49:10
 */
@RestController
@RequestMapping("threadLocal2")
@Slf4j
@Api("错误示范2")
public class ThreadLocalController2 {

    private static final ThreadLocal<Map<Integer,Integer>> threadLocal = new TransmittableThreadLocal<>();

    private static final ExecutorService executorService = new ThreadPoolExecutor(1,1,600L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    int i=0;

    /**
     * 错误,使用类静态代码块加载资源，加载后的map对象会是main线程的，子线程再次获取直接获取main线程的map对象
     * 所有在remove没有办法remove掉main线程的map对象，在已经remove过的子线程中还是获取的main线程的map对象
     * 导致会出现map{1=1,2=2},而不是map{1=1},内存对象越来越多
     * 屏蔽后，每次都会初始化map对象，不会出现内存对象越来越多的情况
     * 需重新运行项目
     */
    static {
//        init();
    }

    /**
     * 错误 多次访问过出现异常情况
     * 2023-08-08 13:51:23.364  INFO 11288 --- [nio-8080-exec-6] com.demo.web.ThreadLocalController2      : 主线程set值为: {1=1, 2=2, 3=3}
     * 2023-08-08 13:51:23.365  INFO 11288 --- [nio-8080-exec-6] com.demo.web.ThreadLocalController2      : 主线程结束
     * 2023-08-08 13:51:24.411  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController2      : 主线程开始
     * 2023-08-08 13:51:24.411  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController2      : 主线程set值为: {1=1, 2=2, 3=3, 4=4}
     * 2023-08-08 13:51:24.411  INFO 11288 --- [nio-8080-exec-8] com.demo.web.ThreadLocalController2      : 主线程结束
     * 2023-08-08 13:51:25.367  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController2      : 主线程开始
     * 2023-08-08 13:51:25.367  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController2      : 主线程set值为: {1=1, 2=2, 3=3, 4=4, 5=5}
     * 2023-08-08 13:51:25.368  INFO 11288 --- [io-8080-exec-10] com.demo.web.ThreadLocalController2      : 主线程结束
     * 2023-08-08 13:51:26.366  INFO 11288 --- [pool-4-thread-1] com.demo.web.ThreadLocalController2      : 子线程get值为: {1=1, 2=2, 3=3, 4=4, 5=5}
     * 2023-08-08 13:51:29.367  INFO 11288 --- [pool-4-thread-1] com.demo.web.ThreadLocalController2      : 子线程get值为: {1=1, 2=2, 3=3, 4=4, 5=5}
     * 2023-08-08 13:51:32.368  INFO 11288 --- [pool-4-thread-1] com.demo.web.ThreadLocalController2      : 子线程get值为: {1=1, 2=2, 3=3, 4=4, 5=5}
     *
     * 正确示范
     * 2023-08-08 13:55:01.942  INFO 12396 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController2      : 主线程开始
     * 2023-08-08 13:55:01.942  INFO 12396 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController2      : 初始化
     * 2023-08-08 13:55:01.943  INFO 12396 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController2      : 主线程set值为: {2=2}
     * 2023-08-08 13:55:01.945  INFO 12396 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController2      : 主线程结束
     * 2023-08-08 13:55:02.939  INFO 12396 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController2      : 主线程开始
     * 2023-08-08 13:55:02.939  INFO 12396 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController2      : 初始化
     * 2023-08-08 13:55:02.939  INFO 12396 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController2      : 主线程set值为: {3=3}
     * 2023-08-08 13:55:02.939  INFO 12396 --- [nio-8080-exec-4] com.demo.web.ThreadLocalController2      : 主线程结束
     * 2023-08-08 13:55:04.946  INFO 12396 --- [pool-4-thread-1] com.demo.web.ThreadLocalController2      : 子线程get值为: {2=2}
     * 2023-08-08 13:55:07.947  INFO 12396 --- [pool-4-thread-1] com.demo.web.ThreadLocalController2      : 子线程get值为: {3=3}
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 13:28:21
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/ttl")
    @ApiOperation(value = "示范")
    public String ttl(){
        log.info("主线程开始");
        Map<Integer, Integer> map = threadLocal.get();
        if (map==null){
            init();
        }
        threadLocal.get().put(++i,i);
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

    private synchronized static  void init() {
        log.info("初始化");
        if (threadLocal.get()==null){
            threadLocal.set(new LinkedHashMap<>());
        }
    }




}
