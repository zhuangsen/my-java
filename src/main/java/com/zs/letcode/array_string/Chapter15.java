package com.zs.letcode.array_string;

/**
 * 最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：[1,1,0,1,1,1]
 * 输出：3
 * 解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
 * <p>
 * 提示：
 * <p>
 * 输入的数组只包含0 和 1 。
 * 输入数组的长度是正整数，且不超过 10,000。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cd71t/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/29 22:18
 */
public class Chapter15 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findMaxConsecutiveOnes1(new int[]{1, 0, 1, 1, 0, 1}));
    }

    private static class Solution {
        /**
         * 我的解法
         *
         * @param nums
         * @return
         */
        public int findMaxConsecutiveOnes(int[] nums) {
            int num = 0, n = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 1) {
                    num = Math.max(num, ++n);
                } else {
                    n = 0;
                }
            }
            return num;
        }

        /**
         * 双指针
         */
        public int findMaxConsecutiveOnes1(int[] nums) {
            int slow = -1, num = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 1) {
                    num = Math.max(num, i - slow);
                    slow = i;
                }
            }
            return num;
        }
    }
}
