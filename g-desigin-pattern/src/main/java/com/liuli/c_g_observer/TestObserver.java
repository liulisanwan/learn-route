package com.liuli.c_g_observer;

/**
 * @author pcc
 */
public class TestObserver {
    public static void main(String[] args) {
        CustomerObserver  customerObserver = new CustomerObserver();
        CustomerObserver2  customerObserver2 = new CustomerObserver2();

        //添加观察者
        CustomerObservable  customerObservable = new CustomerObservable();
        customerObservable.addObserver(customerObserver);
        customerObservable.addObserver(customerObserver2);


        // 假设发生了某业务场景，通知顾客
        customerObservable.publish("顾客状态变更了，请注意");
    }
}