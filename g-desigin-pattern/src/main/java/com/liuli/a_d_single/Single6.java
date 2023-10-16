package com.liuli.a_d_single;

/**
 * 双重检查锁
 *
 * @author zhanghui
 * @date 2023/09/21 17:14:12
 */
public class Single6 {
    private static volatile Single6 single6;

    private Single6(){}

    public static Single6 getInstance(){
        // 这里加if可已在高并发时省去大部分排队加锁的操作，是有必要的
        if(single6==null){
            // 这里可能存在多个线程都进入的场景，所以下面的同步代码块可能存在执行多次的场景
            synchronized (Single6.class){
                // 因为这段代码可能存在多次执行，但是同一时间只有一个会执行，所以再进行一次非空判断就解决了问题
                if(single6==null){
                    single6=new Single6();
                    return single6;
                }
            }
        }
        return single6;
    }
}
