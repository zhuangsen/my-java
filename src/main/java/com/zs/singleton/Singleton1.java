package com.zs.singleton;

/**
 * 饿汉-变种
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:09
 */
public class Singleton1 {
    private static Singleton1 instance = null;

    static {
        instance = new Singleton1();
    }

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
