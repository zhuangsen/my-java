package com.zs.letcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/all-about-array/x90rpm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/15 13:41
 */
public class Chapter6 {
    public static void main(String[] args) {

    }

    private class Solution {
        /**
         * 方法一：基于快速排序的选择方法
         */
        Random random = new Random();

        public int findKthLargest(int[] nums, int k) {
//            Arrays.sort();
            return quickSelect(nums, 0, nums.length - 1, nums.length - k);
        }

        private int quickSelect(int[] a, int l, int r, int index) {
            int q = randomPartition(a, l, r);
            if (q == index) {
                return a[q];
            } else {
                return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
            }
        }

        private int randomPartition(int[] a, int l, int r) {
            int i = random.nextInt(r - l + 1) + l;
            swap(a, i, r);
            return partition(a, l, r);
        }

        private int partition(int[] a, int l, int r) {
            int x = a[r], i = l - 1;
            for (int j = l; j < r; j++) {
//                if (a[j] <= x) {
                if (a[j] < x) {
                    swap(a, ++i, j);
                }
            }
            swap(a, i + 1, r);
            return i + 1;
        }

        public void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        /**
         * 方法二：基于堆排序的选择方法
         */
        public int findKthLargest1(int[] nums, int k) {
            int headSize = nums.length;
            buildMaxHeap(nums, headSize);
            for (int i = nums.length - 1; i > nums.length - k + 1; --i) {
                swap(nums, 0, i);
                headSize--;
                maxHeapify(nums, 0, headSize);
            }
            return nums[0];
        }

        private void buildMaxHeap(int[] a, int headSize) {
            for (int i = headSize / 2; i >= 0; --i) {
                maxHeapify(a, i, headSize);
            }
        }

        private void maxHeapify(int[] a, int i, int headSize) {
            int l = i * 2 + 1, r = i * 2 + 2, largset = i;
            if (l < headSize && a[l] > a[largset]) {
                largset = l;
            }
            if (r < headSize && a[r] > a[largset]) {
                largset = r;
            }
            if (largset != i) {
                swap(a, i, largset);
                maxHeapify(a, largset, headSize);
            }
        }
    }
}
