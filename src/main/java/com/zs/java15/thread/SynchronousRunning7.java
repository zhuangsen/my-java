package com.zs.java15.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 7.使用CyclicBarrier(回环栅栏)实现线程按顺序运行
 * CyclicBarrier(回环栅栏):通过它可以实现让一组线程等待至某个状态之后再全部同时执行。
 * 叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。我们暂且把这个状态就叫做barrier，当调用await()方法之后，线程就处于barrier了。
 * <p>
 * 应用场景:公司组织春游,等待所有的员工到达集合地点才能出发，每个人到达后进入barrier状态。都到达后，唤起大家一起出发去旅行。
 * <p>
 * 运行结果
 * <p>
 * 早上：
 * 测试人员来上班了...
 * 产品经理来上班了...
 * 开发人员来上班了...
 * 产品经理规划新需求
 * 开发人员开发新需求功能
 * 测试人员测试新功能
 *
 * @author madison
 * @description
 * @date 2021/5/16 22:23
 */
public class SynchronousRunning7 {

    static CyclicBarrier barrier1 = new CyclicBarrier(2);
    static CyclicBarrier barrier2 = new CyclicBarrier(2);

    public static void main(String[] args) {

        final Thread thread1 = new Thread(() -> {
            try {
                System.out.println("产品经理规划新需求");
                //放开栅栏1
                barrier1.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        final Thread thread2 = new Thread(() -> {
            try {
                //放开栅栏1
                barrier1.await();
                System.out.println("开发人员开发新需求功能");
                //放开栅栏2
                barrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        final Thread thread3 = new Thread(() -> {
            try {
                //放开栅栏2
                barrier2.await();
                System.out.println("测试人员测试新功能");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");
        thread1.start();
        System.out.println("开发人员来上班了...");
        thread2.start();
    }
}
