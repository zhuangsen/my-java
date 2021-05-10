package com.zs.letcode.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有next 指针都被设置为 NULL。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数量少于4096
 * -1000 <= node.val <= 1000
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoo0ts/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/10 13:14
 */
public class Chapter10 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，BFS解决
         */
        public TreeNode connect(TreeNode root) {
            if (root == null) {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //每一层的数量
                int levelCount = queue.size();
                //前一个节点
                TreeNode pre = null;
                for (int i = 0; i < levelCount; i++) {
                    //出队
                    TreeNode node = queue.poll();
                    //如果pre为空就表示node节点是这一行的第一个，
                    //没有前一个节点指向他，否则就让前一个节点指向他
                    if (pre != null) {
                        pre.next = node;
                    }
                    //然后再让当前节点成为前一个节点
                    pre = node;
                    //左右子节点如果不为空就入队
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return root;
        }

        /**
         * 上面运行效率并不是很高，这是因为我们把节点不同的入队然后再不停的出队，
         * 其实可以不需要队列，每一行都可以看成一个链表比如第一行就是只有一个节点的链表，第二行是只有两个节点的链表
         */
        public TreeNode connect1(TreeNode root) {
            if (root == null) {
                return null;
            }
            //cur我们可以把它看做是每一层的链表
            TreeNode cur = root;
            while (cur != null) {
                //遍历当前层的时候，为了方便操作在下一
                //层前面添加一个哑结点（注意这里是访问
                //当前层的节点，然后把下一层的节点串起来）
                TreeNode dummy = new TreeNode(0);
                //pre表示下一层节点的前一个节点
                TreeNode pre = dummy;

                //然后开始遍历当前层的链表
                //因为是完美二叉树，如果有左子节点就一定有右子节点

                while (cur != null && cur.left != null) {
                    //让pre节点的next指向当前节点的左子节点，也就是把它串起来
                    pre.next = cur.left;
                    //然后再更新pre
                    pre = pre.next;

                    //pre节点的next指向当前节点的右子节点，
                    pre.next = cur.right;
                    pre = pre.next;

                    //继续访问这一行的下一个节点
                    cur = cur.next;
                }
                //把下一层串联成一个链表之后，让他赋值给cur，
                //后续继续循环，直到cur为空为止
                cur = dummy.next;
            }
            return root;
        }

        /**
         * 2，其他方式解决
         */
        public TreeNode connect2(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode pre = null;
            TreeNode cur;
            while (pre.left != null) {
                //遍历当前这一层的结点，然后把他们的下一层连接起来
                cur = pre;
                //cur不为空，就表示这一层还没遍历完，就继续循环
                while (cur != null) {
                    cur.left.next = cur.right;
                    //如果cur.next不为空，就表示还没遍历到这一层
                    //最后的那个节点的右子节点，就让前一个结点的右
                    //子节点指向下一个节点的左子节点
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                    //然后继续连接下一个节点的 子节点
                    cur = cur.next;
                }
                //继续下一层
                pre = pre.next;
            }
            return root;
        }

        /**
         * 3，递归方式
         */
        public TreeNode connect3(TreeNode root) {
            dfs(root, null);
            return root;
        }

        private void dfs(TreeNode cur, TreeNode next) {
            if (cur == null) {
                return;
            }
            cur.next = next;
            dfs(cur.left, cur.right);
            dfs(cur.right, cur.next == null ? null : cur.next.left);
        }
    }
}
