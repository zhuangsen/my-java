package com.zs.letcode.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
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
 * 
 * <p>
 * 示例：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。
 * <p>
 * 提示：
 * <p>
 * 树中的节点数小于 6000
 * -100<= node.val <= 100
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xorvud/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/10 13:58
 */
public class Chapter11 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，BFS解决
         *
         * @param root
         * @return
         */
        public TreeNode connect(TreeNode root) {
            if (root == null) {
                return root;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                //每一层的数量
                int levelCount = queue.size();
                //前一个节点
                TreeNode pre = null;
                for (int i = 0; i < levelCount; i++) {
                    //出队列
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
         * 其实可以不需要队列，每一行都可以看成一个链表比如第一行就是只有一个节点的链表，
         * 第二行是只有两个节点的链表（假如根节点的左右两个子节点都不为空）……
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
                //当前层的节点，然后把下一层的节点串起来
                TreeNode dummy = new TreeNode(0);
                //pre表示访下一层节点的前一个节点
                TreeNode pre = dummy;
                //然后开始遍历当前层的链表
                while (cur != null) {
                    if (cur.left != null) {
                        //如果当前节点的左子节点不为空，就让pre节点
                        //的next指向他，也就是把它串起来
                        pre.next = cur.left;
                        //然后再更新pre
                        pre = pre.next;
                    }
                    //同理参照左子树
                    if (cur.right != null) {
                        pre.next = cur.right;
                        pre = pre.next;
                    }
                    //继续访问这一行的下一个节点
                    cur = cur.next;
                }
                //把下一层串联成一个链表之后，让他赋值给cur，
                //后续继续循环，直到cur为空为止
                cur = dummy.next;
            }
            return root;
        }
    }
}
