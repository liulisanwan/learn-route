package com.liuli.b_e_proxy.static_proxy;

// 云南药厂
public class YunNanBaiYaoSupplierDrug implements SupplierDrug {
    @Override
    public void produceDrug() {
        System.out.println("云南白药生产药品");
    }

    @Override
    public void checkDrug() {
        System.out.println("云南白药检验药品");
    }
}
