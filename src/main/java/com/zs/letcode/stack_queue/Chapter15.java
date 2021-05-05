package com.zs.letcode.stack_queue;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gdwjv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/6 01:28
 */
public class Chapter15 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString("100[leetcode]"));
    }

    private static class Solution {
        /**
         * 方法一：栈操作
         *
         * @param s
         * @return
         */
        int ptr;

        public String decodeString(String s) {
            Deque<String> stack = new LinkedList<>();
            ptr = 0;
            while (ptr < s.length()) {
                char cur = s.charAt(ptr);
                if (Character.isDigit(cur)) {
                    // 获取一个数字并进栈
                    String digital = getDigital(s);
                    stack.addLast(digital);
                } else if (Character.isLetter(cur) || cur == '[') {
                    // 获取一个字母并进栈
                    stack.addLast(String.valueOf(s.charAt(ptr++)));
                } else {
                    ptr++;
                    LinkedList<String> sub = new LinkedList<>();
                    while (!"[".equals(stack.peekLast())) {
                        sub.addLast(stack.removeLast());
                    }
                    Collections.reverse(sub);
                    // 左括号出栈
                    stack.removeLast();
                    // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                    int repTime = Integer.parseInt(stack.removeLast());
                    StringBuffer t = new StringBuffer();
                    String o = getString(sub);
                    // 构造字符串
                    while (repTime-- > 0) {
                        t.append(o);
                    }
                    // 将构造好的字符串入栈
                    stack.addLast(t.toString());
                }
            }
            return getString(stack);
        }

        private String getString(Deque<String> sub) {
            StringBuffer ret = new StringBuffer();
            for (String s : sub) {
                ret.append(s);
            }
            return ret.toString();
        }

        private String getDigital(String s) {
            StringBuffer ret = new StringBuffer();
            while (Character.isDigit(s.charAt(ptr))) {
                ret.append(s.charAt(ptr++));
            }
            return ret.toString();
        }

        /**
         * 方法二：递归
         */
        String src;

        public String decodeString1(String s) {
            src = s;
            ptr = 0;
            return getString();
        }

        private String getString() {
            if (ptr == src.length() || src.charAt(ptr) == ']') {
                // String -> EPS
                return "";
            }
            char cur = src.charAt(ptr);
            int repTime = 1;
            String ret = "";
            if (Character.isDigit(cur)) {
                // String -> Digits [ String ] String
                // 解析 Digits
                repTime = getDigital();
                // 过滤左括号
                ptr++;
                // 解析 String
                String str = getString();
                // 构造字符串
                while (repTime-- > 0) {
                    ret += str;
                }
            } else if (Character.isLetter(cur)) {
                // String -> Char String
                // 解析 Char
                ret = String.valueOf(src.charAt(ptr++));
            }
            return ret + getString();
        }

        private int getDigital() {
            int ret = 0;
            while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
                ret = ret * 10 + src.charAt(ptr++) - '0';
            }
            return ret;
        }
    }
}
