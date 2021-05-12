package com.zs.letcode.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * 给定一个非负整数snumRows，生成杨辉三角的前snumRowss行。
 * <p>
 * <p>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/4mb3s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/12 14:51
 */
public class Chapter3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generate1(5);
    }

    private static class Solution {
        public List<List<Integer>> generate(int numRows) {
            // 结果值
            List<List<Integer>> res = new ArrayList();
            // 每一行的元素
            List<Integer> row = new ArrayList();
            for (int i = 0; i < numRows; i++) {
                // 下面一行都会比上面一行多一个元素，我们在第一个位置给他加个1
                // 在列表的指定位置插入指定元素，并将当前处于该位置的元素及其后续元素的索引加 1。
                row.add(0, 1);
                // 遍历每一行的结果，遍历的时候跳过第一个和最后一个，
                // 每个格子的值都是他正上面和左上角元素的和
                for (int j = 1; j < row.size() - 1; j++) {
                    row.set(j, row.get(j) + row.get(j + 1));
                }
                res.add(new ArrayList<>(row));
            }
            return res;
        }

        public List<List<Integer>> generate1(int numRows) {
            //结果值
            List<List<Integer>> res = new ArrayList();
            for (int i = 0; i < numRows; i++) {
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
            return res;
        }
    }
}
