package com.liuli.b_a_adapter.interfaceAdapter;

/**
 * 适配器
 *
 * @author pcc
 * 对象适配和类适配的区别是，对象适配是引入一个待适配的对象
 * 而类适配则是通过继承来实现，类适配无法处理接口需要适配的场景，且占用继承坑位不利于扩展
 * 这里使用的NeedPhone还是上面代码的NeedPhone
 * 新增SupplierPhoneInterface 这是待适配的接口
 */

public class ObjectPhoneAdapter implements NeedPhone {
    SupplierPhoneInterface supplierPhoneInterface;

    public ObjectPhoneAdapter(SupplierPhoneInterface supplierPhoneInterface) {
        this.supplierPhoneInterface = supplierPhoneInterface;
    }

    @Override
    public void myCall() {
        System.out.println("更换原手机组件");
        supplierPhoneInterface.myCall();
        System.out.println("为手机贴标");
    }
}
