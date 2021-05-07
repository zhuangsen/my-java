package com.zs.letcode.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xebrb2/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/8 00:10
 */
public class Chapter3 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，递归解法
         *
         * @param root
         * @return
         */
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            postorder(root, res);
            return res;
        }

        private void postorder(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }
            postorder(node.left, res);
            postorder(node.right, res);
            res.add(node.val);
        }

        /**
         * 2，非递归解法 使用栈
         */
        public List<Integer> postorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode prev = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.right == null || root.right == prev) {
                    res.add(root.val);
                    prev = root;
                    root = null;
                } else {
                    stack.push(root);
                    root = root.right;
                }
            }
            return res;
        }

        /**
         * 方法三：Morris 遍历
         */
        public List<Integer> postorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            TreeNode p1 = root, p2 = null;
            while (p1 != null) {
                p2 = p1.left;
                if (p2 != null) {
                    while (p2.right != null && p2.right != p1) {
                        p2 = p2.right;
                    }
                    if (p2.right == null) {
                        p2.right = p1;
                        p1 = p1.left;
                        continue;
                    } else {
                        p2.right = null;
                        addPath(res, p1.left);
                    }
                }
                p1 = p1.right;
            }
            addPath(res, root);
            return res;
        }

        private void addPath(List<Integer> res, TreeNode node) {
            int count = 0;
            while (node != null) {
                count++;
                res.add(node.val);
                node = node.right;
            }
            int left = res.size() - count, right = res.size() - 1;
            while (left < right) {
                int temp = res.get(left);
                res.set(left, res.get(right));
                res.set(right, temp);
                left++;
                right--;
            }
        }
    }


    private static class TreeNode {
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
    }
}
