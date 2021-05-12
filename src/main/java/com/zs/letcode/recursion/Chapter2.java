package com.zs.letcode.recursion;

/**
 * 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：head = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1]
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 * <p>
 * 进阶：你能在不修改链表节点值的情况下解决这个问题吗?（也就是说，仅修改节点本身。）
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/4p9kt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/12 13:37
 */
public class Chapter2 {
    public static void main(String[] args) {

    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
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
         * 1，非递归解决
         */
        public ListNode swapPairs(ListNode head) {
            //链表至少有2个节点才能交换，否则就不要交换
            if (head == null || head.next == null) {
                return head;
            }
            //这里的first，second，third可以参照图中的标注
            ListNode first = head;
            ListNode second;
            ListNode third;

            //这个是交换链表之后的尾结点，他的next要指向新交换的链表
            ListNode preLast = null;
            //这个只赋值一次，它是要返回的新链表的头结点
            ListNode newHead = head.next;
            //如果能交换就继续操作
            while (first != null && first.next != null) {
                //给second，third赋值
                second = first.next;
                third = second.next;
                //first和second这两个节点交换
                first.next = third;
                second.next = first;

                //这个时候second就是交换之后新链表的头结点，如果preLast不为空，说明前面还有交换完成的链表，要让preLast的next指向新链表的头结点
                if (preLast != null) {
                    preLast.next = second;
                }
                //因为first和second交换之后，first就变成新链表
                //的尾结点了，把它保存在preLast中
                preLast = first;

                //前两个交换了，然后从第3个在继续操作
                first = third;
            }
            //返回新链表
            return newHead;
        }

        /**
         * 2，递归解法
         */
        public ListNode swapPairs1(ListNode head) {
            //边界条件判断
            if (head == null || head.next == null) {
                return head;
            }
            //从第3个链表往后进行交换
            ListNode third = swapPairs1(head.next.next);
            //从第3个链表往后都交换完了，我们只需要交换前两个链表即可,
            //这里我们把链表分为3组，分别是第1个节点，第2个节点，后面的所有节点，也就是1→2→3，我们要把它变为2→1→3
            ListNode second = head.next;
            head.next = third;
            second.next = head;
            return second;
        }

        /**
         * 3，只交换节点的值
         */
        public ListNode swapPairs2(ListNode head) {
            int first;
            ListNode temp = head;
            while (temp != null && temp.next != null) {
                first = temp.val;
                temp.val = temp.next.val;
                temp.next.val = first;

                temp = temp.next.next;
            }
            return head;
        }
    }
}
