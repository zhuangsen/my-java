package com.zs.java8.thread;

import java.util.Random;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/10 09:46
 * @description:
 */
public class LongAccumulatorTest {

    public static void main(String[] args) throws InterruptedException {
        //这样就可以很安全的求和操作了
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);

        Thread[] ts = new Thread[1000];

        for (int i = 0; i < 1000; i++) {
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = random.nextLong();

                accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
            });
            ts[i].start();
        }
        for (int i = 0; i < 1000; i++) {
            ts[i].join();
        }
        System.out.println(accumulator.longValue()); //9207653574451187103
    }
}
