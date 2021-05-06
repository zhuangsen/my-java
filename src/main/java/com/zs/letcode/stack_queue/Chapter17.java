package com.zs.letcode.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 01 矩阵
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * <p>
 * 输出：
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * 示例 2：
 * <p>
 * 输入：
 * [[0,0,0],
 * [0,1,0],
 * [1,1,1]]
 * <p>
 * 输出：
 * [[0,0,0],
 * [0,1,0],
 * [1,2,1]]
 * <p>
 * 提示：
 * <p>
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/g7pyt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/6 23:16
 */
public class Chapter17 {
    public static void main(String[] args) {

    }

    class Solution {
        /**
         * 方法一：广度优先搜索(BFS)
         *
         * @param mat
         * @return
         */
        public int[][] updateMatrix(int[][] mat) {
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            int m = mat.length, n = mat[0].length;
            int[][] dist = new int[m][n];
            boolean[][] seen = new boolean[m][n];
            Queue<int[]> queue = new LinkedList<>();
            // 将所有的 0 添加进初始队列中
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] == 0) {
                        queue.offer(new int[]{i, j});
                        seen[i][j] = true;
                    }
                }
            }
            // 广度优先搜索
            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int i = cell[0], j = cell[1];
                for (int d = 0; d < 4; d++) {
                    int ni = i + dirs[d][0];
                    int nj = j + dirs[d][1];
                    if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                        dist[ni][nj] = dist[i][j] + 1;
                        queue.offer(new int[]{ni, nj});
                        seen[ni][nj] = true;
                    }
                }
            }

            return dist;
        }
    }
}
