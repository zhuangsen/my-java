package com.zs.letcode.easy.math;

/**
 * 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * <p>
 * 注意：
 * 0 ≤ x, y < 231.
 * <p>
 * 示例:
 * <p>
 * 输入: x = 1, y = 4
 * <p>
 * 输出: 2
 * <p>
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * <p>
 * 上面的箭头指出了对应二进制位不同的位置。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnyode/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/24 22:47
 */
public class Chapter6 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hammingDistance(1, 4));
    }

    private static class Solution {
        /**
         * 方法一：内置位计数功能
         *
         * @param x
         * @param y
         * @return
         */
        public int hammingDistance(int x, int y) {
            return Integer.bitCount(x ^ y);
        }

        /**
         * 方法二：移位
         *
         * @param x
         * @param y
         * @return
         */
        public int hammingDistance1(int x, int y) {
            int xor = x ^ y;
            int res = 0;
            while (xor != 0) {
                if (xor % 2 == 1) {
                    res += 1;
                }
                xor = xor >> 1;
            }
            return res;
        }

        /**
         * 方法三：布赖恩·克尼根算法
         *
         * @param x
         * @param y
         * @return
         */
        public int hammingDistance2(int x, int y) {
            int xor = x ^ y;
            int res = 0;
            while (xor != 0) {
                res += xor & 1;
                xor = xor >>> 1;
            }
            return res;
        }

        // or
        public int hammingDistance3(int x, int y) {
            int xor = x ^ y;
            int res = 0;
            while (xor != 0) {
                res += 1;
                xor &= xor - 1;
            }
            return res;
        }
    }
}
