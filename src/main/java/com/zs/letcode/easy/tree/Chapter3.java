package com.zs.letcode.easy.tree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * 例如，二叉树[1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * 进阶：
 * <p>
 * 你可以运用递归和迭代两种方法解决这个问题吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn7ihv/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/21 13:35
 */
public class Chapter3 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(6);
        root.left = left;
        root.right = right;
        TreeNode leftL = new TreeNode(15);
        TreeNode rightR = new TreeNode(7);
        left.left = leftL;
        right.right = rightR;
//        TreeNode root = new TreeNode(3, left, right);
        Solution s = new Solution();
        System.out.println(s.isSymmetric(root));

        TreeOperation.show(root);
    }

    static class Solution {
        /**
         * 1，递归解决
         *
         * @param root
         * @return
         */
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            //从两个子节点开始判断
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            //如果左右子节点都为空，说明当前节点是叶子节点，返回true
            if (left == null && right == null) {
                return true;
            }
            //如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            //然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
            return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
        }

        /**
         * 2，非递归解决
         *
         * @param root
         * @return
         */
        public boolean isSymmetric1(TreeNode root) {
            // 队列
            Queue<TreeNode> queue = new LinkedList<>();
            if (root == null) {
                return true;
            }
            //左子节点和右子节点同时入队
            queue.add(root.left);
            queue.add(root.right);
            //如果队列不为空就继续循环
            while (!queue.isEmpty()) {
                //每两个出队
                TreeNode left = queue.poll(), right = queue.poll();
                //如果都为空继续循环
                if (left == null && right == null) {
                    continue;
                }
                //如果一个为空一个不为空，说明不是对称的，直接返回false
                if (left == null ^ right == null) {
                    return false;
                }
                //如果这两个值不相同，也不是对称的，直接返回false
                if (left.val != right.val) {
                    return false;
                }
                //这里要记住入队的顺序，他会每两个两个的出队。
                //左子节点的左子节点和右子节点的右子节点同时
                //入队，因为他俩会同时比较。
                //左子节点的右子节点和右子节点的左子节点同时入队，
                //因为他俩会同时比较
                queue.add(left.left);
                queue.add(right.right);
                queue.add(left.right);
                queue.add(right.left);
            }
            return true;
        }
    }
}
