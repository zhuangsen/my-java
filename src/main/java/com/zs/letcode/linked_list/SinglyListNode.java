package com.zs.letcode.linked_list;

/**
 * @author madison
 * @description
 * @date 2021/5/17 09:52
 */
public class SinglyListNode {
    int val;
    SinglyListNode next;

    public SinglyListNode(int x) {
        this.val = x;
    }

    public SinglyListNode(int x, SinglyListNode next) {
        this.val = x;
        this.next = next;
    }
}
