package com.iot.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * 执行器工具类
 *
 * @author zhanghui
 * @date 2023/09/14 14:56:02
 */
@Slf4j
public class ExecutorUtil {
    /**
     * 执行者映射
     */
    public static final ConcurrentHashMap<String,ThreadPoolExecutor> executorMap = new ConcurrentHashMap<>();

    /**
     * 创建线程池
     *
     * @param corePoolSize     核心池大小
     * @param threadNamePrefix 线程名字前缀
     * @return {@code ThreadPoolExecutor }
     * @author zhanghui
     * @date 2023/09/14 14:56:44
     * @see Integer
     * @see String
     * @see ThreadPoolExecutor
     * @since 1.0.0
     */
    public static ThreadPoolExecutor createThreadPool(Integer corePoolSize, String threadNamePrefix) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,Integer.MAX_VALUE,Integer.MAX_VALUE,TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(Integer.MAX_VALUE),
                new BasicThreadFactory.Builder().namingPattern(threadNamePrefix+"-%d").daemon(true).build()){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                if (null != t) {
                    log.info("afterExecute:{}",t.getMessage());
                }
            }
        };
        return executor;
    }


    /**
     * 释放执行者
     *
     * @param deviceCode 装置，装置状态码
     * @return {@code List<Runnable> }
     * @author zhanghui
     * @date 2023/09/14 14:56:53
     * @see String
     * @see List<Runnable>
     * @since 1.0.0
     */
    public static List<Runnable> releaseExecutor(String deviceCode){
        //获取某个设备型号的线程池的队列来结束当前队列中的任务，并且获取当前队列中的任务的信息
        BlockingQueue<Runnable> q = executorMap.get(deviceCode).getQueue();
        ArrayList<Runnable> taskList = new ArrayList<>();
        q.drainTo(taskList);
        if (!q.isEmpty()) {
            for (Runnable r : q.toArray(new Runnable[0])) {
                if (q.remove(r))
                    taskList.add(r);
            }
        }
        return taskList;
    }
}
