package com.zs.letcode.stack_queue;

import java.util.*;

/**
 * 打开转盘锁
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * <p>
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * <p>
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * <p>
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * <p>
 * 提示：
 * <p>
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/kj48j/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/3 09:36
 */
public class Chapter3 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 方法一：广度优先搜索(BFS)
         *
         * @param deadends
         * @param target
         * @return
         */
        public int openLock(String[] deadends, String target) {
            Set<String> dead = new HashSet<>();
            for (String d : deadends) {
                dead.add(d);
            }

            //创建队列
            Queue<String> queue = new LinkedList<>();
            //开始遍历的字符串是"0000"，相当于根节点
            queue.offer("0000");
            queue.offer(null);

            //记录访问过的节点
            Set<String> seen = new HashSet<>();
            seen.add("0000");

            //树的层数
            int depth = 0;
            while (!queue.isEmpty()) {
                //每个节点的值
                String node = queue.poll();
                if (node == null) {
                    //当前层访问完了，到下一层，层数要加1
                    depth++;
                    if (queue.peek() != null) {
                        queue.offer(null);
                    }
                } else if (node.equals(target)) {//如果找到直接返回
                    return depth;
                } else if (!dead.contains(node)) {
                    //对于每个节点中的4个数字分别进行加1和减1，相当于创建8个子节点，这八个子节点
                    //可以类比二叉树的左右子节点
                    for (int i = 0; i < 4; i++) {
                        for (int d = -1; d <= 1; d += 2) {
                            int y = ((node.charAt(i) - '0') + d + 10) % 10;
                            String nei = node.substring(0, i) + ("" + y) + node.substring(i + 1);
                            if (!seen.contains(nei)) {//不能包含死亡数字也不能包含访问过的字符串
                                seen.add(nei);
                                queue.offer(nei);
                            }
                        }
                    }
                }
            }
            return -1;
        }

        /**
         * 方法二：广度优先搜索(BFS) 另一种
         *
         * @param deadends
         * @param target
         * @return
         */
        public int openLock1(String[] deadends, String target) {
            Set<String> set = new HashSet<>(Arrays.asList(deadends));
            //开始遍历的字符串是"0000"，相当于根节点
            String startStr = "0000";
            if (set.contains(startStr)) {
                return -1;
            }
            //创建队列
            Queue<String> queue = new LinkedList<>();
            //记录访问过的节点
            Set<String> visited = new HashSet<>();
            queue.offer(startStr);
            visited.add(startStr);

            //树的层数
            int level = 0;
            while (!queue.isEmpty()) {
                //每层的子节点个数
                int size = queue.size();
                while (size-- > 0) {
                    //每个节点的值
                    String node = queue.poll();
                    //对于每个节点中的4个数字分别进行加1和减1，相当于创建8个子节点，这八个子节点
                    //可以类比二叉树的左右子节点
                    for (int i = 0; i < 4; i++) {
                        char ch = node.charAt(i);
                        //strAdd表示加1的结果，strSub表示减1的结果
                        String strAdd = node.substring(0, i) + (ch == '9' ? 0 : ch - '0' + 1) + node.substring(i + 1);
                        String strSub = node.substring(0, i) + (ch == '0' ? 9 : ch - '0' - 1) + node.substring(i + 1);
                        //如果找到直接返回
                        if (node.equals(target)) {
                            return level;
                        }
                        //不能包含死亡数字也不能包含访问过的字符串
                        if (!visited.contains(strAdd) && !set.contains(strAdd)) {
                            queue.offer(strAdd);
                            visited.add(strAdd);
                        }
                        if (!visited.contains(strSub) && !set.contains(strSub)) {
                            queue.offer(strSub);
                            queue.add(strSub);
                        }
                    }
                }
            }
            return -1;
        }
    }
}
