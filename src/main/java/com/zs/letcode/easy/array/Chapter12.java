package com.zs.letcode.easy.array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 * 给你两个有序整数数组nums1 和 nums2，请你将 nums2 合并到nums1中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化nums1 和 nums2 的元素数量分别为m 和 n 。你可以假设nums1 的空间大小等于m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 示例 2：
 * <p>
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * <p>
 * 提示：
 * <p>
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnumcr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 01:13
 */
public class Chapter12 {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        Solution solution = new Solution();
        solution.merge2(nums1, 3, nums2, 3);
    }


    static class Solution {
        /**
         * 使用官方api
         *
         * @param nums1
         * @param m
         * @param nums2
         * @param n
         */
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            System.arraycopy(nums2, 0, nums1, m, n);
            Arrays.sort(nums1);
            System.out.println(Arrays.toString(nums1));
        }

        /**
         * 参照归并排序
         *
         * @param nums1
         * @param m
         * @param nums2
         * @param n
         */
        public void merge1(int[] nums1, int m, int[] nums2, int n) {
            int[] temp = new int[m + n];
            int index = 0, i = 0, j = 0;
            while (i < m && j < n) {
                if (nums1[i] <= nums2[j]) {
                    temp[index++] = nums1[i++];
                } else {
                    temp[index++] = nums2[j++];
                }
            }
            for (; i < m; ) {
                temp[index++] = nums1[i++];
            }
            for (; j < n; ) {
                temp[index++] = nums2[j++];
            }
            //再把数组temp中的值赋给nums1
            for (int k = 0; k < m + n; k++) {
                nums1[k] = temp[k];
            }
            System.out.println(Arrays.toString(nums1));
        }

        public void merge2(int[] nums1, int m, int[] nums2, int n) {
            int i = m - 1;
            int j = n - 1;
            int end = m + n - 1;
            while (j >= 0) {
                nums1[end--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
            }
            System.out.println(Arrays.toString(nums1));
        }
    }
}
