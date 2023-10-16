package com.liuli.c_g_observer;

import java.util.Observable;
import java.util.Observer;

public class CustomerObserver2 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("我是客户观察者2，我收到通知了，通知的内容是：" + arg);
    }
}
