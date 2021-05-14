package com.zs.letcode.recursion;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/436xj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/14 14:17
 */
public class Chapter10 {

    /**
     * Definition for singly-linked list.
     */
    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static class Solution {
        /**
         * 1，解法一
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            // 下面4行是空判断
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            ListNode dummy = new ListNode(0);
            ListNode cur = dummy;
            while (l1 != null && l2 != null) {
                // 比较一下，哪个小就把哪个放到新的链表中
                if (l1.val <= l2.val) {
                    cur.next = l1;
                    l1 = l1.next;
                } else {
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
            // 然后把那个不为空的链表挂到新的链表中
            cur.next = l1 == null ? l2 : l1;
            return dummy.next;
        }

        /**
         * 递归
         */
        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists1(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists1(l1, l2.next);
                return l2;
            }
        }

        /**
         * 递归二
         */
        public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            //只要有一个为空，就返回另一个
            if (l1 == null || l2 == null) {
                return l2 == null ? l1 : l2;
            }
            // 把小的赋值给first
            ListNode first = (l2.val < l1.val) ? l2 : l1;
            first.next = mergeTwoLists2(first.next, first == l1 ? l2 : l1);
            return first;
        }
    }
}
