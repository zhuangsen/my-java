package com.zs.letcode.easy.str;

import java.util.HashMap;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * s = "leetcode"
 * 返回 0
 * <p>
 * s = "loveleetcode"
 * 返回 2
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn5z8r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2020/9/20 12:20 下午
 */
public class Chapter3 {
    public static void main(String[] args) {
        System.out.println(firstUniqChar2("loveleetcode"));
    }

    public static int firstUniqChar1(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length) {
            int j = 0;
            for (; j < chars.length; j++) {
                if (chars[i] == chars[j] && j != i) {
                    break;
                }
            }
            if (j == chars.length) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int firstUniqChar2(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
