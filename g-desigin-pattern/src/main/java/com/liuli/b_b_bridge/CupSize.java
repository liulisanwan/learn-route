package com.liuli.b_b_bridge;

/**
 * @author pcc
 * 因为杯子有多种尺寸，所以对杯子的共性进行抽象，定义一个抽象类
 * 不同的尺寸对应不同的子类，子类中定义自己的特有属性，这里假设只有尺寸不同
 */
public abstract class CupSize {
    public abstract void size();
}
