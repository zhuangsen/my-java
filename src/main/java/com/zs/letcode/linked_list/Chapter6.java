package com.zs.letcode.linked_list;

/**
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 * <p>
 * 输入：head = []
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *  
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/f58sg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/18 20:38
 */
public class Chapter6 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
        ListNode node2 = new ListNode(3);
        node1.next = node2;
        ListNode node3 = new ListNode(4);
        node2.next = node3;
        ListNode node4 = new ListNode(5);
        node3.next = node4;

        System.out.println(head);
    }

    private class Solution {
        /**
         * 方法一：迭代
         * 1-> 2 -> 3 ->4 ->5
         *
         * next = 2    prev = 1   curr=2    1-> null  	2->3->4->5
         * next = 3    prev = 2   curr=3    2->1        3->4->5
         * next = 4    prev = 3   curr=4    3->2        4->5
         * next =5     prev =4    curr=5    4->3        5
         * next =null  prev =5    curr=null 5->4        5
         */
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }

        /**
         * 方法二：递归
         */
        public ListNode reverseList1(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            // 想象递归已经层层返回，到了最后一步
            // 以链表 1->2->3->4->5 为例，现在链表变成了 5->4->3->2->null，1->2->null（是一个链表，不是两个链表）
            // 此时 newHead是5，head是1
            ListNode newHead = reverseList1(head.next);
            // 最后的操作是把链表 1->2->null 变成 2->1->null
            // head是1，head.next是2，head.next.next = head 就是2指向1，此时链表为 2->1->2
            head.next.next = head;
            // 防止链表循环，1指向null，此时链表为 2->1->null，整个链表为 5->4->3->2->1->null
            head.next = null;
            return newHead;
        }
    }
}
