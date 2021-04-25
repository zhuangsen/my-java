package com.zs.letcode.easy.math;

import java.util.*;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
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
 * 示例2：
 * <p>
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例3：
 * <p>
 * 输入：s = "(]"
 * 输出：false
 * 示例4：
 * <p>
 * 输入：s = "([)]"
 * 输出：false
 * 示例5：
 * <p>
 * 输入：s = "{[]}"
 * 输出：true
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnbcaj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/25 13:50
 */
public class Chapter9 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isValid("()[]{}"));
    }

    private static class Solution {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            char[] chars = s.toCharArray();
            for (char c : chars) {
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

        public boolean isValid1(String s) {
            int n = s.length();
            if (n % 2 == 1) {
                return false;
            }
            Map<Character, Character> pairs = new HashMap<>() {{
                put(')', '(');
                put('}', '{');
                put(']', '[');
            }};
            Deque<Character> stack = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                if (pairs.containsKey(ch)) {
                    if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
                        return false;
                    }
                    stack.pop();
                } else {
                    stack.push(ch);
                }
            }
            return stack.isEmpty();
        }
    }
}
