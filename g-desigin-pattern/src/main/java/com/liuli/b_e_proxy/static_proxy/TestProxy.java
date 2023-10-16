package com.liuli.b_e_proxy.static_proxy;

/**
 * @author pcc
 */
public class TestProxy {
    public static void main(String[] args) {
        SupplierDrug supplierDrug = new ProxyYunNanBaiYaoSupplierDrug(new YunNanBaiYaoSupplierDrug());
        supplierDrug.produceDrug();
        supplierDrug.checkDrug();
    }
}