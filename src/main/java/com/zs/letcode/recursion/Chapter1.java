package com.zs.letcode.recursion;

/**
 * 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * <p>
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * 示例 2：
 * <p>
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/recursion/490ye/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/5/12 13:01
 */
public class Chapter1 {
    public static void main(String[] args) {
        char[] str = new char[]{'h', 'e', 'e', 'e', 'o'};
        Solution solution = new Solution();
        solution.reverseString(str);
    }

    private static class Solution {
        /**
         * 1.双指针
         */
        public void reverseString(char[] s) {
            int i = 0, j = s.length - 1;

            while (i < j) {
                swap(s, i, j);
                i++;
                j--;
            }
        }

        /**
         * 2，递归方式解决
         */
        public void reverseString1(char[] s) {
            if (s == null || s.length == 0) {
                return;
            }
            reverseStringHelper(s, 0, s.length - 1);
        }

        private void reverseStringHelper(char[] s, int left, int right) {
            if (left >= right) {
                return;
            }
            swap(s, left, right);
            reverseStringHelper(s, ++left, --right);

//            我们还可以换种写法，先递归在交换
//            reverseStringHelper(s, left+1, right-1);
//            swap(s, left, right);
        }

        private void swap(char[] array, int i, int j) {
            //第1种交换方式
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;

            //第2种交换方式
//        array[i] = (char) (array[i] + array[j]);
//        array[j] = (char) (array[i] - array[j]);
//        array[i] = (char) (array[i] - array[j]);

            //第3种交换方式
//        array[i] = (char) (array[i] - array[j]);
//        array[j] = (char) (array[i] + array[j]);
//        array[i] = (char) (array[j] - array[i]);

            //第4种交换方式
//        array[i] ^= array[j];
//        array[j] ^= array[i];
//        array[i] ^= array[j];
        }
    }
}
