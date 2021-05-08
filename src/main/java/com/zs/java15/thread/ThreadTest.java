package com.zs.java15.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author madison
 * @description
 * @date 2021/5/8 11:13
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        Random random = new Random();
//      ArrayList 线程不安全，不加.join数据丢失   使用CopyOnWriteArrayList防止数据丢失
        List<Integer> list = new ArrayList<>();

//        Thread thread = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("子线程");
//            list.add(random.nextInt());
//        });
//        thread.start();
//        thread.join();


        for (int i = 0; i < 100000; i++) {
            Thread thread = new Thread(() -> list.add(random.nextInt()));
//            thread表示的线程转换为RUNNABLE状态
            thread.start();

//            在调用 join() 方法的程序中，原来的多个线程仍然多个线程，并没有发生“合并为一个单线程”。
//            真正发生的是调用 join() 的线程进入 TIMED_WAITING 状态，等待 join() 所属线程运行结束后再继续运行。
//            等待thread线程运行完再继续运行, 不加上会丢失数据
            thread.join();
        }
        System.out.println("时间：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" + list.size());
    }
}
