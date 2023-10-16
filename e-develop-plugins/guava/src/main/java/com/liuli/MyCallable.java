package com.liuli;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
        private int a;
        private int b;

        public MyCallable(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public Integer call() throws Exception {
            return a * b;
        }
    }