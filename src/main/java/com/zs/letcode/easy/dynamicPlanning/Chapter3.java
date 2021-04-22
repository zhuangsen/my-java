package com.zs.letcode.easy.dynamicPlanning;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组[4,-1,2,1] 的和最大，为6 。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 * <p>
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 * <p>
 * 输入：nums = [-100000]
 * 输出：-100000
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 * <p>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn3cg3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 17:58
 */
public class Chapter3 {

    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，动态规划解决
         *
         * @param nums
         * @return
         */
        public int maxSubArray(int[] nums) {
            int length = nums.length;
            int[] dp = new int[length];
            //边界条件
            dp[0] = nums[0];
            int max = dp[0];
            for (int i = 1; i < length; i++) {
                //转移公式
                dp[i] = Math.max(dp[i - 1], 0) + nums[i];
                //记录最大值
                max = Math.max(max, dp[i]);
            }
            return max;
        }

        /**
         * 2，代码优化
         * 仔细看下上面的代码会发现，我们申请了一个长度为length的数组，
         * 但在转移公式计算的时候，每次计算当前值的时候只会用到前面的那个值，
         * 再往前面就用不到了，这样实际上是造成了空间的浪费。这里不需要一个数组，只需要一个临时变量即可，看下代码
         *
         * @param nums
         * @return
         */
        public int maxSubArray1(int[] nums) {
            int length = nums.length;
            int cur = nums[0];
            int max = cur;
            for (int i = 1; i < length; i++) {
                cur = Math.max(cur, 0) + nums[i];
                max = Math.max(max, cur);
            }
            return max;
        }
    }
}
