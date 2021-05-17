package com.zs.letcode.linked_list;

/**
 * @author madison
 * @description
 * @date 2021/5/17 11:01
 */
public class ListNode {
    int val;
    ListNode next;

    public ListNode(int x) {
        this.val = x;
    }

    public ListNode(int x, ListNode next) {
        this.val = x;
        this.next = next;
    }
}
