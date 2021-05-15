package com.zs.java15.base;

/**
 * @author madison
 * @description
 * @date 2021/5/15 00:28
 */
public class Demo3 {
    public static void main(String[] args) {
        System.out.println(3 * 0.1);

        byte a = 127;
        byte b = 127;
        // 报编译错误:cannot convert from int to byte
//        b = a + b;
        b += a;
        System.out.println(b);

        short s1 = 1;
//        s1 = s1 + 1;
        s1 += 1;
        System.out.println(s1);
    }
}
