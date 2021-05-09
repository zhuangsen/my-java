package com.zs.letcode.binaryTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 路径总和
 * 给你二叉树的根节点root 和一个表示目标和的整数targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xo566j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/9 18:21
 */
public class Chapter6 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，递归求解
         */
        public boolean hasPathSum(TreeNode root, int targetSum) {
            // 如果根节点为空，或者叶子节点也遍历完了也没找到这样的结果，就返回false
            if (root == null) {
                return false;
            }
            // 如果到叶子节点了，并且剩余值等于叶子节点的值，说明找到了这样的结果，直接返回true
            if (root.left == null && root.right == null && targetSum - root.val == 0) {
                return true;
            }
            //分别沿着左右子节点走下去，然后顺便把当前节点的值减掉，左右子节点只要有一个返回true，
            //说明存在这样的结果
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }

        /**
         * 2，非递归解决 BFS解决 相加
         */
        public boolean hasPathSum1(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                TreeNode cur = stack.pop();
//                累加到叶子节点之后，结果等于sum，说明存在这样的一条路径
                if (cur.left == null && cur.right == null) {
                    if (cur.val == targetSum) {
                        return true;
                    }
                }
                //右子节点累加，然后入栈
                if (cur.right != null) {
                    cur.right.val = cur.val + cur.right.val;
                    stack.push(cur.right);
                }
                //左子节点累加，然后入栈
                if (cur.left != null) {
                    cur.left.val = cur.val + cur.left.val;
                    stack.push(cur.left);
                }
            }
            return false;
        }

        /**
         * 3，BFS解决 相减
         */
        public boolean hasPathSum2(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            root.val = targetSum - root.val;
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                //累减到根节点之后，结果为0，说明存在这样一条路径，直接返回true
                if (node.left == null && node.right == null && node.val == 0) {
                    return true;
                }
                //左子节点累减
                if (node.left != null) {
                    node.left.val = node.val - node.left.val;
                    queue.add(node.left);
                }
                //右子节点累减
                if (node.right != null) {
                    node.right.val = node.val - node.right.val;
                    queue.add(node.right);
                }
            }
            return false;
        }

        /**
         * 3，BFS解决 两个队列
         */
        public boolean hasPathSum3(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            Queue<Integer> valQueue = new LinkedList<>();
            nodeQueue.offer(root);
            valQueue.offer(root.val);
            while (!nodeQueue.isEmpty()) {
                TreeNode now = nodeQueue.poll();
                int temp = valQueue.poll();
                if (now.left == null && now.right == null) {
                    if (temp == targetSum) {
                        return true;
                    }
                    continue;
                }
                if (now.left != null) {
                    nodeQueue.offer(now.left);
                    valQueue.offer(now.left.val);
                }
                if (now.right != null) {
                    nodeQueue.offer(now.left);
                    valQueue.offer(now.left.val);
                }
            }
            return false;
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
