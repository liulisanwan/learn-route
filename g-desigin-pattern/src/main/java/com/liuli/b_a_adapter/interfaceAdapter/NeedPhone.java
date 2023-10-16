package com.liuli.b_a_adapter.interfaceAdapter;

// 需要适配的对象从类变成了接口，当然这里是类也依然是可以的
@FunctionalInterface
interface SupplierPhoneInterface{
    void myCall();
}



/**
 * 这是假设场景里真正需要的手机生产者，需要使用他来进行生产
 * 这个还是自己的生产手机的地方，这个没动
 */
public interface NeedPhone {
    public void myCall();
}


