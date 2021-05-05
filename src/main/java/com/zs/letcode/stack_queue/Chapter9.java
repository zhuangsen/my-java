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
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gpfm5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/5 14:35
 */
public class Chapter9 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println();
    }

    private static class Solution {
        /**
         * 1，DFS解决
         *
         * @param grid
         * @return
         */
        public int numIslands(char[][] grid) {
            //边界条件判断
            if (grid == null || grid.length == 0) {
                return 0;
            }
            //统计岛屿的个数
            int count = 0;
            //两个for循环遍历每一个格子
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    //只有当前格子是1才开始计算
                    if (grid[i][j] == '1') {
                        //如果当前格子是1，岛屿的数量加1
                        count++;
                        //然后通过dfs把当前格子的上下左右4
                        //个位置为1的都要置为0，因为他们是连着
                        //一起的算一个岛屿，
                        dfs(grid, i, j);
                    }
                }
            }
            //最后返回岛屿的数量
            return count;
        }

        //这个方法会把当前格子以及他邻近的为1的格子都会置为1
        private void dfs(char[][] grid, int i, int j) {
            //边界条件判断，不能越界
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
                return;
            }
            //把当前格子置为0，然后再从他的上下左右4个方向继续遍历
            grid[i][j] = '0';
            dfs(grid, i - 1, j);//上
            dfs(grid, i + 1, j);//下
            dfs(grid, i, j - 1);//左
            dfs(grid, i, j + 1);//右
        }

        /**
         * 2，BFS解决
         */
        public int numIslands1(char[][] grid) {
            //边界条件判断
            if (grid == null || grid.length == 0) {
                return 0;
            }
            //统计岛屿的个数
            int count = 0;
            //两个for循环遍历每一个格子
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    //只有当前格子是1才开始计算
                    if (grid[i][j] == '1') {
                        count++;
                        //然后通过bfs把当前格子的上下左右4
                        //个位置为1的都要置为0，因为他们是连着
                        //一起的算一个岛屿，
                        bfs(grid, i, j);
                    }
                }
            }
            return count;
        }

        private void bfs(char[][] grid, int i, int j) {
            //把当前格子先置为0
            grid[i][j] = '0';
            int n = grid.length;
            int m = grid[0].length;
            //使用队列，存储的是格子坐标转化的值
            Queue<Integer> queue = new LinkedList<>();
            //我们知道平面坐标是两位数字，但队列中存储的是一位数字，
            //所以这里是把两位数字转化为一位数字
            int code = i * m + j;
            //坐标转化的值存放到队列中
            queue.add(code);
            while (!queue.isEmpty()) {
                //出队
                code = queue.poll();
                //在反转成坐标值（i，j）
                int x = code / m;
                int y = code % m;
                if (x > 0 && grid[x - 1][y] == '1') {//上
                    //如果上边格子为1，把它置为0，然后加入到队列中
                    //下面同理
                    grid[x - 1][y] = '0';
                    queue.add((x - 1) * m + y);
                }
                if (x < n - 1 && grid[x + 1][y] == '1') {//下
                    grid[x + 1][y] = '0';
                    queue.add((x + 1) * m + y);
                }
                if (y > 0 && grid[x][y - 1] == '1') {//左
                    grid[x][y - 1] = '0';
                    queue.add(x * m + y - 1);
                }
                if (y < m - 1 && grid[x][y + 1] == '1') {//右
                    grid[x][y + 1] = '0';
                    queue.add(x * m + y + 1);
                }
            }
        }
    }
}
