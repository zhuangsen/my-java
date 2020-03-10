package com.zs.java8.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/9 15:26
 * @description:
 */
public class TestThreadFactory {

    class WorkThread extends Thread{

        private Runnable target;
        private AtomicInteger counter;


        public WorkThread(ThreadGroup group, String name, Runnable target, AtomicInteger counter) {
            super(group, name);
            this.target = target;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {

                target.run();
            }finally {
                int c = counter.getAndDecrement();
                System.out.println("terminate no " + c + " Threads");
            }

        }
    }


    static class WorkRunnable implements Runnable{

        /**

         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("Comoplete a task");

        }
    }


    /*
    线程工场
     */
    static class WorkThreadFactory implements ThreadFactory {

        private AtomicInteger atomicInteger =new AtomicInteger(0);

        /**
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         *
         * @param r a runnable to be executed by new thread instance
         * @return constructed thread, or {@code null} if the request to
         * create a thread is rejected
         */
        @Override
        public Thread newThread(Runnable r) {
            int c = atomicInteger.incrementAndGet();
            System.out.println("create no " + c + " Threads");
            return new Thread(r, String.valueOf(atomicInteger));
        }
    }



    public static void main(String[] args){

        ExecutorService es = Executors.newCachedThreadPool(new WorkThreadFactory());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        es.execute(new WorkRunnable());
        //指示当所有线程执行完毕后关闭线程池和工作线程，如果不调用此方法，jvm不会自动关闭
        es.shutdown();
        try {
//阻止所有任务在关闭请求完成后执行，或发生超时，或当前线程中断，以先到者为准。
            es.awaitTermination(2*60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
