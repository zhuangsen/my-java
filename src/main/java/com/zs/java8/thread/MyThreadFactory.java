package com.zs.java8.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/9 14:45
 * @description:
 */
public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger atomicInteger = new AtomicInteger();

    private boolean isDaemon;

    public MyThreadFactory(boolean isDaemon) {
        this.isDaemon = isDaemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        atomicInteger.incrementAndGet();
        Thread thread =  new MyWorkThread(atomicInteger,r);
        thread.setDaemon(isDaemon);
        return thread;
    }
}
