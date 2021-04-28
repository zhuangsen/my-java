package com.zs.letcode.array_string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 两数之和 II - 输入有序数组
 * 给定一个已按照 升序排列 的整数数组numbers ，请你从数组中找出两个数满足相加之和等于目标数target 。
 * <p>
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 * <p>
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * 示例 2：
 * <p>
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 示例 3：
 * <p>
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cnkjg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/28 22:42
 */
public class Chapter13 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 暴力
         *
         * @param numbers
         * @param target
         * @return
         */
        public int[] twoSum(int[] numbers, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < numbers.length; i++) {
                map.put(i, numbers[i]);
            }
            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < numbers.length; j++) {
                    if (target - map.get(i) == numbers[j] && i != j) {
                        return new int[]{i + 1, j + 1};
                    }
                }
            }
            return new int[]{};
        }

        /**
         * 方法一：二分查找
         *
         * @param numbers
         * @param target
         * @return
         */

        public int[] twoSum1(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; i++) {
                int low = i + 1, high = numbers.length - 1;
                while (low < high) {
                    int mid = (high - low) / 2 + low;
                    if (numbers[mid] == target - numbers[i]) {
                        return new int[]{i + 1, mid + 1};
                    } else if (numbers[mid] > target - numbers[i]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }

            }
            return new int[]{-1, -1};
        }

        /**
         * 方法二：双指针
         */
        public int[] twoSum2(int[] numbers, int target) {
            int i = 0, j = numbers.length - 1;
            while (i < j) {
                int sum = numbers[i] + numbers[j];
                if (target == sum) {
                    return new int[]{i + 1, j + 1};
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
            return new int[]{-1, -1};
        }
    }
}
