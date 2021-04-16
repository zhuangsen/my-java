package com.zs.letcode.easy.linked;

/**
 * 删除链表中的节点
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 * <p>
 *  
 * <p>
 * 现有一个链表 --head =[4,5,1,9]，它可以表示为:
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：给定你链表中值为5的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2：
 * <p>
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：给定你链表中值为1的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 *  
 * <p>
 * 提示：
 * <p>
 * 链表至少包含两个节点。
 * 链表中所有节点的值都是唯一的。
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
 * 不要从你的函数中返回任何结果。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnarn7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2020/9/27 1:42 下午
 */
public class Chapter1 {

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(1);
        ListNode tail = new ListNode(9);
        head.next = node2;
        node2.next = node3;
        node3.next = tail;

        System.out.println(head);
//        deleteNode1(node2);
//        System.out.println(head);
        deleteNode2(head, node2);
        System.out.println(head);
    }

    //不能删除末尾节点
    public static void deleteNode1(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    //可以删除头节点
    public static void deleteNode2(ListNode head, ListNode node) {
        if (node.next == null) {
            ListNode preNode = head;
            while (preNode.next != node) {
                preNode = preNode.next;
            }
            preNode.next = null;
        } else {
            node.val = node.next.val;
            node.next = node.next.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            if (this.next == null) {
                return String.valueOf(this.val);
            }
            return this.val + "->" + this.next.toString();
        }
    }
}