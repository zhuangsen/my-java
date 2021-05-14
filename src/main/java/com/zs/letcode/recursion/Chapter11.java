package com.zs.letcode.recursion;

/**
 * 第K个语法符号
 * 在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
 * <p>
 * 给定行数N和序数 K，返回第 N 行中第 K个字符。（K从1开始）
 * <p>
 * 例子:
 * <p>
 * 输入: N = 1, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 1
 * 输出: 0
 * <p>
 * 输入: N = 2, K = 2
 * 输出: 1
 * <p>
 * 输入: N = 4, K = 5
 * 输出: 1
 * <p>
 * 解释:
 * 第一行: 0
 * 第二行: 01
 * 第三行: 0110
 * 第四行: 01101001
 * <p>
 * 注意：
 * <p>
 * N的范围[1, 30].
 * K的范围[1, 2^(N-1)].
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/46lfr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/14 14:45
 */
public class Chapter11 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 方法一： 暴力法
         */
        public int kthGrammar(int n, int k) {
            int[] lastrow = new int[1 << n];
            for (int i = 1; i < n; i++) {
                for (int j = (1 << (i - 1)); j >= 0; j--) {
                    lastrow[2 * j] = lastrow[j];
                    lastrow[2 * j + 1] = 1 - lastrow[j];
                }
            }
            return lastrow[k - 1];
        }

        /**
         * 方法二： 递归 (父子递推)
         */
        public int kthGrammar1(int n, int k) {
            if (n == 1) {
                return 0;
            }
            return (~k & 1) ^ kthGrammar1(n - 1, (k + 1) / 2);
        }

        /**
         * 方法三： 递归 (翻转递推)
         */
        public int kthGrammar2(int n, int k) {
            if (n == 1) {
                return 0;
            }
            if (k <= (1 << n - 2)) {
                return kthGrammar2(n - 1, k);
            }
            return kthGrammar2(n - 1, k - (1 << n - 2)) ^ 1;
        }

        /**
         * 方法四： Binary Count
         */
        public int kthGrammar3(int n, int k) {
            return Integer.bitCount(k - 1) % 2;
        }
    }
}
