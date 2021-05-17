package com.zs.java15.thread;

import java.util.concurrent.SynchronousQueue;

/**
 * 什么是SynchronousQueue？
 * SynchronousQueue作为阻塞队列的时候，对于每一个take的线程会阻塞直到有一个put的线程放入元素为止，反之亦然。
 * 在SynchronousQueue内部没有任何存放元素的能力，可以理解为容量为 0。所以类似peek操作或者迭代器操作也是无效的，元素只能通过put类操作或者take类操作才有效。
 * SynchronousQueue支持支持生产者和消费者等待的公平性策略。默认情况下：非公平。
 * 如果是公平锁的话可以保证当前第一个队首的线程是等待时间最长的线程，这时可以视SynchronousQueue为一个FIFO队列。
 * SynchronousQueue 提供两种实现方式，分别是栈和队列的方式实现。这两种实现方式中，栈是属于非公平的策略，队列是属于公平策略。
 *
 * @author madison
 * @description
 * @date 2021/5/17 12:26
 */
public class SynchronousQueueTest {
    public static void main(String[] args) throws Exception {
        //使用非公平策略
//        SynchronousQueue synchronousQueue= new SynchronousQueue();
        //使用公平策略
        SynchronousQueue synchronousQueue = new SynchronousQueue(true);
        new Thread(() -> {
            try {
                synchronousQueue.put("A");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //休眠一下，让异步线程完成
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                synchronousQueue.put("B");
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }).start();
        //休眠一下，让异步线程完成
        Thread.sleep(1000);
        new Thread(() -> {
            try {
                Object take = synchronousQueue.take();
                System.out.println(take);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }).start();
        //休眠一下，让异步线程完成
        Thread.sleep(1000);
        //不管如何输出，都是 0
        System.out.println(synchronousQueue.size());

        System.out.println(synchronousQueue.poll());
    }
}
