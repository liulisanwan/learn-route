package com.liuli.c_d_iterator;

import java.util.Iterator;
/**
 * @author pcc
 * 这里继承的是Iterable接口，而不是Iterator接口,注意
 */
public class MyArrayList<T> implements Iterable<T>{

    private static Integer index = 0;
    T[] t ;

    // 内部容器数组需要初始化
    public MyArrayList(T[] t){
        this.t = t;
    }

    public MyArrayList add(T t){
        this.t[index++]=t;
        return this;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }


    // 需要一个Iterator的实现
    private class MyIterator implements Iterator<T>{

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor<t.length;
        }

        @Override
        public T next() {
            return t[cursor++];
        }
    }
}