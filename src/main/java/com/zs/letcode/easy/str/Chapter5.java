package com.zs.letcode.easy.str;

import java.util.Arrays;

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
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xne8id/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2020/9/21 1:22 下午
 */
public class Chapter5 {
    public static void main(String[] args) {
        System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome1("race a car"));
        System.out.println(isPalindrome1("OP"));
        System.out.println(~1);
    }

    public static boolean isPalindrome1(String s) {
        String s1 = s.toLowerCase();
        char[] chars = s1.toCharArray();
        char[] newChars = new char[s1.length()];
        int index = 0;
        for (char ch : chars) {
            if (ch >= 'a' && ch <= 'z') {
                newChars[index] = ch;
                index++;
            }
        }
        newChars = Arrays.copyOfRange(newChars, 0, index);
        for (char ch : newChars) {
            System.out.print(ch + "\t");
        }
        int i = 0, j = newChars.length - 1;
        while (i < j) {
            if (newChars[i] != newChars[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome2(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stringBuffer.append(Character.toLowerCase(ch));
            }
        }
        int n = stringBuffer.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right++;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }

        }
        return true;
    }

    public static boolean isPalindrome3(String s) {
        int length = s.length();
        int left = 0, right = length - 1;
        while (left < right) {
            if (Character.isLowerCase(s.charAt(left)) != Character.isLowerCase(s.charAt(right))) {
                return false;
            }
            ++left;
            --right;
        }

        return true;
    }

    public static boolean isPalindrome4(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stringBuffer.append(Character.toLowerCase(ch));
            }
        }
        StringBuffer stringBuffer_rev = new StringBuffer(stringBuffer).reverse();
        return stringBuffer.toString().equals(stringBuffer_rev.toString());
    }

}
