package com.zs.letcode.easy.search;

/**
 * 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * <p>
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * <p>
 * 你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * <p>
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * <p>
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5)-> true
 * 调用 isBadVersion(4)-> true
 * <p>
 * 所以，4 是第一个错误的版本。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnto1s/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * @author madison
 * @description
 * @date 2021/4/22 01:52
 */
public class Chapter1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstBadVersion(6));
    }

    private static class Solution extends VersionControl {
        /**
         * 方法一：线性扫描 [超出时间限制]
         * @param n
         * @return
         */
        public int firstBadVersion(int n) {
            for (int i = 1; i < n; i++) {
                if (isBadVersion(i)) {
                    return i;
                }
            }
            return n;
        }

        /**
         * 方法二：二分查找 [通过]
         * @param n
         * @return
         */
        public int firstBadVersion1(int n) {
            int start = 1, end = n;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (!isBadVersion(mid)) {
                    start = mid + 1;
                } else {
                    end = mid;
                }
            }
            return start;
        }
    }

    private static class VersionControl {
        boolean isBadVersion(int version) {
            return false;
        }
    }
}
