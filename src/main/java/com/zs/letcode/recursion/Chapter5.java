package com.zs.letcode.recursion;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
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
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/42zth/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/12 16:09
 */
public class Chapter5 {
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
         * 1，使用栈解决
         */
        public ListNode reverseList(ListNode head) {
            Deque<ListNode> stack = new LinkedList<>();
            // 把链表节点全部摘掉放到栈中
            while (head != null) {
                stack.push(head);
                head = head.next;
            }
            if (stack.isEmpty()) {
                return null;
            }
            ListNode node = stack.pop();
            ListNode dummy = node;
            // 栈中的结点全部出栈，然后重新连成一个新的链表
            while (!stack.isEmpty()) {
                ListNode tempNode = stack.pop();
                node.next = tempNode;
                node = node.next;
            }
            //最后一个结点就是反转前的头结点，一定要让他的next
            //等于空，否则会构成环
            node.next = null;
            return dummy;
        }

        /**
         * 2，双链表求解
         */
        public ListNode reverseList1(ListNode head) {
            //新链表
            ListNode newHead = null;
            while (head != null) {
                // 先保存访问的节点的下一个节点，保存起来
                // 留着下一步访问的
                ListNode temp = head.next;
                // 每次访问的原链表节点都会成为新链表的头结点，
                // 其实就是把新链表挂到访问的原链表节点的
                // 后面就行了
                head.next = newHead;
                // 更新新链表
                newHead = head;
                // 重新赋值，继续访问
                head = temp;
            }
            // 返回新链表
            return newHead;
        }


        /**
         * 方法一：迭代
         * 同上
         *
         * @param head
         * @return
         */
        public ListNode reverseList2(ListNode head) {
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

        /**
         * 方法二：递归
         */
        public ListNode reverseList3(ListNode head) {
            //终止条件
            if (head == null || head.next == null) {
                return head;
            }
            ListNode p = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return p;
        }

        /**
         * 方法二：递归的另一种方法
         */
        public ListNode reverseList4(ListNode head) {
            //终止条件
            if (head == null || head.next == null) {
                return head;
            }
            // 保存当前节点的下一个结点
            ListNode next = head.next;
            // 从当前节点的下一个结点开始递归调用
            ListNode reverse = reverseList4(next);
            // reverse是反转之后的链表，因为函数reverseList
            // 表示的是对链表的反转，所以反转完之后next肯定
            // 是链表reverse的尾结点，然后我们再把当前节点
            // head挂到next节点的后面就完成了链表的反转。
            next.next = head;
            // 这里head相当于变成了尾结点，尾结点都是为空的，
            // 否则会构成环
            head.next = null;
            return reverse;
        }

        /**
         * 尾递归
         */
        public ListNode reverseList5(ListNode head) {
            return reverseListInt(head, null);
        }

        private ListNode reverseListInt(ListNode head, ListNode newHead) {
            if (head == null) {
                return newHead;
            }
            ListNode next = head.next;
            head.next = newHead;
            return reverseListInt(next, head);
        }

        /**
         * 尾递归虽然也会不停的压栈，但由于最后返回的是递归函数的值，所以在返回的时候都会一次性出栈，不会一个个出栈这么慢。
         * 但如果我们再来改一下，像下面代码这样又会一个个出栈了
         */
        private ListNode reverseListInt1(ListNode head, ListNode newHead) {
            if (head == null) {
                return newHead;
            }
            ListNode next = head.next;
            head.next = newHead;
            ListNode node = reverseListInt1(next, head);
            return node;
        }
    }
}
