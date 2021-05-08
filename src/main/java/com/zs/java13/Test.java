package com.zs.java13;

/**
 * @auther: madisonzhuang
 * @date: 2019/9/23 15:10
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        Long long1 = new Long(100L);
        Long long2 = 100L;
        Long long3 = 100L;
        Long long4 = 128L;
        Long long5 = 128L;

        System.out.println(long1 == long2);
        System.out.println(+long1 == long2);

        System.out.println(long2 == long3);
        System.out.println(+long2 == long3);

        System.out.println(long4 == long5);
        System.out.println(+long4 == long5);

    }
}
