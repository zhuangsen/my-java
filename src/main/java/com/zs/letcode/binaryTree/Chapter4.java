package com.zs.letcode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 *  
 * <p>
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层序遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xefh1i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/8 22:44
 */
public class Chapter4 {
    public static void main(String[] args) {

    }


    private static class Solution {
        /**
         * 1，BFS解决 广度优先搜索
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<>();
            }
            //队列
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            //跟节点入队
            queue.add(root);
            //如果队列不为空就继续循环
            while (!queue.isEmpty()) {
                //BFS打印，levelNum表示的是每层的结点数
                int levelNum = queue.size();
                //subList存储的是每层的结点值
                List<Integer> subList = new ArrayList<>();
                for (int i = 0; i < levelNum; i++) {
                    //出队
                    TreeNode node = queue.poll();
                    subList.add(node.val);
                    //左右子节点如果不为空就加入到队列中
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                //把每层的结点值存储在res中，
                res.add(subList);
            }
            return res;
        }

        /**
         * 2，DFS解决 深度优先搜索算法
         */
        public List<List<Integer>> levelOrder1(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            levelDFS(res, root, 0);
            return res;
        }

        private void levelDFS(List<List<Integer>> res, TreeNode root, int level) {
            //边界条件判断
            if (root == null) {
                return;
            }
            //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
            //要先把下一层的list初始化，防止下面add的时候出现空指针异常
            if (level >= res.size()) {
                res.add(new ArrayList<>());
            }
            //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
            res.get(level).add(root.val);
            //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
            levelDFS(res, root.left, level + 1);
            levelDFS(res, root.right, level + 1);
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
