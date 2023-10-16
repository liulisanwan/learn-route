package com.liuli.b_f_facade;

/**
 * @author pcc
 */
public class TestFacade {

    public void funOne(){
        System.out.println("填写请假信息");
    }

    public void funTwo(){
        System.out.println("找留到审批请假");
    }

    public void funThree(){
        System.out.println("将请假信息交给考勤人员");
    }

    public void funFour(){
        funOne();
        funTwo();
        funThree();
    }

    public static void main(String[] args) {
        new TestFacade().funFour();
    }
}
