package com.zs.letcode.easy.tree;

import java.util.*;

/**
 * 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
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
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnldjj/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/21 17:18
 */
public class Chapter4 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        TreeNode leftL = new TreeNode(3);
        TreeNode leftR = new TreeNode(4);
        left.left = leftL;
        left.right = leftR;
//        TreeNode root = new TreeNode(3, left, right);

        TreeNode rightL = new TreeNode(4);
        TreeNode rightR = new TreeNode(3);
        right.left = rightL;
        right.right = rightR;


        Solution s = new Solution();
        System.out.println(s.levelOrder(root));

        Solution1 s1 = new Solution1();
        System.out.println(Arrays.toString(s1.levelOrder(root)));

        TreeOperation.show(root);
    }

    static class Solution {
        /**
         * DFS解决
         * 这题让一层一层的打印，其实就是BFS，但使用DFS也是可以解决的，看一下
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            levelHelper(res, root, 0);
            return res;
        }

        public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
            //边界条件判断
            if (root == null) {
                return;
            }
            //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
            //要先把下一层的list初始化，防止下面add的时候出现空指针异常
            if (level >= list.size()) {
                list.add(new ArrayList<>());
            }
            //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
            list.get(level).add(root.val);
            //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
            levelHelper(list, root.left, level + 1);
            levelHelper(list, root.right, level + 1);
        }

        /**
         * BFS解决
         * 这题和剑指 Offer 32 - I. 从上到下打印二叉树其实是一样的，只不过上一题返回的是数组，
         * 这一题返回的是list。返回数组，我们还要初始化数组，但不知道数组的大小，所以一般是先储存在list中再转化为数组，返回list就比较简单了。
         *
         * @param root
         * @return
         */
        public List<List<Integer>> levelOrder1(TreeNode root) {
            //边界条件判断
            if (root == null) {
                return new ArrayList<>();
            }
            //队列
            Queue<TreeNode> queue = new LinkedList<>();
            List<List<Integer>> res = new ArrayList<>();
            //根节点入队
            queue.add(root);
            //如果队列不为空就继续循环
            while (!queue.isEmpty()) {
                //BFS打印，levelNum表示的是每层的结点数
                int levelNum = queue.size();
                //subList存储的是每层的结点值
                List<Integer> subList = new ArrayList<>();
                for (int i = 0; i < levelNum; i++) {
                    //出列
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
                //把每层的结点值存储在res中
                res.add(subList);
            }

            return res;
        }

    }

    /**
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
     * <p>
     * 例如:
     * 给定二叉树:[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回：
     * <p>
     * [3,9,20,15,7]
     * <p>
     * 提示：
     * <p>
     * 节点总数 <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Solution1 {
        /**
         * 1，BFS解决
         *
         * @param root
         * @return
         */
        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            //队列
            Queue<TreeNode> queue = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            //根结点入队
            queue.add(root);
            while (!queue.isEmpty()) {
                //出队
                TreeNode node = queue.poll();
                //把根结点放到list中
                list.add(node.val);
                //左右子节点如果不为空就加入到队列中
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            System.out.println(list);
            //把list转化为数组
            int[] res = new int[list.size()];
            //list.toArray(res);
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }

        /**
         * 2，递归方式解决
         * @param root
         * @return
         */
        public int[] levelOrder1(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            levelHelper(list, root, 0);
            List<Integer> tempList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                tempList.addAll(list.get(i));
            }
            //把list转化为数组
            int[] res = new int[tempList.size()];
            for (int i = 0; i < tempList.size(); i++) {
                res[i] = tempList.get(i);
            }
            return res;
        }

        public void levelHelper(List<List<Integer>> list, TreeNode root, int height) {
            if (root == null) {
                return;
            }
            if (height >= list.size()) {
                list.add(new ArrayList<>());
            }
            list.get(height).add(root.val);
            levelHelper(list, root.left, height + 1);
            levelHelper(list, root.right, height + 1);
        }
    }
}
