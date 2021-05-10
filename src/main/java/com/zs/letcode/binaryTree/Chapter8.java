package com.zs.letcode.binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoei3r/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/9 19:17
 */
public class Chapter8 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，递归方式
         *
         * @param preorder
         * @param inorder
         * @return
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            //把前序遍历的值和中序遍历的值放到list中
            List<Integer> preorderList = new ArrayList<>();
            List<Integer> inorderList = new ArrayList<>();
            for (int i = 0; i < preorder.length; i++) {
                preorderList.add(preorder[i]);
                inorderList.add(inorder[i]);
            }
            return helper(preorderList, inorderList);
        }

        private TreeNode helper(List<Integer> preorderList, List<Integer> inorderList) {
            if (inorderList.size() == 0) {
                return null;
            }
            //前序遍历的第一个值就是根节点
            int rootVal = preorderList.remove(0);
            //创建跟结点
            TreeNode root = new TreeNode(rootVal);
            //查看根节点在中序遍历中的位置，然后再把中序遍历的数组劈两半，前面部分是
            //根节点左子树的所有值，后面部分是根节点右子树的所有值
            int mid = inorderList.indexOf(rootVal);
            //[0，mid)是左子树的所有值，inorderList.subList(0, mid)表示截取inorderList
            //的值，截取的范围是[0，mid)，包含0不包含mid。
            root.left = helper(preorderList, inorderList.subList(0, mid));
            //[mid+1，inorderList.size())是右子树的所有值，
            // inorderList.subList(mid + 1, inorderList.size())表示截取inorderList
            //的值，截取的范围是[mid+1，inorderList.size())，包含mid+1不包含inorderList.size()。
            root.right = helper(preorderList, inorderList.subList(mid + 1, inorderList.size()));
            return root;
        }

        /**
         * 2，使用指针解决
         */
        public TreeNode buildTree1(int[] preorder, int[] inorder) {
            return helper1(0, 0, inorder.length - 1, preorder, inorder);
        }

        private TreeNode helper1(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
            if (preStart > preorder.length - 1 || inStart > inEnd) {
                return null;
            }
            //创建结点
            TreeNode root = new TreeNode(preorder[preStart]);
            int index = 0;
            //找到当前节点root在中序遍历中的位置，然后再把数组分两半
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == root.val) {
                    index = i;
                    break;
                }
            }
            root.left = helper1(preStart + 1, inStart, index - 1, preorder, inorder);
            root.left = helper1(preStart + index - inStart + 1, index + 1, inEnd, preorder, inorder);
            return root;
        }

        /**
         * 3，使用栈解决
         */
        public TreeNode buildTree2(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            //前序的第一个其实就是根节点
            TreeNode root = new TreeNode(preorder[0]);
            TreeNode cur = root;
            for (int i = 1, j = 0; i < preorder.length; i++) {
                //第一种情况
                if (cur.val != inorder[j]) {
                    cur.left = new TreeNode(preorder[i]);
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    //第二种情况
                    j++;
                    //找到合适的cur，然后确定他的右节点
                    while (!stack.isEmpty() && stack.peek().val == inorder[j]) {
                        cur = stack.pop();
                        j++;
                    }
                    //给cur添加右节点
                    cur = cur.right = new TreeNode(preorder[i]);
                }
            }
            return root;
        }

        /**
         * 4，递归的另一种解法
         */
        private int in = 0;
        private int pre = 0;

        public TreeNode buildTree3(int[] preorder, int[] inorder) {
            return build(preorder, inorder, Integer.MIN_VALUE);
        }

        private TreeNode build(int[] preorder, int[] inorder, int stop) {
            if (pre >= preorder.length) {
                return null;
            }
            if (inorder[in] == stop) {
                in++;
                return null;
            }
            TreeNode node = new TreeNode(preorder[pre++]);
            node.left = build(preorder, inorder, node.val);
            node.right = build(preorder, inorder, stop);
            return node;
        }
    }
}
