package com.zs.letcode.array_string;

/**
 * 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/array-and-string/cxqdh/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/25 21:06
 */
public class Chapter2 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 7));
    }

    private static class Solution {
        public int searchInsert(int[] nums, int target) {
            if (nums[0] >= target) {
                return 0;
            }
            int length = nums.length;
            if (nums[length - 1] < target) {
                return length;
            }
            if (nums[length - 1] == target) {
                return length - 1;
            }
            for (int i = 0; i < length - 1; i++) {
                if (nums[i] <= target && nums[i + 1] >= target) {
                    return i + 1;
                }
            }
            return 0;
        }

        /**
         * 方法一：二分查找
         *
         * @param nums
         * @param target
         * @return
         */
        public int searchInsert1(int[] nums, int target) {
            int len = nums.length;
            int l = 0, r = len - 1;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] < target) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            return l;
        }
    }
}
