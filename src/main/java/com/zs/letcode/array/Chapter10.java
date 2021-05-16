package com.zs.letcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * <p>
 * 输入："leetcode"
 * 输出："leotcede"
 * <p>
 * 提示：
 * <p>
 * 元音字母不包含字母 "y" 。
 * 相关标签
 * 双指针
 * 字符串
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x93lce/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/16 14:49
 */
public class Chapter10 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseVowels("hello"));
        System.out.println(solution.reverseVowels("leetcode"));
    }

    private static class Solution {
        public String reverseVowels(String s) {
            List<Character> vowels = Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
            char[] chars = s.toCharArray();
            int low = 0, high = chars.length - 1;
            while (low < high) {
                if (vowels.contains(s.charAt(low)) && vowels.contains(s.charAt(high))) {
                    char ch = chars[low];
                    chars[low] = chars[high];
                    chars[high] = ch;
                    low++;
                    high--;
                }
                if (!vowels.contains(s.charAt(low))) {
                    low++;
                }
                if (!vowels.contains(s.charAt(high))) {
                    high--;
                }
            }
            return new String(chars);
        }
    }
}
