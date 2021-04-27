package com.zs.letcode.array_string;

import java.util.*;

/**
 * 概述
 * 数组简介
 * 二维数组简介
 * 字符串简介
 * 字符串简介
 * 最长公共前缀
 * 最长回文子串
 * 翻转字符串里的单词
 * （选修）字符串匹配算法：KMP
 * 实现 strStr()
 * 双指针技巧
 * 小结
 * 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * <p>
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * <p>
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * <p>
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * @author madison
 * @description
 * @date 2021/4/27 21:36
 */
public class Chapter9 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseWords2("  Bob    Loves  Alice   "));
    }

    private static class Solution {
        /**
         * 方法一：使用语言特性
         *
         * @param s
         * @return
         */
        public String reverseWords(String s) {
            // 除去开头和末尾的空白字符
            s = s.trim();
            // 正则匹配连续的空白字符作为分隔符分割
            List<String> wordList = Arrays.asList(s.split("\\s+"));
            Collections.reverse(wordList);
            return String.join(" ", wordList);
        }

        /**
         * 方法二：自行编写对应的函数
         */
        public String reverseWords1(String s) {
            StringBuffer sb = trimSpaces(s);
            // 翻转字符串
            reverse(sb, 0, sb.length() - 1);

            // 翻转每个单词
            reverseEachWord(sb);
            return sb.toString();
        }

        private StringBuffer trimSpaces(String s) {
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                left++;
            }
            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                right--;
            }
            // 将字符串间多余的空白字符去除
            StringBuffer sb = new StringBuffer();
            while (left <= right) {
                char c = s.charAt(left);
                if (c != ' ') {
                    sb.append(c);
                } else if (sb.charAt(sb.length() - 1) != ' ') {
                    sb.append(c);
                }
                left++;
            }
            return sb;
        }

        private void reverse(StringBuffer sb, int left, int right) {
            while (left < right) {
                char tmp = sb.charAt(left);
                sb.setCharAt(left++, sb.charAt(right));
                sb.setCharAt(right--, tmp);
            }
        }

        private void reverseEachWord(StringBuffer sb) {
            int n = sb.length();
            int start = 0, end = 0;
            while (start < n) {
                // 循环至单词的末尾
                while (end < n && sb.charAt(end) != ' ') {
                    end++;
                }
                // 翻转单词
                reverse(sb, start, end - 1);
            }
        }

        /**
         * 方法三：双端队列
         */
        public String reverseWords2(String s) {
            int left = 0, right = s.length() - 1;
            // 去掉字符串开头的空白字符
            while (left <= right && s.charAt(left) == ' ') {
                left++;
            }
            // 去掉字符串末尾的空白字符
            while (left <= right && s.charAt(right) == ' ') {
                right--;
            }
            Deque<String> d = new ArrayDeque<>();
            StringBuffer word = new StringBuffer();
            while (left <= right) {
                char c = s.charAt(left);
                if ((word.length() != 0) && (c == ' ')) {
                    // 将单词 push 到队列的头部
                    d.offerFirst(word.toString());
                    word.setLength(0);
                } else if (c != ' ') {
                    word.append(c);
                }
                left++;
            }
            d.offerFirst(word.toString());
            return String.join(" ", d);
        }
    }
}
