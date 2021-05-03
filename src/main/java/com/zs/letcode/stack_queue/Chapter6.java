package com.zs.letcode.stack_queue;

import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'node的字符串 s ，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "()"
 * 输出：true
 * 示例node2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例node3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例node4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例node5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * node
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/g9d0h/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/3 14:45
 */
public class Chapter6 {
    public static void main(String[] args) {
        Solution solution = new Solution();
    }

    private static class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char[] chars = s.toCharArray();
            //遍历所有的元素
            for (char c : chars) {
                //如果是左括号，就把他们对应的右括号压栈
                if (c == '(') {
                    stack.push(')');
                } else if (c == '{') {
                    stack.push('}');
                } else if (c == '[') {
                    stack.push(']');
                } else if (stack.isEmpty() || stack.pop() != c) {
                    //否则就只能是右括号。
                    //1，如果栈为空，说明括号无法匹配。
                    //2，如果栈不为空，栈顶元素就要出栈，和这个右括号比较。
                    //如果栈顶元素不等于这个右括号，说明无法匹配，
                    //直接返回false。
                    return false;
                }
            }
            //最后如果栈为空，说明完全匹配，是有效的括号。
            //否则不完全匹配，就不是有效的括号
            return stack.isEmpty();
        }
    }
}
