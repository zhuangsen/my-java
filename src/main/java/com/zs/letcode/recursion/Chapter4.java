package com.zs.letcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角 II
 * 给定一个非负索引sk，其中 ks≤s33，返回杨辉三角的第 k 行。
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 3
 * 输出: [1,3,3,1]
 * 进阶：
 * <p>
 * 你可以优化你的算法到 O(k) 空间复杂度吗？
 * <p>
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/4a18d/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/12 15:27
 */
public class Chapter4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getRow(5);
    }

    private static class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> res = new ArrayList();
            for (int i = 0; i <= rowIndex; i++) {
                res.add(0, 1);
                for (int j = 1; j < res.size() - 1; j++) {
                    res.set(j, res.get(j) + res.get(j + 1));
                }
            }
            return res;
        }

        /**
         * 方法一：递推
         */
        public List<Integer> getRow1(int rowIndex) {
            //结果值
            List<List<Integer>> res = new ArrayList();
            for (int i = 0; i <= rowIndex; i++) {
                //每一行的元素
                List<Integer> row = new ArrayList();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                    }
                }
                res.add(row);
            }
            return res.get(rowIndex);
        }

        /**
         * 方法一：递推优化
         */
        public List<Integer> getRow2(int rowIndex) {
            List<Integer> res = new ArrayList();
            for (int i = 0; i <= rowIndex; i++) {
                List<Integer> cur = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        cur.add(1);
                    } else {
                        cur.add(res.get(j - 1) + res.get(j));
                    }
                }
                res = cur;
            }
            return res;
        }

        /**
         * 方法一：递推进一步优化
         */
        public List<Integer> getRow3(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i <= rowIndex; i++) {
                row.add(0);
                for (int j = i; j > 0; j--) {
                    row.set(j, row.get(j) + row.get(j - 1));
                }
            }
            return row;
        }

        /**
         * 方法二：线性递推
         */
        public List<Integer> getRow4(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i <= rowIndex; i++) {
                row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
            }
            return row;
        }
    }
}
