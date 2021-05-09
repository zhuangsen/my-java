package com.zs.java15.datastructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author madison
 * @description
 * @date 2021/5/9 11:25
 */
public class StringTest {
    public static void main(String[] args) {

        String str = "a,b,c,,";
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);


        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
//        使用集合转数组的方法，必须使用集合的 toArray(T[] array)，传入的是类型完全一
//        致、长度为 0 的空数组。
//        说明：使用 toArray 带参方法，数组空间大小的 length： 1） 等于 0，动态创建与 size 相同的数组，性能最好。
//        2） 大于 0 但小于 size，重新创建大小等于 size 的数组，增加 GC 负担。
//        3） 等于 size，在高并发情况下，数组创建完成之后，size 正在变大的情况下，负面影响与 2 相同。
//        4） 大于 size，空间浪费，且在 size 处插入 null 值，存在 NPE 隐患。

        String[] st = list.toArray(new String[0]);
        System.out.println(st.length);

        String[] sttr = new String[]{"chen", "yang", "hao"};
        List listStr = Arrays.asList(sttr);
        listStr.stream().forEach(System.out::println);
//        add/remove/clear 方法会抛出 UnsupportedOperationException 异常
//        listStr.add("34");
        listStr.set(0, "34");
        System.out.println(sttr);
        for (String s : sttr) {
            System.out.println(s);
        }


        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom.nextInt(5));
    }
}
