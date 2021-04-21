package com.zs.letcode.easy.linked;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnwzei/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/20 22:24
 */
public class Chapter6 {

    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode tail = new ListNode(-4);
        head.next = node2;
        node2.next = node3;
        node3.next = tail;
        tail.next = node2;
        System.out.println(head);

        Solution s = new Solution();
        System.out.println(s.hasCycle1(head));
    }

    static class Solution {
        public boolean hasCycle(ListNode head) {
            Set<ListNode> seen = new HashSet<>();
            while (head != null) {
                if (!seen.add(head)) {
                    return true;
                }
                head = head.next;
            }
            return false;
        }

        public boolean hasCycle1(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            while (slow != fast) {
                if (fast == null || fast.next == null) {
                    return false;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return true;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
            next = null;
        }

//        @Override
//        public String toString() {
//            if (this.next == null) {
//                return String.valueOf(this.val);
//            }
//            return this.val + "->" + this.next.toString();
//        }
    }
}
