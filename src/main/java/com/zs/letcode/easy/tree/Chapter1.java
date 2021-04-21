package com.zs.letcode.easy.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明:叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度3 。
 * <p>
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnd69e/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/20 23:44
 */
public class Chapter1 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        root.left = left;
        root.right = right;
        TreeNode leftL = new TreeNode(15);
        TreeNode rightR = new TreeNode(7);
        left.left = leftL;
        right.right = rightR;
//        TreeNode root = new TreeNode(3, left, right);
        System.out.println(root);
        Solution s= new Solution();
//        System.out.println(s.maxDepth(root));
        System.out.println(s.maxDepth1(root));
    }

    static class Solution {
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }

        public int maxDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int ans = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    size--;
                }
                ans++;
            }
            return ans;
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

//        @Override
//        public String toString() {
//            if (this.left == null && this.right == null) {
//                return String.valueOf(this.val);
//            }
//            return this.val + "," + this.left == null ? "null" : this.left.toString() + "," + this.right == null ? "null" : this.right.toString();
//        }
    }
}
