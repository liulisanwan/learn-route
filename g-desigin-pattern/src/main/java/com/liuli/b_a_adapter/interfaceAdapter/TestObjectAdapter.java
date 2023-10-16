package com.liuli.b_a_adapter.interfaceAdapter;

/**
 * @author pcc
 */
public class TestObjectAdapter {
    public static void main(String[] args) {
        // 对象适配器，传入接口实现类
        ObjectPhoneAdapter objectPhoneAdapter = new ObjectPhoneAdapter(()->{
            System.out.println("我生产标准手机");
        });

        objectPhoneAdapter.myCall();
    }
}
