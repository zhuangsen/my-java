package com.zs.java15.datastructure;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @author madison
 * @description
 * @date 2021/5/8 15:45
 */
public class HashMapTest {
    public static void main(String[] args) {
        // HashMap: 数组+链表+哈希算法
        // 链表解决冲突： next指针 下一个next

        // HashMap底层算法：哈希算法，就是把任意长度值(key)通过散列算法变换成固定长度的key（地址）通过
        // 这个地址进行访问的数据结构
        // 它通过把关键码映射到表中一个。位置来访问记录，以加快查找的速度

        // HashCode: 通过字符串算出它的ascii码，进行mod(取模)，算出哈希表的下标
        // 优点：降低内存、降低数组长度
        // 缺点：hash冲突、碰撞
        HashMap<String, String> map = new HashMap<>();
        Hashtable hashtable = new Hashtable();
        map.put("1", "2");
        System.out.println(map.get("1"));

//        使用 entrySet 遍历 Map 类集合 KV，而不是 keySet 方式进行遍历。
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> kvSet : entries) {
            System.out.printf("K:%s---v:%s\r\n", kvSet.getKey(), kvSet.getValue());
        }

        char[] c = "lies".toCharArray();
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i] + ":" + (int) c[i]);
        }

        HashMapTest mapTest = new HashMapTest();
        mapTest.put("1", "2");
        mapTest.put("电视上", "2");
        mapTest.put("士大夫撒地方", "2");

        com.zs.java15.datastructure.HashMap<String, String> hashMap = new com.zs.java15.datastructure.HashMap();
        hashMap.put("第三方", "sdfsd");
        System.out.println(hashMap.get("第三方"));
    }

    public void put(String key, String value) {
        System.out.printf("key:%s::::::::::::::::::::::::::::::hash值:%s::::::::::::::::::::::存储位置:%s\r\n", key, key.hashCode(), Math.abs(key.hashCode() % 15));
    }
}
