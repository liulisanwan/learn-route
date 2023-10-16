package com.liuli.b_g_flyweight;// 这里就以单例来举例吧
/**
 * @author pcc
 * 享元模式也是一种比较简单的结构型模式，他的核心思想是共享可以共享的部分，
 * 尽量减少内存的压力。比如Java里的String对象就是一种享元模式的设计，
 * 如果我们新建两个相同的String对象，那么字符串在常量池中只会存在一份，
 * 他并没有进行重复存储相同的对象，而是去共用一份。
 * 包括基本数据类型也是如此，他们在栈中也都是共用的。
 * 享元模式就是这个思路：比如一个工程中需要很多地方使用一个对象，
 * 这个对象只需要一个，那我们只需要为这个工厂创建一个对象即可，
 * 然后大家一起用。这里其实就是单例了。但是他们是从不从角度对代码的优化，只是最后走到了一起。

 */
public class TestFlyweight {

    static class TestFlyweightInner{
        private static final TestFlyweight testFlyweight = new  TestFlyweight();
    }
    private TestFlyweight(){
    }

    public static TestFlyweight getInstance(){
        return TestFlyweightInner.testFlyweight;
    }
}
