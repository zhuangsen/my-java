package com.zs.java8.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/10 13:43
 * @description:
 */
public class CompleteFutureTest {
    public static void main(String[] args) {
        //1、变换结果
        // 说明：Async结尾的方法都是可以异步执行的，如果指定了线程池，会在指定的线程池中执行，如果没有指定，
        // 默认会在ForkJoinPool.commonPool()中执行。下面很多方法都是类似的，不再做特别说明。
        //
        //四个静态方法用来为一段异步执行的代码创建CompletableFuture对象，方法的参数类型都是函数式接口，所以可以使用lambda表达式实现异步任务
        //
        //runAsync方法：它以Runnabel函数式接口类型为参数，所以CompletableFuture的计算结果为空。
        //
        //supplyAsync方法以Supplier<U>函数式接口类型为参数，CompletableFuture的计算结果类型为U。
        String result = CompletableFuture.supplyAsync(()->{return "Hello ";}).thenApplyAsync(v -> v + "world").join();
        System.out.println(result);

        //2、消费结果
        CompletableFuture.supplyAsync(()->{return "Hello ";}).thenAccept(v -> { System.out.println("consumer: " + v);});

        //3、结合两个CompletionStage的结果，进行转化后返回
        //需要上一阶段的返回值，并且other代表的CompletionStage也要返回值之后，把这两个返回值，进行转换后返回指定类型的值。
        //
        //说明：同样，也存在对两个CompletionStage结果进行消耗的一组方法，例如thenAcceptBoth，这里不再进行示例。
        result = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }),(s1,s2)->{return s1 + " " + s2;}).join();
        System.out.println(result);

        //4、两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的处理
        //两种渠道完成同一个事情，就可以调用这个方法，找一个最快的结果进行处理，最终有返回值。
        result = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }),(s)->{return s;}).join();
        System.out.println(result);


        //5、运行时出现了异常，可以通过exceptionally进行补偿
        result = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(true) {
                throw new RuntimeException("exception test!");
            }

            return "Hi Boy";
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return "Hello world!";
        }).join();
        System.out.println(result);
    }
}
