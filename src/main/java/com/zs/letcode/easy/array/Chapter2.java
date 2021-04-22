package com.zs.letcode.easy.array;

/**
 * @author madison
 * @description 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * @date 2020/9/9 4:54 下午
 */
public class Chapter2 {

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));
    }

    /**
     * 1，动态规划解决
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
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
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
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
    public static int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int length = prices.length;
        int hold = -prices[0];//持有股票
        int noHold = 0;//不持有股票
        for (int i = 1; i < length; i++) {
            //递归公式
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        //毋庸置疑，最后肯定是手里没持有股票利润才会最大，
        //也就是卖出去了
        return noHold;
    }

    /**
     * 3，贪心算法解决
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }


}
