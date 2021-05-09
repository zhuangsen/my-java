package com.zs.java15.datastructure;

import java.util.ArrayList;

/**
 * @author madison
 * @description
 * @date 2021/5/8 15:53
 */
public class LinkTest {
    public static void main(String[] args) {
        // 链表：链表是一种物理存储单元非连续、非顺序的存储结构
        // 特点：插入、删除时间复杂度O(1) 查找遍历时间复杂度O(N) 总结：插入快 查找慢
        Node head = new Node("madison");
        head.next = new Node("这种");
        head.next.next = new Node("等待");
        System.out.println(head.data);
        System.out.println(head.next.data);
        System.out.println(head.next.next.data);

        /** Java 集合类 {@link java.util.LinkedList} **/

    }

    static class Node {
        public Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }
}
