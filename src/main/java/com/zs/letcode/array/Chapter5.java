package com.zs.letcode.array;

import java.util.Arrays;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 * <p>
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [0]
 * 输出：[0]
 * 示例 4：
 * <p>
 * 输入：nums = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x9wv2h/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/15 12:42
 */
public class Chapter5 {
    public static void main(String[] args) {

    }

    private static class Solution {
        public void sortColors(int[] nums) {
            Arrays.sort(nums);
        }

        /**
         * 方法一：单指针
         */
        public void sortColors1(int[] nums) {
            int n = nums.length;
            int ptr = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[ptr];
                    nums[ptr] = temp;
                    ptr++;
                }
            }
            for (int i = ptr; i < n; i++) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = nums[ptr];
                    nums[ptr] = temp;
                    ptr++;
                }
            }
        }

        /**
         * 方法二：双指针
         */
        public void sortColors2(int[] nums) {
            int n = nums.length;
            int p0 = 0, p1 = 0;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 1) {
                    int temp = nums[i];
                    nums[i] = temp;
                    nums[p1] = temp;
                    p1++;
                } else if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = temp;
                    nums[p0] = temp;
                    if (p0 < p1) {
                        temp = nums[i];
                        nums[i] = temp;
                        nums[p1] = temp;
                    }
                    p0++;
                    p1++;
                }
            }
        }

        /**
         * 方法三：双指针
         */
        public void sortColors3(int[] nums) {
            int n = nums.length;
            int p0 = 0, p2 = n - 1;
            for (int i = 0; i < p2; i++) {
                while (i <= p2 && nums[i] == 2) {
                    int temp = nums[i];
                    nums[i] = nums[p2];
                    nums[p2] = temp;
                    p2--;
                }
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[p0];
                    nums[p0] = temp;
                    p0++;
                }
            }
        }
    }
}
