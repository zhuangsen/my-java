package com.zs.java15.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author madison
 * @description
 * @date 2021/5/8 11:12
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();

//        阿里开发规范
//        【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这
//        样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
//        说明：Executors 返回的线程池对象的弊端如下： 1） FixedThreadPool 和 SingleThreadPool：
//        允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。 2） CachedThreadPool：
//        允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。

//        咋一瞅，不就是newFixedThreadPool(1)吗？定眼一看，这里多了一层FinalizableDelegatedExecutorService包装
//        FixedThreadPool可以向下转型为ThreadPoolExecutor，并对其线程池进行配置，
//        而SingleThreadExecutor被包装后，无法成功向下转型。因此，SingleThreadExecutor被定以后，无法修改，做到了真正的Single。

//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        运行时异常 java.lang.ClassCastException
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)executorService;

//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;


//        序号	    名称	            类型	                        含义
//        1	        corePoolSize	int	                        核心线程池大小
//        2	        maximumPoolSize	int	                        最大线程池大小
//        3	        keepAliveTime	long	                    线程程最大空闲时间(非核心线程=maximumPoolSize-corePoolSize) 对非核心线程数 多长时间销毁
//        4	        unit	        TimeUnit	                时间单位
//        5	        workQueue	    BlockingQueue<Runnable>	    线程等待队列
//        6	        threadFactory	ThreadFactory	            线程创建工厂  (从工厂里面创建线程)
//        7	        handler	        RejectedExecutionHandler	拒绝策略     (队列存不下的多余线程 处理，拒绝、抛异常)

//        ArrayBlockingQueue是一个用数组实现的有界阻塞队列，此队列按照先进先出（FIFO）的原则对元素进行排序。
//        LinkedBlockingDeque是一个由链表结构组成的双向阻塞队列，即可以从队列的两端插入和移除元素。双向队列因为多了一个操作队列的入口，在多线程同时入队时，也就减少了一半的竞争。
        ExecutorService executorService = new ThreadPoolExecutor(1, 1,
                1, TimeUnit.HOURS, new LinkedBlockingDeque<>(40000));

        for (int i = 0; i < 100000; i++) {
            //虽然实现了Runnable接口，但并没有创建100000个线程，也就没有上下文切换，只有2个线程
            try {
                executorService.execute(() -> list.add(random.nextInt()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
            }
        }
//        滑的关闭ExecutorService，当此方法被调用时，ExecutorService停止接收新的任务并且等待已经提交的任务（包含提交正在执行和提交未执行）执行完成。当所有提交任务执行完毕，线程池即被关闭。
        executorService.shutdown();
//        当等待超过设定时间时，会监测ExecutorService是否已经关闭，若关闭则返回true，否则返回false。一般情况下会和shutdown方法组合使用。
        executorService.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" + list.size());
    }
}
