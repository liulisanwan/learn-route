package com.liuli.c_g_observer;

import java.util.Observable;

/**
 * @author pcc
 */
public class CustomerObservable extends Observable {
    public void publish(String str) {
        setChanged(); //  设置状态已改变
        notifyObservers(str); //  通知所有观察者
    }
}
