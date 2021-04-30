package com.zs.letcode.array_string;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角 II
 * 给定一个非负索引k，其中 k≤33，返回杨辉三角的第 k 行。
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
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/ctyt1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/30 21:21
 */
public class Chapter18 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.getRow1(5);
    }

    private static class Solution {
        /**
         * 方法一：递推
         *
         * @param rowIndex
         * @return
         */
        public List<Integer> getRow(int rowIndex) {
            List<List<Integer>> C = new ArrayList<>();
            for (int i = 0; i <= rowIndex; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
                    }
                }
                C.add(row);
            }
            return C.get(rowIndex);
        }

        /**
         * 进一步优化
         */
        public List<Integer> getRow1(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 0; i <= rowIndex; i++) {
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
        public List<Integer> getRow2(int rowIndex) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 1; i <= rowIndex; i++) {
                row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
            }
            return row;
        }
    }
}
