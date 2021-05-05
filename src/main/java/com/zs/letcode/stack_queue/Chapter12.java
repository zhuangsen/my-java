package com.zs.letcode.stack_queue;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的中序遍历
 * 给定一个二叉树的根节点 root ，返回它的 中序遍历。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 * <p>
 * 输入：root = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 示例 4：
 * <p>
 * <p>
 * 输入：root = [1,2]
 * 输出：[2,1]
 * 示例 5：
 * <p>
 * <p>
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 * <p>
 * <p>
 * 进阶:递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/gnq5i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/5 23:47
 */
public class Chapter12 {
    public static void main(String[] args) {

    }


    //     * Definition for a binary tree node.
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

    private static class Solution {
        /**
         * 方法一：递归
         *
         * @param root
         * @return
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            inorder(root, res);
            return res;
        }

        private void inorder(TreeNode node, List<Integer> res) {
            if (node == null) {
                return;
            }
            inorder(node.left, res);
            res.add(node.val);
            inorder(node.right, res);
        }

        /**
         * 方法二：迭代
         */
        public List<Integer> inorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Deque<TreeNode> stack = new LinkedList<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }

        /**
         * 方法三：Morris 中序遍历
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode predecessor = null;
            while (root != null) {
                if (root.left != null) {
                    // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                    predecessor = root.left;
                    while (predecessor.right != null && predecessor.right != root) {
                        predecessor = predecessor.right;
                    }
                    // 让 predecessor 的右指针指向 root，继续遍历左子树
                    if (predecessor.right == null) {
                        predecessor.right = root;
                        root = root.left;
                    }
                    // 说明左子树已经访问完了，我们需要断开链接
                    else {
                        res.add(root.val);
                        predecessor.right = null;
                        root = root.right;
                    }
                }
                // 如果没有左孩子，则直接访问右孩子
                else {
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }
    }
}
