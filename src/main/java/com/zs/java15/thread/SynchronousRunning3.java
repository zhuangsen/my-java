package com.zs.java15.thread;

/**
 * 3.使用线程的wait方法
 * wait():是Object的方法，作用是让当前线程进入等待状态，同时，wait()也会让当前线程释放它所持有的锁。
 * “直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法”，当前线程被唤醒(进入“就绪状态”)
 * <p>
 * notify()和notifyAll():是Object的方法，作用则是唤醒当前对象上的等待线程；
 * notify()是唤醒单个线程，而notifyAll()是唤醒所有的线程。
 * <p>
 * wait(long timeout):让当前线程处于“等待(阻塞)状态”，
 * “直到其他线程调用此对象的notify()方法或 notifyAll() 方法，或者超过指定的时间量”，当前线程被唤醒(进入“就绪状态”)。
 * <p>
 * 应用场景：Java实现生产者消费者的方式。
 * <p>
 * <p>
 * <p>
 * 运行结果:这里输出会有很多种顺序，主要是因为线程进入的顺序，造成锁住线程的顺序不一致。
 * <p>
 * 早上：
 * 测试人员来上班了...
 * 产品经理来上班了...
 * 开发人员来上班了...
 * 测试人员先休息会...
 * 产品经理规划新需求...
 * 开发人员开发新需求功能
 * 测试人员测试新功能
 *
 * @author madison
 * @description
 * @date 2021/5/16 21:57
 */
public class SynchronousRunning3 {

    private static Object myLock1 = new Object();
    private static Object myLock2 = new Object();

    /**
     * 为什么要加这两个标识状态?
     * 如果没有状态标识，当t1已经运行完了t2才运行，t2在等待t1唤醒导致t2永远处于等待状态
     */
    private static Boolean t1Run = false;
    private static Boolean t2Run = false;

    public static void main(String[] args) {

        final Thread thread1 = new Thread(() -> {
            synchronized (myLock1) {
                System.out.println("产品经理规划新需求");
                t1Run = true;
                myLock1.notify();
            }
        });

        final Thread thread2 = new Thread(() -> {
            synchronized (myLock1) {
                try {
                    if (!t1Run) {
                        System.out.println("开发人员先休息会...");
                        myLock1.wait();
                    }
                    synchronized (myLock2) {
                        System.out.println("开发人员开发新需求功能");
                        myLock2.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (myLock2) {
                try {
                    if (!t2Run) {
                        System.out.println("测试人员先休息会...");
                        myLock2.wait();
                    }
                    System.out.println("测试人员测试新功能");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("早上：");
        System.out.println("测试人员来上班了...");
        thread3.start();
        System.out.println("产品经理来上班了...");

        System.out.println("开发人员来上班了...");
        thread2.start();
        thread1.start();
    }

}
