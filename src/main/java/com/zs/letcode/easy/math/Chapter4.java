package com.zs.letcode.easy.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 * 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做  XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1到 3999 的范围内。
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入:"III"
 * 输出: 3
 * 示例2:
 * <p>
 * 输入:"IV"
 * 输出: 4
 * 示例3:
 * <p>
 * 输入:"IX"
 * 输出: 9
 * 示例4:
 * <p>
 * 输入:"LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例5:
 * <p>
 * 输入:"MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn4n7c/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/23 22:47
 */
public class Chapter4 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.romanToInt2("LVIII"));
    }

    private static class Solution {
        /**
         * 方法一
         *
         * @param s
         * @return
         */
        public int romanToInt(String s) {
            Map<String, Integer> map = new HashMap<>();
            //所有可能的都列出来
            map.put("I", 1);
            map.put("IV", 4);
            map.put("V", 5);
            map.put("IX", 9);
            map.put("X", 10);
            map.put("XL", 40);
            map.put("L", 50);
            map.put("XC", 90);
            map.put("C", 100);
            map.put("CD", 400);
            map.put("D", 500);
            map.put("CM", 900);
            map.put("M", 1000);

            int res = 0;
            for (int i = 0; i < s.length(); ) {
                //先截取两个字符，如果这两个字符存在于map中，就表示他们是一个整体。否则就截取一个
                if (i + 1 < s.length() && map.containsKey(s.substring(i, i + 2))) {
                    res += map.get(s.substring(i, i + 2));
                    i += 2;
                } else {
                    res += map.get(s.substring(i, i + 1));
                    i++;
                }
            }
            return res;
        }

        /**
         * 方法二
         *
         * @param s
         * @return
         */
        public int romanToInt1(String s) {
            int sum = 0;
            //前一个数字表示的值
            int preNum = getValue(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                int num = getValue(s.charAt(i));
                //如果前一个数字比现在数字小，说明
                //前一个字符和现在字符组合成一个有效数字，
                if (preNum < num) {
                    sum -= preNum;
                } else {
                    //如果前一个数字不比现在数字小，说明
                    //每个字符都是一个有效的数字
                    sum += preNum;
                }
                preNum = num;
            }
            sum += preNum;
            return sum;
        }

        /**
         * 方法三
         *
         * @param s
         * @return
         */
        public int romanToInt2(String s) {
            int sum = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (sum > 4 * getValue(s.charAt(i))) {
                    sum -= getValue(s.charAt(i));
                } else {
                    sum += getValue(s.charAt(i));
                }
            }
            return sum;
        }

        private int getValue(char ch) {
            switch (ch) {
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
                default:
                    return 0;
            }
        }
    }
}
