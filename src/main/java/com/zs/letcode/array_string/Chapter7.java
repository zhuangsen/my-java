package com.zs.letcode.array_string;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串""。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 * <p>
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * <p>
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/ceda1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/26 15:22
 */
public class Chapter7 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonPrefix1(new String[]{"flower", "flow", "flight"}));
    }

    private static class Solution {
        /**
         * 方法一：横向扫描
         *
         * @param strs
         * @return
         */
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            String prefix = strs[0];
            int count = strs.length;
            for (int i = 0; i < count; i++) {
                prefix = longestCommonPrefix(prefix, strs[i]);
                if (prefix.length() == 0) {
                    break;
                }
            }
            return prefix;
        }

        private String longestCommonPrefix(String prefix, String str) {
            int length = Math.min(prefix.length(), str.length());
            int index = 0;
            while (index < length && prefix.charAt(index) == str.charAt(index)) {
                index++;
            }
            return str.substring(0, index);
        }

        /**
         * 方法二：纵向扫描
         */
        public String longestCommonPrefix1(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int length = strs[0].length();
            int count = strs.length;
            for (int i = 0; i < length; i++) {
                char c = strs[0].charAt(i);
                for (int j = 1; j < count; j++) {
                    if (i == strs[j].length() || strs[j].charAt(i) != c) {
                        return strs[0].substring(0, i);
                    }
                }
            }
            return strs[0];
        }

        /**
         * 方法三：分治
         */
        public String longestCommonPrefix2(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            } else {
                return longestCommonPrefix(strs, 0, strs.length - 1);
            }
        }

        private String longestCommonPrefix(String[] strs, int start, int end) {
            if (start == end) {
                return strs[start];
            } else {
                int mid = (end - start) / 2 + start;
                String lcpLeft = longestCommonPrefix(strs, start, mid);
                String lcpRight = longestCommonPrefix(strs, mid + 1, end);
                return commonPrefix(lcpLeft, lcpRight);
            }
        }

        private String commonPrefix(String lcpLeft, String lcpRight) {
            int minLength = Math.min(lcpLeft.length(), lcpRight.length());
            for (int i = 0; i < minLength; i++) {
                if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                    return lcpLeft.substring(0, i);
                }
            }
            return lcpLeft.substring(0, minLength);
        }

        /**
         * 方法四：二分查找
         */
        public String longestCommonPrefix3(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int minLength = Integer.MIN_VALUE;
            for (String str : strs) {
                minLength = Math.min(minLength, str.length());
            }
            int low = 0, high = minLength;
            while (low < high) {
                int mid = (high - low) / 2 + low;
                if (isCommonPrefix(strs, mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return strs[0].substring(0, low);
        }

        private boolean isCommonPrefix(String[] strs, int mid) {
            String str0 = strs[0].substring(0, mid);
            int count = strs.length;
            for (int i = 1; i < count; i++) {
                String str = strs[i];
                for (int j = 0; j < mid; j++) {
                    if (str0.charAt(j) != str.charAt(j)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
