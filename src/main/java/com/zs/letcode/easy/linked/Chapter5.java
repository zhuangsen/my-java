package com.zs.letcode.easy.linked;

import java.util.ArrayList;

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
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnv1oc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/20 15:40
 */
public class Chapter5 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode tail = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = tail;
        System.out.println(head);
//        System.out.println(isPalindrome(head));

        Chapter5 chapter5 = new Chapter5();
        System.out.println(chapter5.isPalindrome2(head));
    }

    /**
     * 方法一：将值复制到数组中后用双指针法
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        ListNode currentNode = head;
        ArrayList<Integer> vals = new ArrayList<>();
        while (currentNode != null) {
            vals.add(currentNode.val);
            currentNode = currentNode.next;
        }
        for (int i = 0, j = vals.size() - 1; i < j; i++, j--) {
            if (!vals.get(i).equals(vals.get(j))) {
                return false;
            }
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

    private boolean isPalindrome1(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(frontPointer);
    }

    /**
     * 方法三：快慢指针
     * 思路
     * <p>
     * 避免使用 O(n)O(n) 额外空间的方法就是改变输入。
     * <p>
     * 我们可以将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。比较完成后我们应该将链表恢复原样。虽然不需要恢复也能通过测试用例，但是使用该函数的人通常不希望链表结构被更改。
     * <p>
     * 该方法虽然可以将空间复杂度降到 O(1)O(1)，但是在并发环境下，该方法也有缺点。在并发环境下，函数运行时需要锁定其他线程或进程对链表的访问，因为在函数执行过程中链表会被修改。
     * <p>
     * 算法
     * <p>
     * 整个流程可以分为以下五个步骤：
     * <p>
     * 找到前半部分链表的尾节点。
     * 反转后半部分链表。
     * 判断是否回文。
     * 恢复链表。
     * 返回结果。
     * 执行步骤一，我们可以计算链表节点的数量，然后遍历链表找到前半部分的尾节点。
     * <p>
     * 我们也可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
     * <p>
     * 若链表有奇数个节点，则中间的节点应该看作是前半部分。
     * <p>
     * 步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
     * <p>
     * 步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
     * <p>
     * 步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
     * <p>
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list/solution/hui-wen-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    private boolean isPalindrome2(ListNode head) {
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

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
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