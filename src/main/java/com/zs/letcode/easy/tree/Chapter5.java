package com.zs.letcode.easy.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * <p>
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 按 严格递增 顺序排列
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 00:14
 */
public class Chapter5 {
    public static void main(String[] args) {

        int[] nums = new int[]{-10, -3, 0, 5, 9};
        Solution s = new Solution();

        TreeOperation.show(s.sortedArrayToBST(nums));
    }

    static class Solution {
        /**
         * 1，递归解决
         *
         * @param nums
         * @return
         */
        public TreeNode sortedArrayToBST(int[] nums) {
//            if (nums.length == 0) {
//                return null;
//            }
            return sortedArrayToBST(nums, 0, nums.length - 1);
        }

        Random rand = new Random();

        public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
            if (start > end) {
                return null;
            }
            int mid = (start + end) >> 1;
            // 总是选择中间位置左边的数字作为根节点
            // int mid = (start + end) / 2;
            // 总是选择中间位置右边的数字作为根节点
            // int mid = (start + end + 1) / 2;
            // 选择任意一个中间位置数字作为根节点
            // int mid = (start + start + rand.nextInt(2)) / 2;

            TreeNode root = new TreeNode(nums[mid]);
            root.left = sortedArrayToBST(nums, start, mid - 1);
            root.right = sortedArrayToBST(nums, mid + 1, end);
            return root;
        }

        /**
         * 2，BFS解决
         *
         * @param nums
         * @return
         */
        public TreeNode sortedArrayToBST1(int[] nums) {
            if (nums.length == 0) {
                return null;
            }
            Queue<int[]> rangeQueue = new LinkedList<>();
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            int lo = 0;
            int hi = nums.length - 1;
            int mid = (lo + hi) >> 1;
            TreeNode node = new TreeNode(nums[mid]);
            rangeQueue.add(new int[]{lo, mid - 1});
            rangeQueue.add(new int[]{mid + 1, hi});
            nodeQueue.add(node);
            nodeQueue.add(node);

            while (!rangeQueue.isEmpty()) {
                int[] range = rangeQueue.poll();
                TreeNode currentNode = nodeQueue.poll();
                lo = range[0];
                hi = range[1];
                if (lo > hi) {
                    continue;
                }
                mid = (lo + hi) >> 1;
                int midValue = nums[mid];
                TreeNode newNode = new TreeNode(midValue);
                if (midValue > currentNode.val) {
                    currentNode.right = newNode;
                } else {
                    currentNode.left = newNode;
                }
                if (lo < hi) {
                    rangeQueue.add(new int[]{lo, mid - 1});
                    rangeQueue.add(new int[]{mid + 1, lo});
                    nodeQueue.add(newNode);
                    nodeQueue.add(newNode);
                }
            }
            return node;
        }

    }
}
