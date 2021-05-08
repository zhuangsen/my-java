package com.zs.java15.datastructure;

/**
 * @author madison
 * @description
 * @date 2021/5/8 19:17
 */
public interface Map<K, V> {
    V put(K k, V v);

    V get(K k);

    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
