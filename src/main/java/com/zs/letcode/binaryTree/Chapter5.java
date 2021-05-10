package com.zs.letcode.binaryTree;

import java.util.Deque;
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
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoh1zg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/8 23:45
 */
public class Chapter5 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，递归
         *
         * @param root
         * @return
         */

        public int maxDepth(TreeNode root) {
            return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }

        /**
         * 2，BFS
         */
        public int maxDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            // 创建一个队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int count = 0;
            while (!queue.isEmpty()) {
                //每一层的个数
                int size = queue.size();
                while (size-- > 0) {
                    TreeNode cur = queue.poll();
                    if (cur.left != null) {
                        queue.add(cur.left);
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                    }
                }
                count++;
            }
            return count;
        }

        /**
         * 3，DFS
         * 我们可以使用两个栈，一个记录节点的stack栈，一个记录节点所在层数的level栈，
         * stack中每个节点在level中都会有一个对应的值，并且他们是同时出栈，同时入栈
         */
        public int maxDepth2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //stack记录的是节点，而level中的元素和stack中的元素
            //是同时入栈同时出栈，并且level记录的是节点在第几层
            Deque<TreeNode> stack = new LinkedList<>();
            Deque<Integer> level = new LinkedList<>();
            stack.push(root);
            level.push(1);
            int max = 0;
            while (!stack.isEmpty()) {
                //stack中的元素和level中的元素同时出栈
                TreeNode node = stack.pop();
                int temp = level.pop();
                max = Math.max(temp, max);
                if (node.left != null) {
                    //同时入栈
                    stack.push(node.left);
                    level.push(temp + 1);
                }
                if (node.right != null) {
                    //同时入栈
                    stack.push(node.right);
                    level.push(temp + 1);
                }
            }
            return max;
        }
    }
}
