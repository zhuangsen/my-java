package com.zs.java15.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 4.使用线程的线程池方法
 * JAVA通过Executors提供了四种线程池
 * <p>
 * 单线程化线程池(newSingleThreadExecutor);
 * 可控最大并发数线程池(newFixedThreadPool);
 * 可回收缓存线程池(newCachedThreadPool);
 * 支持定时与周期性任务的线程池(newScheduledThreadPool)。
 * 单线程化线程池(newSingleThreadExecutor):优点，串行执行所有任务。
 * <p>
 * submit()：提交任务。
 * <p>
 * shutdown()：方法用来关闭线程池，拒绝新任务。
 * <p>
 * 应用场景:串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
 * <p>
 * 运行结果
 * <p>
 * 早上：
 * 产品经理来上班了
 * 测试人员来上班了
 * 开发人员来上班了
 * 领导吩咐:
 * 首先，产品经理规划新需求...
 * 然后，开发人员开发新需求功能...
 * 最后，测试人员测试新功能...
 * 产品经理规划新需求
 * 开发人员开发新需求功能
 * 测试人员测试新功能
 *
 * @author madison
 * @description
 * @date 2021/5/16 22:00
 */
public class SynchronousRunning4 {


    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {

        final Thread thread1 = new Thread(() -> System.out.println("产品经理规划新需求"));

        final Thread thread2 = new Thread(() -> System.out.println("开发人员开发新需求功能"));

        Thread thread3 = new Thread(() -> System.out.println("测试人员测试新功能"));

        System.out.println("早上：");
        System.out.println("产品经理来上班了");
        System.out.println("测试人员来上班了");
        System.out.println("开发人员来上班了");
        System.out.println("领导吩咐:");
        System.out.println("首先，产品经理规划新需求...");
        executorService.submit(thread1);
        System.out.println("然后，开发人员开发新需求功能...");
        executorService.submit(thread2);
        System.out.println("最后，测试人员测试新功能...");
        executorService.submit(thread3);
        executorService.shutdown();
    }

}
