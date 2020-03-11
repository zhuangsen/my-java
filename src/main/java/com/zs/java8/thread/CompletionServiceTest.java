package com.zs.java8.thread;

import java.util.*;
import java.util.concurrent.*;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/10 11:23
 * @description:
 */
public class CompletionServiceTest {

    public static void main(String[] args){
//        test1();
        test2();
        System.out.println("-------------");
//        test3();
//        test4();
    }

    public static void test1(){
        Long start = System.currentTimeMillis();
        //开启3个线程
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            int taskCount = 10;
            // 结果集
            List<Integer> list = new ArrayList<>();
            List<Future<Integer>> futureList = new ArrayList<>();

            // 1.定义CompletionService
            CompletionService<Integer> completionService = new ExecutorCompletionService<>(exs);

            // 2.添加任务
            for (int i = 0; i < taskCount; i++) {
                Future<Integer> future = completionService.submit(new Task(i + 1));
                futureList.add(future);
            }

            // 3.获取结果
            for (int i = 0; i < taskCount; i++) {
                Integer result = completionService.take().get();
                System.out.println("任务i==" + result + "完成!" + new Date());
                list.add(result);
            }

            System.out.println("list=" + list);

            List<Integer> l = new ArrayList<>();
            for (Future<Integer> future : futureList){
                Integer integer = future.get();
                l.add(integer);
            }
            System.out.println("l="+l);




        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            exs.shutdown();
        }
    }

    public static void test2(){
        Random random = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletionService<String> service = new ExecutorCompletionService<>(pool);
        try {


            for(int i = 0; i<4; i++) {

                int finalI = i;
                service.submit(() -> {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(Thread.currentThread().getName()+"|完成任务");
                    return "data"+ finalI;
                });
            }

            for(int j = 0; j < 4; j++) {
                Future<String> take = service.take(); //这一行没有完成的任务就阻塞
//                Future<String> take = service.poll(); // 不等待，有结果就返回一个 Future 对象，否则返回 null。
                if(Objects.nonNull(take)){
                    String result = take.get(); // 这一行在这里不会阻塞，引入放入队列中的都是已经完成的任务
                    System.out.println("获取到结果："+result);
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    public static void test3() {
        Random random = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Future<String>> resultFuture = new ArrayList<>();

        try {
            for(int i = 0; i<4; i++) {
                final int tmp = i;
                Future<String> future = pool.submit(() -> {
                    Thread.sleep(1000+10*tmp);
                    System.out.println(Thread.currentThread().getName()+"|完成任务");
                    return "data"+random.nextInt(10);
                });
                resultFuture.add(future);
            }

            for(Future<String> future:resultFuture) {
                String result = future.get();
                System.out.println("执行结果"+result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }

    public static void test4(){
        Executor executor = Executors.newFixedThreadPool(3);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(executor);
        //List<Future<Integer>> result = new ArrayList<Future<Integer>>(10);
        try {
            for(int i=0; i< 10; i++){
                cs.submit(new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        Random r = new Random();
                        int init = 0;
                        for(int i = 0; i<100; i++){
                            init += r.nextInt();
                            Thread.sleep(100);
                        }
                        return Integer.valueOf(init);
                    }
                });
            }
            for(int i=0; i<10; i++){
                Future<Integer> future = cs.take();
                if(future != null){
                    System.out.println(future.get());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
        }
    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            if (i == 5) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            System.out.println("线程：" + Thread.currentThread().getName() + "任务i=" + i + ",执行完成！");
            return i;
        }

    }
}
