package com.zs.letcode.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kbcqv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/2 22:22
 */
public class Chapter2 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 方法一：深度优先搜索(DFS)
         *
         * @param grid
         * @return
         */
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int nr = grid.length;
            int nc = grid[0].length;
            int num_islands = 0;
            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    if (grid[r][c] == '1') {
                        num_islands++;
                        dfs(grid, r, c);
                    }
                }
            }
            return num_islands;
        }

        private void dfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;
            if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
                return;
            }
            grid[r][c] = '0';
            dfs(grid, r - 1, c);
            dfs(grid, r + 1, c);
            dfs(grid, r, c - 1);
            dfs(grid, r, c + 1);
        }

        /**
         * 方法二：广度优先搜索(BFS)
         */

        public int numIslands1(char[][] grid) {
            //边界条件判断
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int nr = grid.length;
            int nc = grid[0].length;
            //统计岛屿的个数
            int num_islands = 0;
            //两个for循环遍历每一个格子
            for (int r = 0; r < nr; r++) {
                for (int c = 0; c < nc; c++) {
                    //只有当前格子是1才开始计算
                    if (grid[r][c] == '1') {
                        //如果当前格子是1，岛屿的数量加1
                        num_islands++;
                        //然后通过bfs把当前格子的上下左右4
                        //个位置为1的都要置为0，因为他们是连着
                        //一起的算一个岛屿
                        bfs(grid, r, c);
                    }
                }
            }
            return num_islands;
        }

        private void bfs(char[][] grid, int r, int c) {
            int nr = grid.length;
            int nc = grid[0].length;
            //把当前格子先置为0
            grid[r][c] = '0';
            //使用队列，存储的是格子坐标转化的值
            Queue<Integer> neighbors = new LinkedList<>();
            //我们知道平面坐标是两位数字，但队列中存储的是一位数字，
            //所以这里是把两位数字转化为一位数字
            neighbors.add(r * nc + c);
            while (!neighbors.isEmpty()) {
                //出队
                int id = neighbors.poll();
                int row = id / nc;
                int col = id % nc;
                if (row - 1 >= 0 && grid[row - 1][col] == '1') {//上
                    //如果上边格子为1，把它置为0，然后加入到队列中
                    //下面同理
                    neighbors.add((row - 1) * nc + col);
                    grid[row - 1][col] = '0';
                }
                if (row + 1 < nr && grid[row + 1][col] == '1') {//下
                    neighbors.add((row + 1) * nc + col);
                    grid[row + 1][col] = '0';
                }
                if (col - 1 >= 0 && grid[row][col - 1] == '1') {//左
                    neighbors.add(row * nc + col - 1);
                    grid[row][col - 1] = '0';
                }
                if (col + 1 < nc && grid[row][col + 1] == '1') {//右
                    neighbors.add(row * nc + col + 1);
                    grid[row][col + 1] = '0';
                }
            }
        }

        /**
         * 方法三：并查集
         * @param grid
         * @return
         */
//        public int numIslands2(char[][] grid) {
//
//        }
    }
}
