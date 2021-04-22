package com.zs.letcode.easy.dynamicPlanning;

/**
 * 爬楼梯
 * 假设你正在爬楼梯。需要 n阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn854d/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 14:04
 */
public class Chapter1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.climbStairs4(45));
    }


    private static class Solution {
        /**
         * 1，递归的写法
         * 这题我们可以参照之前分析的青蛙跳台阶问题，其实原理是完全一样的
         * 我们来分析一下：
         * <p>
         * 当n等于1的时候，只需要跳一次即可，只有一种跳法，记f(1)=1
         * 当n等于2的时候，可以先跳一级再跳一级，或者直接跳二级，共有2种跳法，记f(2)=2
         * 当n等于3的时候，他可以从一级台阶上跳两步上来，也可以从二级台阶上跳一步上来，所以总共有f(3)=f(2)+f(1)；
         * 同理当等于n的时候，总共有f(n)=f(n-1)+f(n-2)(这里n>2)种跳法。
         *
         * @param n
         * @return
         */
        //这种当n比较大的时候会超时，所以不推荐
        public int climbStairs(int n) {
            if (n <= 1) {
                return 1;
            }
            if (n < 3) {
                return n;
            }
            return climbStairs(n - 1) + climbStairs(n - 2);
        }

        //如果还想使用递归我们可以改为尾递归的方式，我们来看下代码
        public int climbStairs1(int n) {
            return Fibonacci(n, 1, 1);
        }

        private int Fibonacci(int n, int a, int b) {
            if (n <= 1) {
                return b;
            }
            return Fibonacci(n - 1, b, a + b);
        }

        /**
         * 2，非递归
         * 但递归由于重复计算，导致效率很差，我们来看一下，有很多重复的计算，相同的颜色表示计算多次
         *
         * @param n
         * @return
         */
        public int climbStairs2(int n) {
            if (n <= 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        /**
         * 3,非递归优化
         * 我们看到上面的数组当前值是依赖他前面两个值的（前两个除外），我们只需要用两个临时变量即可，不需要申请一个数组，这个也是击败了100%的用户
         *
         * @param n
         * @return
         */
        public int climbStairs3(int n) {
            if (n <= 2) {
                return n;
            }
            int first = 1, second = 2, sum = 0;
            while (n-- > 2) {
                sum = first + second;
                first = second;
                second = sum;
            }
            return sum;
        }

        /**
         * 4,公式计算
         * 还可以参照我公众号的这篇文章356，青蛙跳台阶相关问题，我们可以找到他的递推公式是
         * <p>
         * 这个公式其实就是斐波那契公式，1,1,2,3,5,8,13……，
         * 但我们这道题的前几项是1,2,3,5,8……明显比那公式少了一项，所以这里计算第n项的指数应该是n+1
         * 这种解决方式也击败了100%的用户
         */
        public int climbStairs4(int n) {
            double sqrt = Math.sqrt(5);
            return (int) ((Math.pow((1 + sqrt) / 2, n + 1) - Math.pow((1 - sqrt) / 2, n + 1)) / sqrt);
        }
    }
}
