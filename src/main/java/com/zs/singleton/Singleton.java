package com.zs.singleton;

/**
 * 饿汉
 * 类加载的时候就创建了实例
 * 优点：类加载的时候创建一次实例，避免了多线程同步问题
 * <p>
 * 缺点：即使单例没被用到也会创建，浪费内存
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:06
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
