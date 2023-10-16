package com.liuli.c_d_iterator;

/**
 * @author pcc
 */
public class TestMyArrayList {
    public static void main(String[] args) {
        MyArrayList<String> myArrayList = new MyArrayList<>(new String[10]);
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add("c");
        myArrayList.add("d");
        myArrayList.add("e");
        myArrayList.add("f");
        myArrayList.add("g");
        myArrayList.add("h");
        myArrayList.add("i");
        myArrayList.add("j");

        for (String s : myArrayList) {
            System.out.println(s);
        }
    }
}