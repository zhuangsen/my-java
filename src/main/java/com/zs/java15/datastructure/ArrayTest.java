package com.zs.java15.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author madison
 * @description
 * @date 2021/5/8 15:46
 */
public class ArrayTest {

    public static void main(String[] args) {
        // 数组：采用一段连续的存储单元来存储数据
        // 特点：查询0(1) 删除插入0(N) 总结：查询快，删除插入慢
        Integer[] array = new Integer[4];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        System.out.println(array[1]);

        /** Java 集合类 {@link ArrayList} **/
    }
}
