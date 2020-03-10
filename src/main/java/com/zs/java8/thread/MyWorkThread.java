package com.zs.java8.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/9 14:46
 * @description:
 */
public class MyWorkThread extends Thread {

    private AtomicInteger atomicInteger;

    public MyWorkThread(AtomicInteger atomicInteger, Runnable runnable){
        super(runnable);
        this.atomicInteger = atomicInteger;
    }


    @Override
    public void run() {
        System.out.println(this.getName()+" test"+atomicInteger.getAndDecrement());
        super.run();
    }
}
