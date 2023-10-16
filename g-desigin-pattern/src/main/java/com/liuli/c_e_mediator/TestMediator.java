package com.liuli.c_e_mediator;

/**
 * @author pcc
 */
public class TestMediator {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        SellPerson  sellPerson = new SellPerson("金城碧桂园1","12栋1单元201");
        SellPerson  sellPerson2 = new SellPerson("金城碧桂园2","12栋1单元202");
        SellPerson  sellPerson3 = new SellPerson("金城碧桂园3","12栋1单元203");
        sellPerson.sellRoom(mediator);
        sellPerson2.sellRoom(mediator);
        sellPerson3.sellRoom(mediator);


        // 下面假设是买方动作
        if(null!=mediator.get("金城碧桂园1")){
            System.out.println("周到目标："+mediator.get("金城碧桂园1"));
        }
    }
}