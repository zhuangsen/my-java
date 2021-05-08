package com.zs.java15.thread;

import java.util.concurrent.*;

/**
 * @author madison
 * @description
 * @date 2021/5/8 12:00
 */
public class ThreadDemoTest {
    public static void main(String[] args) throws InterruptedException {
        // new SynchronousQueue<Runnable>() 队列消费完才产生另一个
        // 结果就是线程N 做第N个项目
        ExecutorService executorService1 = Executors.newCachedThreadPool(); //快

        // LinkedBlockingQueue MAX容量队列
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);// 慢

        // 咋一瞅，不就是newFixedThreadPool(1)吗？定眼一看，这里多了一层FinalizableDelegatedExecutorService包装
        // FixedThreadPool可以向下转型为ThreadPoolExecutor，并对其线程池进行配置，
        // 而SingleThreadExecutor被包装后，无法成功向下转型。因此，SingleThreadExecutor被定以后，无法修改，做到了真正的Single。
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();//最慢

        // 1-10 -> 11-20 -> 21-30 执行结果是 1-10 -> 21-30 -> 11-20
        // 原因是提交优先级和执行优先级不一样：
        // 提交优先级： 核心线程-> 线程等待队列  -> 非核心线程
        // 执行优先级： 核心线程-> 非核心线程    -> 线程等待队列
        ExecutorService executorService4 = new ThreadPoolExecutor(10, 20,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10));

        for (int i = 1; i <= 100; i++) {
            executorService4.execute(new MyTask(i));
        }
//        executorService3.shutdown();
//        executorService3.awaitTermination(1, TimeUnit.MINUTES);
    }
}

class MyTask implements Runnable {

    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "做第" + i + "个项目");
        try {
            // newCachedThreadPool 不sleep 线程会复用
            TimeUnit.SECONDS.sleep(1); //业务逻辑
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}