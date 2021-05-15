package com.zs.java15.base;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author madison
 * @description
 * @date 2021/5/14 22:50
 */
public class Demo1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if (item.equals("2")) {
                iterator.remove();
            }
        }


//        for (String item : list) {
//            if ("2".equals(item)) {
//                list.remove(item);
//            }
//        }
        System.out.println(list.size());
        list.stream().forEach(System.out::println);

//        Collections.binarySearch()

        int a = 10;
        long b = 10L;
        double c = 10.0;
        System.out.println(a == b);
        System.out.println(b == c);

        // 强引用  强引用是平常中使用最多的引用，强引用在程序内存不足（OOM）的时候也不会被回收，使用方式
        String str = new String("str");
        System.out.println(str);

        // 软引用 软引用在程序内存不足时，会被回收，使用方式：
        // 注意：wrf这个引用也是强引用，它是指向SoftReference这个对象的，
        // 这里的软引用指的是指向new String("str")的引用，也就是SoftReference类中T
        SoftReference<String> srf = new SoftReference<>(str);
        System.out.println(srf.get());

        // 弱引用 弱引用就是只要JVM垃圾回收器发现了它，就会将之回收，使用方式：
        WeakReference<String> wrf = new WeakReference<>(str);
        System.out.println(wrf.get());

        // 虚引用
        // 虚引用的回收机制跟弱引用差不多，但是它被回收之前，会被放入 ReferenceQueue 中。注意
        // 哦，其它引用是被JVM回收后才被传入 ReferenceQueue 中的。由于这个机制，所以虚引用大多
        // 被用于引用销毁前的处理工作。还有就是，虚引用创建的时候，必须带有 ReferenceQueue ，
        // 使用例子
        PhantomReference<String> prf = new PhantomReference<>(str, new ReferenceQueue<>());
        System.out.println(prf.get());
    }
}
