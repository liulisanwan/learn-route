package com.liuli.a_d_single;

/**
 * 基础的懒汉模式
 *
 * @author zhanghui
 * @date 2023/09/21 17:13:22
 */
public class Single3 {

    private  static Single3 instance ;

    private Single3() {

    }

    public static Single3 getInstance() {
        if (instance == null) {

            // 放大线程安全问题
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


            instance = new Single3();
        }
        return instance;
    }
}
