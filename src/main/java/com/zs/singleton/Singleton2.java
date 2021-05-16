package com.zs.singleton;

/**
 * 懒汉-（非线程安全）
 * 优点：需要时才去创建
 * 缺点：没有考虑线程安全问题，多个线程并发调用getInstance，可能会创建多个实例
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:10
 */
public class Singleton2 {
    private static Singleton2 instance = null;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
