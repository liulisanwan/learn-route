package com.liuli.b_b_bridge;

/**
 * @author pcc
 * 这是桥接模式类
 * 通过使用抽象的尺寸大小的对象，而不是继承抽象类，从而实现分类抽象和实现的目的。
 *
 */
public class SupplierCoffee {
    CupSize cupSize;

    public SupplierCoffee(CupSize cupSize){
        this.cupSize = cupSize;
    }

    public void doSupplierCoffee(){
        System.out.println("使用原料制作咖啡");
        System.out.print("使用杯子:");
        cupSize.size();
        System.out.println("咖啡制作完成");

    }
}


class BigCupSize extends CupSize{
    @Override
    public void size() {
        System.out.println("大杯");
    }
}

class MiddleCupSize extends CupSize{

    @Override
    public void size() {
        System.out.println("中杯");
    }
}


class LittleCupSize extends CupSize{

    @Override
    public void size() {
        System.out.println("小杯");
    }
}

