package com.zs.letcode.array_string;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * 给定一个非负整数numRows，生成杨辉三角的前numRows行。
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
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cuj3m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/30 20:06
 */
public class Chapter17 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.generate(5);
    }

    private static class Solution {
        /**
         * 方法一：数学
         *
         * @param numRows
         * @return
         */
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> ret = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        row.add(1);
                    } else {
                        row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                    }
                }
                ret.add(row);
            }
            return ret;
        }
    }
}
