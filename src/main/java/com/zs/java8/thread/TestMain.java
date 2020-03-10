package com.zs.java8.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/9 14:47
 * @description:
 */
public class TestMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new MyThreadFactory(false));
        for (int i=0;i<1000;i++){
            executorService.execute(new TestRunnable(String.valueOf(i)));
        }
        executorService.shutdown();

    }

    static class TestRunnable implements Runnable{

        private String msg;

        public TestRunnable(String msg){
            this.msg = msg;
        }
        @Override
        public void run() {
            System.out.println("runnable:"+msg);
        }
    }
}
