package com.zs.letcode.easy.dynamicPlanning;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnq4km/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 22:19
 */
public class Chapter4 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{2, 7, 9, 3, 1}));
    }

    private static class Solution {
        /**
         * 1，动态规划解决
         *
         * @param nums
         * @return
         */
        public int rob(int[] nums) {
            // 边界条件判断
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int[][] dp = new int[length][2];
            dp[0][0] = 0; // 第1家没偷
            dp[0][1] = nums[0]; //第1家偷了
            // 第2个开始判断
            for (int i = 1; i < length; i++) {
                // 下面两行是递推公式
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = dp[i - 1][0] + nums[i];
            }
            // 最后取最大值即可
            return Math.max(dp[length - 1][0], dp[length - 1][1]);
        }

        /**
         * 2，动态规划优化
         *
         * @param nums
         * @return
         */
        public int rob1(int[] nums) {
            //边界条件判断
            if (nums == null || nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int dp0 = 0;//第1家没偷
            int dp1 = nums[0];//第1家偷了
            //从第2个开始判断
            for (int i = 1; i < length; i++) {
                //防止dp0被修改之后对下面运算造成影响，这里
                //使用一个临时变量temp先把结果存起来，计算完
                //之后再赋值给dp0.
                int temp = Math.max(dp0, dp1);
                dp1 = dp0 + nums[i];
                dp0 = temp;
            }
            return Math.max(dp0, dp1);
        }

        /**
         * 3，递归方式解决
         *
         * @param nums
         * @return
         */
        public int rob2(int[] nums) {
            return robHelper(nums, nums.length - 1);
        }

        private int robHelper(int[] nums, int i) {
            //终止条件
            if (i < 0) {
                return 0;
            }
            //偷上上家之前所能得到的最大值
            int lastLast = robHelper(nums, i - 2);
            //偷上家之前所能得到的最大值
            int last = robHelper(nums, i - 1);
            //偷上上家之前的还可以再偷当前这一家
            int cur = lastLast + nums[i];
            //然后返回偷当前这一家和不偷当前这一家的最大值
            return Math.max(last, cur);
        }

        /**
         * 4，递归代码优化
         *
         * @param nums
         * @return
         */
        public int rob3(int[] nums) {
            return robHelper(nums, nums.length - 1, new HashMap<>());
        }

        private int robHelper(int[] nums, int i, Map<Integer, Integer> map) {
            //终止条件
            if (i < 0) {
                return 0;
            }
            int lastLast = 0;
            int last = 0;
            //查看map中是否存在，如果存在就从map中取，不用再计算了
            if (map.containsKey(i - 2)) {
                lastLast = map.get(i - 2);
            } else {
                //偷上上家之前所能得到的最大值
                lastLast = robHelper(nums, i - 2, map);
                //如果map中不存在就计算，计算完之后要存储在map中，下次用的
                //时候直接从map中取，不用再计算了。
                map.put(i - 2, lastLast);
            }

            //原理同时
            if (map.containsKey(i - 1)) {
                last = map.get(i - 1);
            } else {
                //偷上家之前所能得到的最大值
                last = robHelper(nums, i - 1, map);
                map.put(i - 1, last);
            }
            //偷上上家之前的还可以再偷当前这一家
            int cur = lastLast + nums[i];
            //然后返回偷当前这一家和不偷当前这一家的最大值
            return Math.max(last, cur);
        }
    }
}
