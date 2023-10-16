package com.liuli.b_b_bridge;

/**
 * @author pcc
 */
public class TestBridge {
    public static void main(String[] args) {
        SupplierCoffee supplierCoffee = new SupplierCoffee(new BigCupSize());
        supplierCoffee.doSupplierCoffee();
    }
}
