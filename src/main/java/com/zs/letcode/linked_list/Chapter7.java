package com.zs.letcode.linked_list;

/**
 * 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 * 示例 2：
 * <p>
 * 输入：head = [], val = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 * <p>
 * 提示：
 * <p>
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 * 相关标签
 * 链表
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/f9izv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/19 10:22
 */
public class Chapter7 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 可以通过哨兵节点去解决它，哨兵节点广泛应用于树和链表中，
         * 如伪头、伪尾、标记等，它们是纯功能的，通常不保存任何数据，
         * 其主要目的是使链表标准化，如使链表永不为空、永不无头、简化插入和删除。
         */
        public ListNode removeElements(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head);
            ListNode prev = dummy, curr = head;
            while (curr != null) {
                if (curr.val == val) {
                    prev.next = curr.next;
                } else {
                    prev = curr;
                }
                curr = curr.next;
            }
            return dummy.next;
        }

        public ListNode removeElements1(ListNode head, int val) {
            ListNode dummy = new ListNode(0, head);
            ListNode prev = dummy;
            while (prev != null && prev.next != null) {
                if (prev.next.val == val) {
                    prev.next = prev.next.next;
                    continue;
                }
                prev = prev.next;
            }
            return dummy.next;
        }
    }
}
