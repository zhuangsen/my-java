package com.zs.singleton;

/**
 * 懒汉-（线程安全）
 * 缺点：性能问题，添加了synchronized的函数比一般方法慢得多，若多次调用getInstance，则累积的性能损耗特别大。
 * 考虑到以上的性能问题，所以又有一种双重校验锁的实现方式: {@link Singleton4}
 *
 * @author madison
 * @description
 * @date 2021/5/16 11:16
 */
public class Singleton3 {
    private static Singleton3 instance = null;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
