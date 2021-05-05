package com.zs.letcode.stack_queue;

import java.util.Stack;

/**
 * 每日温度
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用0 来代替。
 * <p>
 * 例如，给定一个列表temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是[1, 30000]。每个气温的值的均为华氏度，都是在[30, 100]范围内的整数。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/queue-stack/genw3/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/4 12:57
 */
public class Chapter7 {
    public static void main(String[] args) {

    }

    private static class Solution {
        /**
         * 1，暴力求解
         *
         * @param T
         * @return
         */
        public int[] dailyTemperatures(int[] T) {
            int length = T.length;
            int[] res = new int[length];
            for (int i = 0; i < length; i++) {
                for (int j = i + 1; j < length; j++) {
                    if (T[j] > T[i]) {
                        res[i] = j - i;
                        break;
                    }
                }
            }
            return res;
        }

        /**
         * 2，使用栈解决
         */
        public int[] dailyTemperatures1(int[] T) {
            Stack<Integer> stack = new Stack<>();
            int[] res = new int[T.length];
            for (int i = 0; i < T.length; i++) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int idx = stack.pop();
                    res[idx] = i - idx;
                }
                stack.push(i);
            }
            return res;
        }

        /**
         * 3，我们还可以把栈改为数组的形式
         */
        public int[] dailyTemperatures2(int[] T) {
            int[] stack = new int[T.length];
            int top = -1;
            int[] res = new int[T.length];
            for (int i = 0; i < T.length; i++) {
                while (top >= 0 && T[i] > T[stack[top]]) {
                    int idx = stack[top--];
                    res[idx] = i - idx;
                }
                stack[++top] = i;
            }
            return res;
        }

        /**
         * 4，这题我们还可以参照第《84. 柱状图中最大的矩形》https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
         */
        public int[] dailyTemperatures3(int[] T) {
            int length = T.length;
            Stack<Integer> stack = new Stack<>();
            int[] res = new int[length];
            for (int i = 0; i < length; i++) {
                int h = T[i];
                if (stack.isEmpty() || h <= T[stack.peek()]) {
                    stack.push(i);
                } else {
                    int top = stack.pop();
                    res[top] = i - top;
                    i--;
                }
            }
            return res;
        }

        /**
         * 5，最后一种解法，这种更厉害，从后面开始查找，效率更高，击败了100%的用户，代码中有注释，大家自己看
         */
        public int[] dailyTemperatures4(int[] T) {
            int[] res = new int[T.length];
            //从后面开始查找
            for (int i = res.length; i >= 0; i--) {
                int j = i + 1;
                while (j < res.length) {
                    if (T[j] > T[i]) {
                        //如果找到就停止while循环
                        res[i] = j - i;
                        break;
                    } else if (res[j] == 0) {
                        //如果没找到，并且res[j]==0。说明第j个元素后面没有
                        //比第j个元素大的值，因为这一步是第i个元素大于第j个元素的值，
                        //那么很明显这后面就更没有大于第i个元素的值。直接终止while循环。
                        break;
                    } else {
                        //如果没找到，并且res[j]！=0说明第j个元素后面有比第j个元素大的值，
                        //然后我们让j往后挪res[j]个单位，找到那个值，再和第i个元素比较
                        j += res[j];
                    }
                }
            }
            return res;
        }
    }
}
