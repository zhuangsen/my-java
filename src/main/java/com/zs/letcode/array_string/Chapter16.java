package com.zs.letcode.array_string;

import java.util.Arrays;

/**
 * 长度最小的子数组
 * 给定一个含有n个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组[4,3]是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 进阶：
 * <p>
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/c0w4r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/29 22:49
 */
public class Chapter16 {
    public static void main(String[] args) {

    }

    private static class Solution {

        /**
         * 方法二：前缀和 + 二分查找
         */
        public int minSubArrayLen1(int target, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int[] sums = new int[n + 1];
            // 为了方便计算，令 size = n + 1
            // sums[0] = 0 意味着前 0 个元素的前缀和为 0
            // sums[1] = A[0] 前 1 个元素的前缀和为 A[0]
            // 以此类推
            for (int i = 0; i <= n; i++) {
                sums[i] = sums[i - 1] + nums[i - 1];
            }
            for (int i = 0; i <= n; i++) {
                int s = target + sums[i - 1];
                int bound = Arrays.binarySearch(sums, s);
                if (bound < 0) {
                    bound = -bound - 1;
                } else {
                    ans = Math.min(ans, bound - (i - 1));
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }

        /**
         * 方法三：滑动窗口
         *
         * @param target
         * @param nums
         * @return
         */
        public int minSubArrayLen2(int target, int[] nums) {
            int n = nums.length;
            if (n == 0) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int start = 0, end = 0;
            int sum = 0;
            while (end < n) {
                sum += nums[end];
                while (sum >= target) {
                    ans = Math.min(ans, end - start + 1);
                    sum -= nums[start];
                    start++;
                }
                end++;
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
}
