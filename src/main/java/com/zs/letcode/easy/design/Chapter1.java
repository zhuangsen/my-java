package com.zs.letcode.easy.design;

import java.util.Stack;

/**
 * 最小栈
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) —— 将元素 x 推入栈中。
 * pop()—— 删除栈顶的元素。
 * top()—— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * <p>
 * 示例:
 * <p>
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * <p>
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * <p>
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 提示：
 * <p>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnkq37/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/23 16:52
 */
public class Chapter1 {
    public static void main(String[] args) {
        MinStack1 minStack = new MinStack1();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

    }

    private static class MinStack {
        //链表头，相当于栈顶
        private ListNode head;

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        //压栈，需要判断栈是否为空
        public void push(int val) {
            if (isEmpty()) {
                head = new ListNode(val, val, null);
            } else {
                head = new ListNode(val, Math.min(val, head.min), head);
            }
        }

        //出栈，相当于把链表头删除
        public void pop() {
            if (isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            head = head.next;
        }

        //栈顶的值也就是链表头的值
        public int top() {
            if (isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            return head.val;
        }

        public int getMin() {
            if (isEmpty()) {
                throw new IllegalStateException("栈为空...");
            }
            return head.min;
        }

        //判断栈是否为空
        private boolean isEmpty() {
            return head == null;
        }
    }

    private static class MinStack1 {
        Stack<StackNode> stack = new Stack<>();

        //压栈
        public void push(int x) {
            if (empty()) {
                stack.push(new StackNode(x, x));
            } else {
                stack.push(new StackNode(x, Math.min(x, getMin())));
            }
        }

        //出栈
        public void pop() {
            if (empty()) {
                throw new IllegalStateException("栈为空……");
            }
            stack.pop();
        }

        public int top() {
            if (empty()) {
                throw new IllegalStateException("栈为空……");
            }
            return stack.peek().val;
        }

        private int getMin() {
            if (empty()) {
                throw new IllegalStateException("栈为空……");
            }
            return stack.peek().min;
        }

        //判断栈是否为空
        private boolean empty() {
            return stack.isEmpty();
        }
    }

    private static class StackNode {
        public int val;
        public int min;

        public StackNode(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }

    private static class ListNode {
        public int val;
        private int min;
        public ListNode next;

        public ListNode(int val, int min, ListNode next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}
