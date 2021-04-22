package com.zs.letcode.easy.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 *     / \
 *    3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *    根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/21 00:44
 */
public class Chapter2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(6);
        root.left = left;
        root.right = right;
//        TreeNode leftL = new TreeNode(15);
//        TreeNode rightR = new TreeNode(7);
//        left.left = leftL;
//        right.right = rightR;
//        TreeNode root = new TreeNode(3, left, right);
        Solution s = new Solution();
        System.out.println(s.isValidBST1(root));
        TreeOperation.show(root);
    }

    static class Solution {

        /**
         * 1，递归写法
         *
         * @param root
         * @return
         */
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        public boolean isValidBST(TreeNode node, long lower, long upper) {
            if (node == null) {
                return true;
            }
            if (node.val <= lower || node.val >= upper) {
                return false;
            }
            return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
        }


        /**
         * 2，中序遍历递归
         *
         * @param root
         * @return
         */
        //前一个结点，全局的
        TreeNode prev;

        public boolean isValidBST2(TreeNode root) {
            if (root == null) {
                return true;
            }
            //访问左子树
            if (!isValidBST2(root.left)) {
                return false;
            }
            //访问当前节点：如果当前节点小于等于中序遍历的前一个节点直接返回false。
            if (prev != null && prev.val >= root.val) {
                return false;
            }
            prev = root;
            //访问右子树
            if (!isValidBST2(root.right)) {
                return false;
            }

            return true;
        }

        /**
         * 3，中序遍历非递归
         *
         * @param root
         * @return
         */
        public boolean isValidBST1(TreeNode root) {
            Deque<TreeNode> stack = new LinkedList<>();
            double inorder = Double.MIN_VALUE;
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (root.val <= inorder) {
                    return false;
                }
                inorder = root.val;
                root = root.right;
            }
            return true;
        }
    }

}
