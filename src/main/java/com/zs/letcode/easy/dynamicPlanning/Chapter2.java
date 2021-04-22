package com.zs.letcode.easy.dynamicPlanning;

import java.util.Stack;

/**
 * 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn8fsh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 16:31
 */
public class Chapter2 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxProfit21(new int[]{7, 1, 5, 3, 6, 4}));
    }

    private static class Solution {
        /**
         * 1，动态规划解决
         *
         * @param prices
         * @return
         */
        public int maxProfit(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int length = prices.length;
            int[][] dp = new int[length][2];
            //边界条件
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            for (int i = 1; i < length; i++) {
                //递推公式
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            }
            //毋庸置疑，最后肯定是手里没持有股票利润才会最大，也就是卖出去了
            return dp[length - 1][0];
        }

        /**
         * 2，代码优化
         * 我们看到上面二维数组中计算当天的最大利润只和前一天的利润有关，所以没必要使用二维数组，
         * 只需要使用两个变量即可，一个表示当天持有股票的最大利润，一个表示当天没持有股票的最大利润，代码如下。
         * [7,1,5,3,6,4]
         *
         * @param prices
         * @return
         */
        public int maxProfit1(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int length = prices.length;
            int hold = -prices[0];//持有股票
            int noHold = 0;//不持有股票
            for (int i = 1; i < length; i++) {
                //递归公式
                noHold = Math.max(noHold, hold + prices[i]);
                hold = Math.max(hold, -prices[i]);
            }
            //毋庸置疑，最后肯定是手里没持有股票利润才会最大，
            //也就是卖出去了
            return noHold;
        }

        /**
         * 3，双指针解决
         *
         * @param prices
         * @return
         */
        public int maxProfit2(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int maxPro = 0; // 记录最大利润
            int min = prices[0]; // 记录数组中访问过的最小值
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(min, prices[i]);
                maxPro = Math.max(prices[i] - min, maxPro);
            }
            return maxPro;
        }

        public int maxProfit21(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
//                if (prices[i] < minPrice) {
//                    minPrice = prices[i];
//                } else if (prices[i] - minPrice > maxProfit) {
//                    maxProfit = prices[i] - minPrice;
//                }
                minPrice = Math.min(minPrice, prices[i]);
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
            return maxProfit;
        }

        /**
         * 4，单调栈解决
         *
         * @param prices
         * @return
         */
        public int maxProfit4(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            Stack<Integer> stack = new Stack<>();
            stack.push(prices[0]);
            int max = 0;
            for (int i = 0; i < prices.length; i++) {
                //如果栈顶元素大于prices[i]，那么栈顶元素出栈，
                //把prices[i]压栈，要始终保证栈顶元素是最小的
                if (stack.peek() > prices[i]) {
                    stack.pop();
                    stack.push(prices[i]);
                } else {
                    //否则如果栈顶元素不大于prices[i]，就要计算
                    //prices[i]和栈顶元素的差值
                    max = Math.max(max, prices[i] - stack.peek());
                }
            }

            return max;
        }

        /**
         * 5，参照最大子序和
         *
         * @param prices
         * @return
         */
        public int maxProfit5(int[] prices) {
            if (prices == null || prices.length == 0) {
                return 0;
            }
            int length = prices.length;
            int cur = 0;
            int max = cur;
            for (int i = 1; i < length; i++) {
                //这地方把prices[i]改为 prices[i] - prices[i - 1]即可
                cur = Math.max(cur, 0) + prices[i] - prices[i - 1];
                //记录最大值
                max = Math.max(max, cur);
            }
            return max;
        }

        /**
         * 6，暴力解决
         *
         * @param prices
         * @return
         */
        public int maxProfit6(int[] prices) {
            int maxProfit = 0;
            for (int i = 0; i < prices.length; i++) {
                for (int j = 1; j < prices.length; j++) {
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
            }
            return maxProfit;
        }
    }
}
