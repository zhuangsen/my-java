package com.zs.letcode.array;

/**
 * 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9tqjc/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/16 14:11
 */
public class Chapter9 {
    public static void main(String[] args) {

    }


    class Solution {
        /**
         * 方法一：筛选 + 判断
         */
        public boolean isPalindrome(String s) {
            StringBuffer sgood = new StringBuffer();
            int length = s.length();
            for (int i = 0; i < length; i++) {
                char ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sgood.append(Character.toLowerCase(ch));
                }
            }
            StringBuffer sgood_rev = new StringBuffer(sgood).reverse();
            return sgood.toString().equals(sgood_rev.toString());
        }


        /**
         * 方法二：双指针  在原字符串上直接判断
         * @param s
         * @return
         */
        public boolean isPalindrome1(String s) {
            int low = 0, high = s.length() - 1;
            s = s.toLowerCase();
            while (low < high) {
                if (!Character.isLetterOrDigit(s.charAt(low))) {
                    low++;
                    continue;
                }
                if (!Character.isLetterOrDigit(s.charAt(high))) {
                    high--;
                    continue;
                }
                if (s.charAt(low) == s.charAt(high)) {
                    low++;
                    high--;
                } else {
                    return false;
                }
            }
            return true;
        }
    }
}
