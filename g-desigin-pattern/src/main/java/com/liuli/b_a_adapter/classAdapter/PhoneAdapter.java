package com.liuli.b_a_adapter.classAdapter;

/**
 * @author pcc
 * SupplierPhone生产的是标准手机，业务场景无法直接使用
 * 所以我们需要对其进行适配，在生产我们自己手机时对其进行二次加工
 * 所以这里需要继承SupplierPhone来获得他的手机生产方式
 * 然后对手机进行二次加工
 * <p>
 * 这里最好不要让SupplierPhone 和 NeedPhone 的生产方法同名，同名的话
 * 会把SupplierPhone的方法认为是NeedPhone的实现了
 */
public class PhoneAdapter extends SupplierPhone implements NeedPhone {

    @Override
    public void myCall() {
        System.out.println("更换手机原件");
        super.call();
        System.out.println("为手机增加商标");
    }


    public static void main(String[] args) {
        PhoneAdapter phoneAdapter = new PhoneAdapter();
        phoneAdapter.myCall();
    }

}
