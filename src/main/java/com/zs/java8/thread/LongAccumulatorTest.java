package com.zs.java8.thread;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/10 09:46
 * @description:
 */
public class LongAccumulatorTest {

    public static void main(String[] args) throws InterruptedException {
        //这样就可以很安全的求和操作了
        LongAccumulator accumulator = new LongAccumulator(Long::max, 2);

        Thread[] ts = new Thread[1000];

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            ts[i] = new Thread(() -> {
                Random random = new Random();
                long value = finalI;

                accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
            });
            ts[i].start();
        }
        for (int i = 0; i < 5; i++) {
            ts[i].join();
        }
        System.out.println(accumulator.longValue());
    }


    public static final int THREAD_COUNT = 1000;
    static ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
    static CompletionService<Long> completionService = new ExecutorCompletionService<Long>(pool);
    static final AtomicLong atomicLong = new AtomicLong(0L);
    static final LongAdder longAdder = new LongAdder();

    public static void compareTest() throws Exception{
        long start = System.currentTimeMillis();
        for(int i = 0; i < THREAD_COUNT; i++) {
            completionService.submit(new Callable<Long>() {
                @Override
                public Long call() throws Exception {
                    for(int j = 0; j < 100000; j++) {
//						atomicLong.incrementAndGet();
                        longAdder.increment();
                    }
                    return 1L;
                }
            });
        }
        for(int i = 0; i < THREAD_COUNT; i++) {
            Future<Long> future = completionService.take();
            future.get();
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        pool.shutdown();
    }
}
