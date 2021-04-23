package com.zs.letcode.easy.math;

/**
 * 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * <p>
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 27
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：n = 9
 * 输出：true
 * 示例 4：
 * <p>
 * 输入：n = 45
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * -231 <= n <= 231 - 1
 * <p>
 * 进阶：
 * <p>
 * 你能不使用循环或者递归来完成本题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnsdi2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/23 21:02
 */
public class Chapter3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPowerOfThree2(27));
    }

    private static class Solution {
        /**
         * 1，一直除以3
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree(int n) {
            if (n > 1) {
                while (n % 3 == 0) {
                    n /= 3;
                }
            }
            return n == 1;
        }

        /**
         * 2，递归方式解决
         */
        public boolean isPowerOfThree1(int n) {
            return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree1(n / 3)));
        }

        /**
         * 3，算术表达式计算
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree2(int n) {
            return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        }

        /**
         * 4，解法4
         *  题中n的范围是-2^31 <= n <= 2^31 - 1，而在这个范围内3的最大幂是1162261467，
         *  在比他大就超过int表示的范围了，我们直接用它对n求余即可，过求余的结果是0，说明n是3的幂次方
         *
         * @param n
         * @return
         */
        public boolean isPowerOfThree3(int n) {
            return (n > 0 && 1162261467 % n == 0);
        }
    }
}
