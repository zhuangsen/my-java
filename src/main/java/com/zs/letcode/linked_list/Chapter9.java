package com.zs.letcode.linked_list;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 相关标签
 * 链表
 * 双指针
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/fov6t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/19 12:37
 */
public class Chapter9 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        head.next = node1;
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode node3 = new ListNode(1);
        node2.next = node3;
//        ListNode node4 = new ListNode(5);
//        node3.next = node4;

        System.out.println(head);

        Solution solution = new Solution();
        System.out.println(solution.isPalindrome2(head));
    }

    private static class Solution {
        /**
         * 方法一：将值复制到数组中后用双指针法
         * 利用栈
         *
         * @param head
         * @return
         */
        public boolean isPalindrome(ListNode head) {
            Deque<Integer> nodeVal = new LinkedList();
            ListNode node = head;
            while (node != null) {
                nodeVal.push(node.val);
                node = node.next;
            }
            while (head != null) {
                if (head.val != nodeVal.pop()) {
                    return false;
                }
                head = head.next;
            }
            return true;
        }

        /**
         * 方法二：递归
         *
         * @param head
         * @return
         */
        private ListNode frontPointer;

        public boolean isPalindrome1(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }

        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) {
                    return false;
                }
                if (currentNode.val != frontPointer.val) {
                    return false;
                }
                frontPointer = frontPointer.next;
            }
            return true;
        }

        /**
         * 方法三：快慢指针
         */
        public boolean isPalindrome2(ListNode head) {
            if (head == null) {
                return true;
            }
            // 找到前半部分链表的尾节点并反转后半部分链表
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // 判断是否回文
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) {
                    result = false;
                }
                p1 = p1.next;
                p2 = p2.next;
            }
            // 还原链表并返回结果
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

        private ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }
    }
}
