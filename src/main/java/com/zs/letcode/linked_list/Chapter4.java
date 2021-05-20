package com.zs.letcode.linked_list;

import java.util.HashSet;
import java.util.Set;

/**
 * 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 如下面的两个链表：
 * <p>
 * <p>
 * 在节点 c1 开始相交。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * <p>
 * 示例2：
 * <p>
 * <p>
 * 输入：intersectVal= 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * <p>
 * 示例3：
 * <p>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * <p>
 * 注意：
 * <p>
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 * 相关标签
 * 链表
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/linked-list/jjbj2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/18 10:02
 */
public class Chapter4 {
    public static void main(String[] args) {

    }

    public class Solution {
        /**
         * 方法一: 暴力法
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode nodeA = headA;
            while (nodeA != null) {
                ListNode nodeB = headB;
                while (nodeB != null) {
                    if (nodeA == nodeB) {
                        return nodeA;
                    }
                    nodeB = nodeB.next;
                }
                nodeA = nodeA.next;
            }
            return null;
        }

        /**
         * 方法二: 哈希表法
         */
        public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
            Set<ListNode> seen = new HashSet<>();
            ListNode curNode = headA;
            while (curNode != null) {
                seen.add(curNode);
                curNode = curNode.next;
            }
            curNode = headB;
            while (curNode != null) {
                if (seen.contains(curNode)) {
                    return curNode;
                }
                curNode = curNode.next;
            }
            return null;
        }

        /**
         * 方法三：双指针法
         */
        public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
            // 特判
            if (headA == null || headB == null) {
                return null;
            }

            ListNode head1 = headA;
            ListNode head2 = headB;

            while (head1 != head2) {
                if (head1 != null) {
                    head1 = head1.next;
                } else {
                    head1 = headB;
                }

                if (head2 != null) {
                    head2 = head2.next;
                } else {
                    head2 = headA;
                }
            }
            return head1;
        }
    }
}
