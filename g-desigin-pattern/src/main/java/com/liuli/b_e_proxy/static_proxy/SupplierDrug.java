package com.liuli.b_e_proxy.static_proxy;

/**
 * @author pcc
 * 静态代理和装饰模式代码上基本相同
 * 假设有一个药品接口：drug /drʌɡ/,他有两个抽象方法
 * 1. 生产药品
 * 2. 检验药品
 * 他有一个实现类云南白药 YunNanBaiYaoSupplierDrug
 * 现在我想要做云南白药药厂生产的药的代理
 *

 */
public interface SupplierDrug {
    public void produceDrug();
    public void checkDrug();
}


// 代理云南药厂
class ProxyYunNanBaiYaoSupplierDrug implements SupplierDrug {
    private SupplierDrug supplierDrug;

    public ProxyYunNanBaiYaoSupplierDrug(SupplierDrug supplierDrug) {
        this.supplierDrug = supplierDrug;
    }

    @Override
    public void produceDrug() {
        supplierDrug.produceDrug();
        System.out.println("代理生产对药品二次包装");
    }

    @Override
    public void checkDrug() {
        supplierDrug.checkDrug();
        System.out.println("代理二次检验药品");
    }
}