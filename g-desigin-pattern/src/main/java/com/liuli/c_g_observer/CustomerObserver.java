package com.liuli.c_g_observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author pcc
 */
public class CustomerObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("?"+o.countObservers());
        System.out.println("我是客户观察者1，我收到通知了，通知的内容是：" + arg);
    }
}








