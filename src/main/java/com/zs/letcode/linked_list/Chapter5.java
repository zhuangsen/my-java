package com.zs.letcode.linked_list;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 删除链表的倒数第N个节点
 * 给你一个链表，删除链表的倒数第n个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 * 相关标签
 * 链表
 * 双指针
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/jf1cc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/18 10:34
 */
public class Chapter5 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        head.next = node1;
//        ListNode node2 = new ListNode(3);
//        node1.next = node2;
//        ListNode node3 = new ListNode(4);
//        node2.next = node3;
//        ListNode node4 = new ListNode(5);
//        node3.next = node4;

        System.out.println(head);

        Solution solution = new Solution();
        System.out.println(solution.removeNthFromEnd1(head, 2));
    }

    private static class Solution {
        /**
         * 方法一：暴力解法
         */
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode node = head;
            int size = 0;
            while (node != null) {
                size++;
                node = node.next;
            }
            int index = size - n + 1;
            if (index == 1) {
                head = head.next;
                return head;
            }
            node = head;
            for (int i = 1; i < index - 1; i++) {
                node = node.next;
            }
            node.next = node.next.next;

            return head;
        }

        /**
         * 方法一：暴力解法-添加哑节点
         */
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode node = head;
            int size = 0;
            while (node != null) {
                size++;
                node = node.next;
            }
            node = dummy;
            int index = size - n + 1;
            for (int i = 1; i < index; i++) {
                node = node.next;
            }
            node.next = node.next.next;
            return dummy.next;
        }

        /**
         * 方法二：栈
         */
        public ListNode removeNthFromEnd2(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            Deque<ListNode> stack = new LinkedList<>();
            ListNode cur = dummy;
            while (cur != null) {
                stack.push(cur);
                cur = cur.next;
            }
            for (int i = 0; i < n; i++) {
                stack.pop();
            }
            ListNode prev = stack.peek();
            prev.next = prev.next.next;
            return dummy.next;
        }

        /**
         * 方法三：双指针
         */
        public ListNode removeNthFromEnd3(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode first = head, second = dummy;
            for (int i = 0; i < n; i++) {
                first = first.next;
            }
            while (first != null) {
                first = first.next;
                second = second.next;
            }
            second.next = second.next.next;
            return dummy.next;
        }
    }
}
