package com.zs.letcode.easy.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计数质数
 * 统计所有小于非负整数n的质数的数量。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnzlu6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/23 18:18
 */
public class Chapter2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countPrimes3(10));
    }

    private static class Solution {
        /**
         * 方法一：枚举
         *
         * @param n
         * @return
         */
        public int countPrimes(int n) {
            int ans = 0;
            for (int i = 2; i < n; i++) {
                ans += isPrime(i) ? 1 : 0;
            }
            return ans;
        }

        private boolean isPrime(int i) {
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 方法二：埃氏筛
         *
         * @param n
         * @return
         */
        public int countPrimes1(int n) {
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            int ans = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime[i] == 1) {
                    ans += 1;
                    if ((long) i * i < n) {
                        for (int j = i * i; j < n; j += i) {
                            isPrime[j] = 0;
                        }
                    }
                }
            }
            return ans;
        }

        public int countPrimes2(int n) {
            boolean[] notPrimes = new boolean[n];
            int count = 0;
            for (int i = 2; i < n; i++) {
                //如果是合数就不需要统计
                if (notPrimes[i]) {
                    continue;
                }
                count++;
                //到这一步说明不是素数，直接让他的2倍，3倍……都标记为非素数即可
                for (int j = i; j < n; j += i) {
                    notPrimes[j] = true;
                }
            }
            return count;
        }

        /**
         * 方法三：线性筛
         *
         * @param n
         * @return
         */
        public int countPrimes3(int n) {
            List<Integer> primes = new ArrayList<>();
            int[] isPrime = new int[n];
            Arrays.fill(isPrime, 1);
            for (int i = 2; i < n; i++) {
                if (isPrime[i] == 1) {
                    primes.add(i);
                }
                for (int j = 0; j < primes.size() && i * primes.get(j) < n; j++) {
                    isPrime[i * primes.get(j)] = 0;
                    if (i * primes.get(j) == 0) {
                        break;
                    }
                }
            }
            return primes.size();
        }
    }
}
