package com.liuli.c_a_template;

/**
 * @author pcc
 * 这是完成流程的接口，完成流程时我们将数据的状态进行更新记录日志等这是一个固定操作
 * 此外可能还有需要通知三方系统等，但不确定有哪些三方系统
 * 这里使用的是函数式接口来实现场景，无论是函数式接口还是普通类、抽象类都是可以的
 */
@FunctionalInterface
public interface CompleteProcess {

    /**
     * 这是一个抽象方法：供子类实现
     */
    void notifyThirdSystem();


    /**
     * 完成的流程是确定的，我们唯一不确定的是通知的系统
     */
    default void complete(){
        System.out.println("更新数据状态");
        notifyThirdSystem();
        System.out.println("记录日志");
    }

}




/**
* 这是子类
*/
class DealProcess implements CompleteProcess{

    /**
     *
     * 子类只负责这个方法，其他方法由父类实现
     * 此外若是这个实现变动，也无需改动父类
     */
    @Override
    public void notifyThirdSystem() {
        System.out.println("通知三方系统:A");
        System.out.println("通知三方系统:B");
    }
}