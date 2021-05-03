package com.zs.letcode.stack_queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 完全平方数
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
 * <p>
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 * 示例 2：
 * <p>
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 104
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kfgtt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/3 11:24
 */
public class Chapter4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSquares1(136345345));
    }

    private static class Solution {
        /**
         * 1，广度优先搜索算法 BFS解决
         *
         * @param n
         * @return
         */
        public int numSquares(int n) {
            Queue<Integer> queue = new LinkedList<>();
            //记录访问过的节点值
            Set<Integer> visited = new HashSet<>();
            queue.offer(0);
            visited.add(0);
            //树的第几层
            int level = 0;
            while (!queue.isEmpty()) {
                //每一层的节点数量
                int size = queue.size();
                level++;
                //遍历当前层的所有节点
                for (int i = 0; i < size; i++) {
                    //节点的值
                    int digit = queue.poll();
                    //访问当前节点的子节点，类比于二叉树的左右子节点
                    for (int j = 1; j <= n; j++) {
                        //子节点的值
                        int nodeValue = digit + j * j;
                        //nodeValue始终是完全平方数的和，当他等于n的
                        //时候直接返回
                        if (nodeValue == n) {
                            return level;
                        }
                        //如果大于n，终止内层循环
                        if (nodeValue > n) {
                            break;
                        }
                        if (!visited.contains(nodeValue)) {
                            queue.offer(nodeValue);
                            visited.add(nodeValue);
                        }
                    }
                }
            }
            return level;
        }

        /**
         * 2，动态规划解决
         */
        public int numSquares1(int n) {
            int dp[] = new int[n + 1];
            dp[0] = 0;
            for (int i = 1; i <= n; i++) {
                //最坏的情况都是由1的平方组成
                dp[i] = i;
                for (int j = 1; j * j <= i; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            return dp[n];
        }

        /**
         * 3，拉格朗日四平方和定理
         * 拉格朗日四平方和定理说明任何一个数，都可以由小于等于4个的完全平方数相加得到。
         * 当n=(8b+7)*4^n的时候，n是由4个完全平方数得到，否则n只有1到3个完全平方数得到。
         */
        public int numSquares2(int n) {
            //一，先判断由1个平方数组成的
            //如果n是平方数，直接返回1即可，表示n由
            //1个平方数组成
            if (is_square(n)) {
                return 1;
            }
            //如果n是4的倍数，就除以4，因为4是2的平方，
            //如果n可以由m个完全平方数组成，那么4n也
            //可以由m个完全平方数组成
            while ((n & 3) == 0) {
                n >>= 2;
            }
            //二，在判断由4个平方数组成的
            //如果n是4的倍数，在上面代码的执行中就会一直除以4，
            //直到不是4的倍数为止，所以这里只需要判断n=(8b+7)
            //即可
            if ((n & 7) == 7) {
                return 4;
            }
            int sqrt_n = (int) Math.sqrt(n);
            //三，接着判断由2个平方数组成的
            //下面判断是否能由2个平方数组成
            for (int i = 1; i <= sqrt_n; i++) {
                if (is_square(n - i * i)) {
                    return 2;
                }
            }
            //四，剩下的只能由3个平方数组成了
            //如果上面都不成立，根据拉格朗日四平方和定理
            //只能由3个平方数组成了
            return 3;
        }

        //判断n是否是平方数
        private boolean is_square(int n) {
            int sqrt_n = (int) (Math.sqrt(n));
            return sqrt_n * sqrt_n == n;
        }
    }
}
