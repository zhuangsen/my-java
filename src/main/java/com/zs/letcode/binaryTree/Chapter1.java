package com.zs.letcode.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
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
 * 输出：[1,2]
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
 *  
 * <p>
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xeywh5/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/7 23:09
 */
public class Chapter1 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，递归解法
         *
         * @param root
         * @return
         */
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            preOrder(root, res);
            return res;
        }

        private void preOrder(TreeNode root, List<Integer> res) {
            if (root == null) {
                return;
            }
            //先打印当前节点，然后打印左子树，最后再打印右子树
            res.add(root.val);
            preOrder(root.left, res);
            preOrder(root.right, res);
        }

        /**
         * 2，非递归解法
         */
        public List<Integer> preorderTraversal1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            stack.push(root);//压栈
            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                res.add(node.val);
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return res;
        }

        /**
         * 3，Morris前序遍历方式
         */
        public List<Integer> preorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            //首先把根节点赋值给cur
            TreeNode cur = root;
            //如果cur不为空就继续遍历
            while (cur != null) {
                if (cur.left == null) {
                    //如果当前节点cur的左子节点为空，就访问当前节点cur，
                    //接着让当前节点cur指向他的右子节点
                    res.add(cur.val);
                    cur = cur.right;
                } else {
                    TreeNode pre = cur.left;
                    //查找pre节点，注意这里有个判断就是pre的右子节点不能等于cur
                    while (pre.left != null && pre.right != cur) {
                        pre = pre.right;
                    }
                    //如果pre节点的右指针指向空，我们就让他指向当前节点cur，
                    //然后打印当前节点cur的值，最后再让当前节点cur指向他的左子节点
                    if (pre.right == null) {
                        pre.right = cur;
                        res.add(cur.val);
                        cur = cur.left;
                    } else {
                        //如果pre节点的右指针不为空，那么他肯定是指向cur的,
                        //表示当前节点cur和他的的左子节点都遍历完了，直接
                        //让他指向他的右子节点即可。
                        pre.right = null;
                        cur = cur.right;
                    }
                }
            }
            return res;
        }
    }
}
