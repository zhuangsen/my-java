package com.zs.genericsTutorial;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @auther: madisonzhuang
 * @date: 2020/3/16 11:23
 * @description: 编写一段泛型程序来实现LRU缓存?对于喜欢Java编程的人来说这相当于是一次练习。
 * 给你个提示，LinkedHashMap可以用来实现固定大小的LRU缓存，
 * 当LRU缓存已经满了的时候，它会把最老的键值对移出缓存。
 * LinkedHashMap提供了一个称为removeEldestEntry()的方法，
 * 该方法会被put()和putAll()调用来删除最老的键值对。
 */
public class LRUCache<K, V> extends LinkedHashMap {

    private int cacheSize;

    //
    public LRUCache(int cacheSize) {
        super((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75F, true);
        this.cacheSize = cacheSize;
    }


    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() >= cacheSize;
    }


    public static void main(String[] args) {
        //第二种方法
        int cacheSize = 100;

        LRUCache<String, String> lruCache1 = new LRUCache<>(cacheSize);


        Map<String, String> lruCache2 = new LinkedHashMap<String, String>((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };
    }
}
