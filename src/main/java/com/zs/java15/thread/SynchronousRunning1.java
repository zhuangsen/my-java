package com.zs.java15.thread;

/**
 * 1.使用线程的join方法
 * join():是Theard的方法，作用是调用线程需等待该join()线程执行完成后，才能继续用下运行。
 * <p>
 * 应用场景：当一个线程必须等待另一个线程执行完毕才能执行时可以使用join方法。
 * <p>
 * <p>
 * 运行结果：
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
 * @date 2021/5/16 21:49
 */
public class SynchronousRunning1 {

    public static void main(String[] args) {
        final Thread thread1 = new Thread(() -> System.out.println("产品经理规划新需求"));

        final Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
                System.out.println("开发人员开发新需求功能");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread3 = new Thread(() -> {
            try {
                thread2.join();
                System.out.println("测试人员测试新功能");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
//        thread3.start(); // 为什么加到这里没用
        System.out.println("产品经理来上班了...");
        System.out.println("开发人员来上班了...");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
