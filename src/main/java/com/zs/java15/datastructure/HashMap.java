package com.zs.java15.datastructure;

/**
 * @author madison
 * @description
 * @date 2021/5/8 19:18
 */
public class HashMap<K, V> implements Map<K, V> {

    private Entry<K, V>[] table;
    int size = 0;

    public HashMap() {
        table = new Entry[16];
    }

    /**
     * 通过key 算出hash
     * 进行取模 拿到index
     * 去数组找到index下标的对象
     * 然后判断当前对象为空 如果为空 没有冲突 null
     * 如果不为null 链表来存储
     *
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (null == entry) {
            //没有冲突
            table[index] = new Entry<>(k, v, index, null);
            size++;
        } else {
            //出现冲突 链表
            table[index] = new Entry<>(k, v, index, entry);
        }
        return table[index].getValue();
    }

    private int hash(K k) {
        int i = k.hashCode() % 16;
        return Math.abs(i);
    }

    /**
     * key hash index
     * table [index]是否为空
     * 如果为空 没有直接返回null
     * 不为空 进行比较
     * key 取对象 相等找到了 直接返回对象
     * 如果不想等 next是否为空 不为空 比较
     * 直到相等为止 或者不想等为止 返回
     *
     * @param k
     * @return
     */
    @Override
    public V get(K k) {
        if (size == 0) {
            return null;
        }
        int index = hash(k);
        Entry<K, V> entry = findValue(k, table[index]);
        return entry == null ? null : entry.getValue();
    }

    private Entry<K, V> findValue(K k, Entry<K, V> kvEntry) {
        if (k.equals(kvEntry.getKey()) || k == kvEntry.getKey()) {
            return kvEntry;
        } else {
            if (kvEntry.next != null) {
                return findValue(k, kvEntry.next);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {
        K k;
        V v;
        int hash;
        Entry<K, V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
