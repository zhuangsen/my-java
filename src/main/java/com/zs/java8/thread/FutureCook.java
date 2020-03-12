package com.zs.java8.thread;

import java.util.concurrent.*;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/10 10:49
 * @description:
 */
public class FutureCook {

    public static void main(String[] args)  throws InterruptedException, ExecutionException{
//        cooking();
//        testRunnable();
//        testCallable();
//        testFutureTask();
        testFutureTask2();
    }

    static void cooking() throws InterruptedException, ExecutionException{
        long startTime = System.currentTimeMillis();
        // 第一步 网购厨具
        Callable<Chuju> onlineShopping = new Callable<Chuju>() {

            @Override
            public Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");
                Thread.sleep(5000);  // 模拟送货时间
                System.out.println("第一步：快递送到");
                return new Chuju();
            }

        };
        FutureTask<Chuju> task = new FutureTask<>(onlineShopping);
        new Thread(task).start();
        // 第二步 去超市购买食材
        Thread.sleep(2000);  // 模拟购买食材时间
        Shicai shicai = new Shicai();
        System.out.println("第二步：食材到位");
        // 第三步 用厨具烹饪食材
        if (!task.isDone()) {  // 联系快递员，询问是否到货
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
//            boolean cancel = task.cancel(false);
//            System.out.println("取消状态:"+cancel);
        }
        if(!task.isCancelled()){
            Chuju chuju = task.get();
            System.out.println("第三步：厨具到位，开始展现厨艺");
            cook(chuju, shicai);
        }else{
            System.out.println("取消订单，重新下单");
        }


        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }

    //  用厨具烹饪食材
    static void cook(Chuju chuju, Shicai shicai) {
    }

    // 厨具类
    static class Chuju {
    }

    // 食材类
    static class Shicai {
    }

    /**
     * runnable, 无返回值
     */
    public static void testRunnable(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<String> future = (Future<String>) executorService.submit(new Task());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * Callable, 有返回值
     */
    public static void testCallable(){
        ExecutorService executorService = Executors.newCachedThreadPool();

        Future<Integer> future = (Future<Integer>) executorService.submit(new Task2());
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * FutureTask则是一个RunnableFuture<V>，即实现了Runnbale又实现了Futrue<V>这两个接口，
     * 另外它还可以包装Runnable(实际上会转换为Callable)和Callable
     * <V>，所以一般来讲是一个符合体了，它可以通过Thread包装来直接执行，也可以提交给ExecuteService来执行
     * ，并且还可以通过v get()返回执行结果，在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果。
     */
    public static void testFutureTask(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task2());

        executorService.submit(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executorService.shutdown();
    }

    /**
     * FutureTask则是一个RunnableFuture<V>，即实现了Runnbale又实现了Futrue<V>这两个接口，
     * 另外它还可以包装Runnable(实际上会转换为Callable)和Callable
     * <V>，所以一般来讲是一个符合体了，它可以通过Thread包装来直接执行，也可以提交给ExecuteService来执行
     * ，并且还可以通过v get()返回执行结果，在线程体没有执行完成的时候，主线程一直阻塞等待，执行完则直接返回结果。
     */
    public static void testFutureTask2(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("testFutureTask2 run");
            }
        },fibc(30));

        executorService.submit(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        executorService.shutdown();
    }


    public static class Task implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("run");
        }

    }
    public static class Task2 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            System.out.println("call");
            return fibc(30);
        }

    }


    /**
     * 效率低下的斐波那契数列, 耗时的操作
     *
     * @param num
     * @return
     */
    static int fibc(int num) {
        if (num == 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return fibc(num - 1) + fibc(num - 2);
    }
}
