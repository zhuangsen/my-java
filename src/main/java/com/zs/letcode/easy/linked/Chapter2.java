package com.zs.letcode.easy.linked;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第n个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn2925/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2020/9/27 4:10 下午
 */
public class Chapter2 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode tail = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = tail;
        System.out.println(head);
        System.out.println(removeNthFromEnd5(head, 3));
        System.out.println(head);
    }

    /**
     * my solution
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 1;
        ListNode preNode = head;
        ListNode tail = head;

        while (preNode.next != null) {
            preNode = preNode.next;
            length++;
            if (preNode.next != null && preNode.next.next == null) {
                tail = preNode;
            }
        }
        int m = length - n + 1;

        preNode = head;
        int num = 1;

        while (preNode.next != null) {
            if (m == num) {
                break;
            }
            preNode = preNode.next;
            num++;
        }
        if (m != length) {
            preNode.val = preNode.next.val;
            preNode.next = preNode.next.next;
        } else {
            tail.next = null;
        }
        return head;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }


    /**
     * 方法三：双指针
     * 思路与算法
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     * <p>
     * 由于我们需要找到倒数第 nn 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，
     * 并且 first 比 second 超前 nn 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 nn 个节点。
     * <p>
     * 具体地，初始时 first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 nn。
     * 此时，first 和 second 之间间隔了 n-1n−1 个节点，即 first 比 second 超前了 nn 个节点。
     * <p>
     * 在这之后，我们同时使用 first 和 second 对链表进行遍历。当 first 遍历到链表的末尾（即 first 为空指针）时，second 恰好指向倒数第 nn 个节点。
     * <p>
     * 根据方法一和方法二，如果我们能够得到的是倒数第 nn 个节点的前驱节点而不是倒数第 nn 个节点的话，
     * 删除操作会更加方便。因此我们可以考虑在初始时将 second 指向哑节点，其余的操作步骤不变。这样一来，当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 0; i < n + 1; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 一种容易想到的方法是，我们首先从头节点开始对链表进行一次遍历，得到链表的长度 LL。随后我们再从头节点开始对链表进行一次遍历，当遍历到第 L-n+1L−n+1 个节点时，它就是我们需要删除的节点。
     * <p>
     * 为了与题目中的 nn 保持一致，节点的编号从 11 开始，头节点为编号 11 的节点。
     * <p>
     * 为了方便删除操作，我们可以从哑节点开始遍历 L-n+1L−n+1 个节点。当遍历到第 L-n+1L−n+1 个节点时，它的下一个节点就是我们需要删除的节点，这样我们只需要修改一次指针，就能完成删除操作。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        for (int i = 1; i < length - n + 1; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    /**
     * 方法二：栈
     * 思路与算法
     * <p>
     * 我们也可以在遍历链表的同时将所有节点依次入栈。根据栈「先进后出」的原则，
     * 我们弹出栈的第 nn 个节点就是需要删除的节点，并且目前栈顶的节点就是待删除节点的前驱节点。这样一来，删除操作就变得十分方便了。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd5(ListNode head, int n) {
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


    //  Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode head) {
            this.val = x;
            this.next = head;
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



