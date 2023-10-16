package com.liuli.c_thread_pool.thread_pool_executor;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池执行器使用
 *
 * @author zhanghui
 * @date 2023/10/16 11:33:08
 */
public class CustomThreadPoolExecutorUse {
     static Logger log = LoggerFactory.getLogger(CustomThreadPoolExecutorUse.class);

    public static ThreadPoolExecutor createThreadPool(Integer corePoolSize, String threadNamePrefix) {
        return createThreadPool(corePoolSize, Integer.MAX_VALUE, Integer.MAX_VALUE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE),threadNamePrefix);
    }
    public static ThreadPoolExecutor createThreadPool(Integer corePoolSize,Integer maximumPoolSize, String threadNamePrefix) {
        return createThreadPool(corePoolSize, maximumPoolSize, Integer.MAX_VALUE, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE),threadNamePrefix);
    }

    public static ThreadPoolExecutor createThreadPool(Integer corePoolSize,Integer maximumPoolSize,Integer keepAliveTime, String threadNamePrefix) {
        return createThreadPool(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE),threadNamePrefix);
    }

    public static ThreadPoolExecutor createThreadPool(int corePoolSize,
                                                      int maximumPoolSize,
                                                      long keepAliveTime,
                                                      TimeUnit unit,
                                                      BlockingQueue<Runnable> workQueue,
                                                      String threadNamePrefix) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                new BasicThreadFactory.Builder().namingPattern(threadNamePrefix + "-%d").daemon(true).build()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (null != t) {
                    log.info("afterExecute:{}", t.getMessage());
                }
            }
        };
        return executor;
    }
}
