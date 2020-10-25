package com.zs.letcode.easy.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x21ib6/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2020/9/17 12:03 上午
 */
public class Chapter5 {

    public static void main(String[] args) {
        System.out.println(singleNumber3(new int[]{4, 1, 2, 1, 2}));
    }

    public static int singleNumber1(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    public static int singleNumber2(int[] nums) {
        Arrays.sort(nums);
        for (int i : nums) {
            System.out.print(i + "\t");
        }
        System.out.println();
        if (nums.length == 1) {
            return nums[0];
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (i == 1) {
                if (nums[i] != nums[i - 1]) {
                    return nums[i - 1];
                }
            } else if (i == nums.length - 2) {
                if (nums[i] != nums[i + 1]) {
                    return nums[i + 1];
                }
            } else {
                if (nums[i] != nums[i + 1] && nums[i] != nums[i - 1]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    public static int singleNumber3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
//        Arrays.sort(nums);
        for (int value : nums) {
            int count = map.getOrDefault(value, 0);
            map.put(value, count + 1);
        }
        for (int i : map.keySet()) {
            int count = map.get(i);
            if (count == 1) {
                return i;
            }
        }
        return -1;
    }

}
