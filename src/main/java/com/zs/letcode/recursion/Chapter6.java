package com.zs.letcode.recursion;


import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契数
 * 斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。该数列由0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
 * <p>
 * F(0) = 0，F(1)= 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * 给你 n ，请计算 F(n) 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：2
 * 输出：1
 * 解释：F(2) = F(1) + F(0) = 1 + 0 = 1
 * 示例 2：
 * <p>
 * 输入：3
 * 输出：2
 * 解释：F(3) = F(2) + F(1) = 1 + 1 = 2
 * 示例 3：
 * <p>
 * 输入：4
 * 输出：3
 * 解释：F(4) = F(3) + F(2) = 2 + 1 = 3
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 30
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/4wi01/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/13 11:47
 */
public class Chapter6 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 递归
         */
        public int fib(int n) {
            if (n < 2) {
                return n;
            }
            return fib(n - 1) + fib(n - 2);
        }

        /**
         * 递归优化
         *
         * @param n
         * @return
         */
        Map<Integer, Integer> cache = new HashMap<>();

        public int fib1(int n) {
            if (cache.containsKey(n)) {
                return cache.get(n);
            }

            int result;
            if (n < 2) {
                result = n;
            } else {
                result = fib1(n - 1) + fib1(n - 2);
            }
            cache.put(n, result);
            return result;
        }

        /**
         * 方法一：动态规划
         */
        public int fib2(int n) {
            if (n < 2) {
                return n;
            }
            int p = 0, q = 0, r = 1;
            for (int i = 2; i <= n; i++) {
                p = q;
                q = r;
                r = p + q;
            }
            return r;
        }

        /**
         * 方法三：通项公式
         */
        public int fib3(int n) {
            double sqrt5 = Math.sqrt(5);
            double fiN = Math.pow((1 + sqrt5) / 2, n) - Math.pow((1 - sqrt5) / 2, n);
            return (int) Math.round(fiN / sqrt5);
        }
    }
}
