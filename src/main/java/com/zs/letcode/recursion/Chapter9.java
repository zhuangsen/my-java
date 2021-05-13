package com.zs.letcode.recursion;

/**
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/4t1eg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/13 13:47
 */
public class Chapter9 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myPow(2, 10));
        System.out.println(solution.myPow(2.10000, 3));
        System.out.println(solution.myPow(2.00000, -2));
    }

    private static class Solution {
        /**
         * 方法一：暴力法
         */
        public double myPow(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double result = 1;
            for (int i = 1; i <= N; i++) {
                result = x * result;
            }
            return result;
        }

        /**
         * 方法二：递归快速幂算法
         */
        public double myPow1(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            return fastPow(x, N);
        }

        private double fastPow(double x, long n) {
            if (n == 0) {
                return 1.0;
            }
            double half = fastPow(x, n / 2);
            if (n % 2 == 0) {
                return half * half;
            } else {
                return half * half * x;
            }
        }

        /**
         * 方法三：迭代快速幂算法
         */
        public double myPow2(double x, int n) {
            long N = n;
            if (N < 0) {
                x = 1 / x;
                N = -N;
            }
            double ans = 1;
            double current_product = x;
            for (long i = N; i > 0; i /= 2) {
                if ((i % 2) == 1) {
                    ans = ans * current_product;
                }
                current_product = current_product * current_product;
            }
            return ans;
        }
    }
}
