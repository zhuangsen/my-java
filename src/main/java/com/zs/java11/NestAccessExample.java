package com.zs.java11;

import java.lang.reflect.Field;

/**
 * @auther: madison
 * @date: 2019-07-20 22:55
 * @description:
 */
public class NestAccessExample {
    public static class X {
        void test() throws Exception{
            Y y = new Y();
            y.y = 1;

            Field field = Y.class.getDeclaredField("y");
//            jdk11之前加上不会报错
            field.setAccessible(true);
            field.setInt(y, 2);
        }
    }

    private static class Y {
        private int y;
    }

    public static void main(String[] args) throws Exception {
        new X().test();
    }
}
