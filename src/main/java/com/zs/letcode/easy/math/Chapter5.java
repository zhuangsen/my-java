package com.zs.letcode.easy.math;

/**
 * 位1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的示例 3中，输入表示有符号整数 -3。
 * <p>
 * 示例 1：
 * <p>
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011中，共有三位为 '1'。
 * 示例 2：
 * <p>
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000中，共有一位为 '1'。
 * 示例 3：
 * <p>
 * 输入：11111111111111111111111111111101
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 输入必须是长度为 32 的 二进制串 。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果多次调用这个函数，你将如何优化你的算法？
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn1m0i/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/24 22:10
 */
public class Chapter5 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hammingWeight3(00000000000000000000000000001011));
//        System.out.println(solution.hammingWeight1(11111111111111111111111111111101));

    }

    private static class Solution {
        // you need to treat n as an unsigned value

        /**
         * 一，通过移动数字计算
         *
         * @param n
         * @return
         */
        public int hammingWeight(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if (((n >>> i) & 1) == 1) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 上面的分析中我们看到，如果一个数往右移了几步之后结果为0了，就没必要在计算了，所以代码我们还可以在优化一点
         *
         * @param n
         * @return
         */
        public int hammingWeight1(int n) {
            int count = 0;
            while (n != 0) {
                count += n & 1;
                n = n >>> 1;
            }
            return count;
        }

        /**
         * 二，通过移动1来计算
         */
        public int hammingWeight2(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) != 0) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 当然我们还可以通过运算的结果是否是1来判断也是可以的，我们只需要把往左移的1和n运算完之后再往右移即可，我们来看下代码
         */
        public int hammingWeight3(int n) {
            int count = 0;
            for (int i = 0; i < 32; i++) {
                if ((n & (1 << i)) >>> n == 1) {
                    count++;
                }
            }
            return count;
        }

        /**
         * 三，不通过移位计算
         * <p>
         * 前面两种方式要么是移动原数字，要么是移动1，这次我们不移动任何数字来计算。
         * 在位运算中有这样一个操作，就是n&(n-1)可以把n最右边的1给消掉。举个例子，当n=12的时候，我们来画个图看一下
         */
        public int hammingWeight4(int n) {
            int count = 0;
            while (n != 0) {
                n &= n - 1;
                count++;
            }
            return count;
        }

        /**
         * 我们还可以把它给为递归的写法，直接一行代码搞定
         */
        public int hammingWeight5(int n) {
            return n == 0 ? 0 : 1 + hammingWeight5(n & (n - 1));
        }

        /**
         * 四，查表
         * <p>
         * 我们还可以通过查表来计算，我们先要把0到15转化为二进制，记录下每个数字包含1的个数再构成一张表，
         * 然后再把数字n每4位进行一次计算，图就不画了，代码中有注释，我们来看下代码
         */
        public int hammingWeight6(int n) {
            //table是0到15转化为二进制时1的个数
            int table[] = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4};
            int count = 0;
            while (n != 0) {
                //通过每4位计算一次，求出包含1的个数
                count += table[n & 0xf];
                n >>>= 4;
            }
            return count;
        }

        /**
         * 1，每两位存储
         * <p>
         * 我们先来看一下，两位二进制位总共有4种表示，分别是00，01，10，11。
         * 他们中1的个数分别是0，1，1，2，最大值是2，而两位二进制表示的最大数是3，所以足够存储了，我们就以-3为例来画个图分析一下
         */
        public int hammingWeight7(int n) {
            n = ((n & 0xaaaaaaaa) >>> 1) + (n & 0x55555555);
            n = ((n & 0xcccccccc) >>> 2) + (n & 0x33333333);
            n = (((n & 0xf0f0f0f0) >>> 4) + (n & 0x0f0f0f0f));
            n = n + (n >>> 8);
            n = n + (n >>> 16);
            return n & 63;
        }
    }
}
