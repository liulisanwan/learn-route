package com.liuli.c_h_state;

/**
 * @author pcc
 * 状态抽象类，定义为借口其实更好
 * 以及他的三个状态实现类
 *
 * 优点：解耦状态还业务实现
 * 缺点：若是状态过多，容易造成状态类过多（感觉不是缺点，若是每个状态都做自己的动作与业务代码耦合在一起，会导致后期维护越来越乱）
 */
public abstract class State {

    public abstract void handlerEvent();
}

class StartState extends State{
    public static final Integer STATE = 0;

    @Override
    public void handlerEvent() {
        System.out.println("start-process");
    }
}

class DealState extends State{
    public static final Integer STATE = 1;

    @Override
    public void handlerEvent() {
        System.out.println("deal-process");
    }
}

class CompleteState extends State{
    public static final Integer STATE = 2;
    @Override
    public void handlerEvent() {
        System.out.println("complete-process");
    }
}




