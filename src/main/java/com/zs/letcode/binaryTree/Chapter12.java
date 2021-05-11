package com.zs.letcode.binaryTree;


import java.util.*;

/**
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 * 示例 3：
 * <p>
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xopaih/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/11 16:50
 */
public class Chapter12 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * BFS
         */
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 记录遍历到的每个节点的父节点。
            Map<TreeNode, TreeNode> parent = new HashMap<>();
            Queue<TreeNode> queue = new LinkedList<>();
            //根节点没有父节点，所以为空
            parent.put(root, null);
            queue.add(root);
            //直到两个节点都找到为止。
            while (!parent.containsKey(p) || !parent.containsKey(q)) {
                //队列是一边进一边出，这里poll方法是出队,
                TreeNode node = queue.poll();
                if (node.left != null) {
                    //左子节点不为空，记录下他的父节点
                    parent.put(node.left, node);
                    //左子节点不为空，把它加入到队列中
                    queue.add(node.left);
                }
                //右节点同上
                if (node.right != null) {
                    parent.put(node.right, node);
                    queue.add(node.right);
                }
            }
            Set<TreeNode> ancestors = new HashSet<>();
            //记录下p和他的祖先节点，从p节点开始一直到根节点。
            while (p != null) {
                ancestors.add(p);
                p = parent.get(p);
            }
            //查看p和他的祖先节点是否包含q节点，如果不包含再看是否包含q的父节点……
            while (!ancestors.contains(q)) {
                q = parent.get(q);
            }
            return q;
        }

        /**
         * 2，递归写法
         */
        public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
            return dfs(root, p, q);
        }

        public TreeNode dfs(TreeNode cur, TreeNode p, TreeNode q) {
            if (cur == null || cur == p || cur == q) {
                return cur;
            }
            TreeNode left = dfs(cur.left, p, q);
            TreeNode right = dfs(cur.right, p, q);
            //如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
            if (left == null) {
                return right;
            }
            //同上
            if (right == null) {
                return left;
            }
            //如果left和right都不为空，说明这两个节点一个在cur的左子树上一个在cur的右子树上，
            //我们只需要返回cur结点即可。
            return cur;
        }
    }
}
