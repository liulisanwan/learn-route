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
 * 线程本地控制器3
 *
 * @author zhanghui
 * @date 2023/10/16 14:49:13
 */
@RestController
@RequestMapping("threadLocal3")
@Slf4j
@Api("错误示范3")
public class ThreadLocalController3 {


    private static final ThreadLocal<Map<Integer,Integer>> threadLocal = new TransmittableThreadLocal<>();

    private static final ExecutorService executorService = new ThreadPoolExecutor(1,1,600L, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    int i=0;


    /**
     * 错误 单次访问过出现异常情况
     * 2023-08-08 13:57:29.503  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 主线程开始
     * 2023-08-08 13:57:29.503  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 初始化
     * 2023-08-08 13:57:29.506  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 主线程set值为: {1=1}
     * 2023-08-08 13:57:29.520  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 二次添加
     * 2023-08-08 13:57:29.520  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 主线程set值为: {1=1, 2=2}
     * 2023-08-08 13:57:29.520  INFO 7868 --- [nio-8080-exec-1] com.demo.web.ThreadLocalController3      : 主线程结束
     * 2023-08-08 13:57:32.520  INFO 7868 --- [pool-5-thread-1] com.demo.web.ThreadLocalController3      : 子线程get值为: {1=1, 2=2}
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 13:28:21
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/ttl")
    @ApiOperation("原生错误示范")
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
        log.info("二次添加");
        threadLocal.get().put(++i,i);
        log.info("主线程get值为: " + threadLocal.get());
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


    private static final ThreadLocal<Map<Integer,Integer>> threadLocal2 = new TransmittableThreadLocal(){
        /**
         * 复制父线程的值到子线程  单独添加此方法不可行
         *
         *
         * @param parentValue 父值
         * @return {@code Object }
         * @author zhanghui
         * @date 2023/08/08 14:00:55
         * @see Object
         * @see Object
         * @since 1.0.0
         */
        @Override
        protected Map<Integer,Integer> childValue(Object parentValue) {
            if (parentValue instanceof Map){
                return new LinkedHashMap((Map) parentValue);
            }
            return null;
        }
    };


    /**
     * ttl2->错误示范2
     * 在复写childValue方法后，子线程传递父线程的值的对象不会是同一个,但是仍有错误
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 15:37:41
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/ttl2")
    @ApiOperation("原生错误示范2")
    public String ttl2(){
        log.info("主线程开始");
        Map<Integer, Integer> map = threadLocal2.get();
        if (map==null){
            init2();
        }
        threadLocal2.get().put(++i,i);
        log.info("主线程set值为: " + threadLocal2.get());
        executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            log.info("子线程get值为: " + threadLocal2.get());
        })));
        log.info("二次添加");
        threadLocal2.get().put(++i,i);
        log.info("主线程get值为: " + threadLocal2.get());
        threadLocal2.remove();
        log.info("主线程结束");
        return "success";
    }

    private synchronized static  void init2() {
        log.info("初始化");
        if (threadLocal2.get()==null){
            threadLocal2.set(new LinkedHashMap<>());
        }
    }



    private static final ThreadLocal<Map<Integer,Integer>> threadLocal3 = new TransmittableThreadLocal(){
        /**
         * 复制父线程的值到子线程  单独添加此方法不可行
         *
         *
         * @param parentValue 父值
         * @return {@code Object }
         * @author zhanghui
         * @date 2023/08/08 14:00:55
         * @see Object
         * @see Object
         * @since 1.0.0
         */
        @Override
        protected Map<Integer,Integer> childValue(Object parentValue) {
            if (parentValue instanceof Map){
                return new LinkedHashMap((Map) parentValue);
            }
            return null;
        }

        /**
         * 复制
         *
         * @param parentValue 父值
         * @return {@code Map<Integer,Integer> }
         * @author zhanghui
         * @date 2023/08/08 14:08:37
         * @see Object
         * @see Map<Integer,Integer>
         * @since 1.0.0
         */
        @Override
        public Map<Integer,Integer> copy(Object parentValue) {
            if (parentValue instanceof Map){
                return new LinkedHashMap((Map) parentValue);
            }
            return null;
        }
    };


    /**
     * ttl3->正确示范
     * 复写childValue后，仍需复写copy方法，将父线程的值复制到子线程,并且子线程的值是独立的,父线程再去执行操作并不影响子线程的对象
     *
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 主线程开始
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 初始化
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 主线程set值为: {3=3}
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 二次添加
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 主线程set值为: {3=3, 4=4}
     * 2023-08-08 14:08:49.722  INFO 14900 --- [nio-8080-exec-2] com.demo.web.ThreadLocalController3      : 主线程结束
     * 2023-08-08 14:08:52.722  INFO 14900 --- [pool-5-thread-1] com.demo.web.ThreadLocalController3      : 子线程get值为: {3=3}
     *
     * TransmittableThreadLocal.set()会调用addThisToHolder()会把当前的TransmittableThreadLocal放在WeakHashMap中当Key,value为null
     * 使用TtlRunnable.get()方法时，会调用Transmitter.capture()方法生成一个Snapshot对象带2个Map对象产生一个静态的备份，在子线程使用过程中会把主线程的备份给还原到子线程上->{
     * 方法captureTtlValues()返回第一个对象,循环使用TransmittableThreadLocal.holder找出所有的TransmittableThreadLocal本身跟他的值放在hashMap中，
     * 方法captureThreadLocalValues()是将之前备份好的值放入hashMap中，然后将当前线程的值备份，放入hashMap中，最后返回hashMap,TtlCopier的copy可复写}
     *
     *
     * @return {@code String }
     * @author zhanghui
     * @date 2023/08/08 14:09:26
     * @see String
     * @since 1.0.0
     */
    @GetMapping("/ttl3")
    @ApiOperation("原生正确示范")
    public String ttl3(){
        log.info("主线程开始");
        Map<Integer, Integer> map = threadLocal3.get();
        if (map==null){
            init3();
        }
        threadLocal3.get().put(++i,i);
        log.info("主线程set值为: " + threadLocal3.get());
        executorService.execute(Objects.requireNonNull(TtlRunnable.get(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            log.info("子线程get值为: " + threadLocal3.get());
        })));
        log.info("二次添加");
        threadLocal3.get().put(++i,i);
        log.info("主线程get值为: " + threadLocal3.get());
        threadLocal3.remove();
        log.info("主线程结束");
        return "success";
    }

    private synchronized static  void init3() {
        log.info("初始化");
        if (threadLocal3.get()==null){
            threadLocal3.set(new LinkedHashMap<>());
        }
    }



}
