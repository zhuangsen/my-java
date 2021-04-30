package com.zs.letcode.array_string;

/**
 * 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * <p>
 * 提示：
 * <p>
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c8su7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/30 21:36
 */
public class Chapter19 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords("Let's take LeetCode contest"));
    }

    private static class Solution {
        /**
         * 方法一：使用额外空间
         *
         * @param s
         * @return
         */
        public String reverseWords(String s) {
            StringBuffer ret = new StringBuffer();
            int length = s.length();
            int i = 0;
            while (i < length) {
                int start = i;
                while (i < length && s.charAt(i) != ' ') {
                    i++;
                }
                for (int p = start; p < i; p++) {
                    ret.append(s.charAt(start + i - 1 - p));
                }
                while (i < length && s.charAt(i) == ' ') {
                    i++;
                    ret.append(' ');
                }
            }
            return ret.toString();
        }

        /**
         * 方法二：原地解法
         *
         * @param s
         * @return
         */
        public String reverseWords1(String s) {
            int length = s.length();
            int i = 0;
            while (i < length) {
                int start = i;
                while (i < length && s.charAt(i) != ' ') {
                    i++;
                }
                int left = start, right = i - 1;
                while (left < right) {
                    left++;
                    right--;
                }
                while (i < length && s.charAt(i) == ' ') {
                    i++;
                }
            }
            return s;
        }
    }
}
