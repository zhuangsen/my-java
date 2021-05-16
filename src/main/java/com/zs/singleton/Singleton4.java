package com.zs.singleton;

/**
 * 双重校验锁
 * 大部分情况下，同步代码块都不会执行到，提高了程序的性能。
 * <p>
 * 有一种情况，两个线程ThreadA，ThreadB，如果threadA执行到了第一个if条件判断，instance = null；
 * ThreadB也执行到了if条件判断instance = null，所以A和B会依次执行同步代码块里的代码。为了避免创建两个实例，因此又在同步代码块里添加了if条件进行二重检验。
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:24
 */
public class Singleton4 {
    private static Singleton4 instance = null;

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
