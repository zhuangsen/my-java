package com.zs.letcode.binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化与反序列化
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
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
 * 输入：root = [1,2]
 * 输出：[1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中结点数在范围 [0, 104] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xomr73/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/11 17:39
 */
public class Chapter13 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode rLeft = new TreeNode(4);
        TreeNode rRight = new TreeNode(5);
        root.left = left;
        root.right = right;
        right.left = rLeft;
        right.right = rRight;

        Codec codec = new Codec();
        String data = codec.serialize(root);
        System.out.println(data);
        TreeNode node = codec.deserialize(data);
        System.out.println(node);
        String str = ",,,#,,#,#,#,#,,,,";
        System.out.println(str.split(",").length);
    }

    private static class Codec {

        /**
         * 1，BFS解决
         */
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            // 边界判断，如果为空就返回一个字符串"#"
            if (root == null) {
                return "#";
            }
            //创建一个队列
            Queue<TreeNode> queue = new LinkedList<>();
            StringBuilder res = new StringBuilder();
            //把根节点加入到队列中
            queue.add(root);
            while (!queue.isEmpty()) {
                //节点出队
                TreeNode node = queue.poll();
                //如果节点为空，添加一个字符"#"作为空的节点
                if (node == null) {
                    res.append("#,");
                    continue;
                }
                //如果节点不为空，把当前节点的值加入到字符串中，
                //注意节点之间都是以逗号","分隔的，在下面把字符
                //串还原二叉树的时候也是以逗号","把字符串进行拆分
                res.append(node.val + ",");
                //左子节点加入到队列中（左子节点有可能为空）
                queue.add(node.left);
                //右子节点加入到队列中（右子节点有可能为空）
                queue.add(node.right);
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            //如果是"#"，就表示一个空的节点
            if (data == "#") {
                return null;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            //因为上面每个节点之间是以逗号","分隔的，所以这里
            //也要以逗号","来进行拆分
            String[] values = data.split(",");
            //上面使用的是BFS，所以第一个值就是根节点的值，这里创建根节点
            TreeNode root = new TreeNode(Integer.parseInt(values[0]));
            queue.add(root);
            for (int i = 1; i < values.length; i++) {
                //队列中节点出栈
                TreeNode parent = queue.poll();
                //因为在BFS中左右子节点是成对出现的，所以这里挨着的两个值一个是
                //左子节点的值一个是右子节点的值，当前值如果是"#"就表示这个子节点
                //是空的，如果不是"#"就表示不是空的
                if (!"#".equals(values[i])) {
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    parent.left = left;
                    queue.add(left);
                }
                //上面如果不为空就是左子节点的值，这里是右子节点的值，注意这里有个i++，
                if (!"#".equals(values[++i])) {
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    parent.right = right;
                    queue.add(right);
                }
            }
            return root;
        }

        /**
         * 2，DFS解决
         * 把树转化为字符串（使用DFS遍历，也是前序遍历，顺序是：根节点→左子树→右子树）
         */
        public String serialize1(TreeNode root) {
            //边界判断，如果为空就返回一个字符串"#"
            if (root == null) {
                return "#";
            }
            return root.val + "," + serialize1(root.left) + "," + serialize1(root.right);
        }

        //把字符串还原为二叉树
        public TreeNode deserialize1(String data) {
            //把字符串data以逗号","拆分，拆分之后存储到队列中
            Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
            return dfs(queue);
        }

        private TreeNode dfs(Queue<String> queue) {
            //出队
            String val = queue.poll();
            //如果是"#"表示空节点
            if ("#".equals(val)) {
                return null;
            }
            //否则创建当前节点
            TreeNode root = new TreeNode(Integer.valueOf(val));
            //分别创建左子树和右子树
            root.left = dfs(queue);
            root.right = dfs(queue);
            return root;
        }

    }
}
