package Leetcode.Simple;

/*
 * Given an array of integers nums, you start with an initial positive value startValue.
 * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
 * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
 */
public class MinimumValuetoGetPositiveStepbyStepSum {
    public static int minStartValue(int[] nums) {
        int len = nums.length, res = 0, sum = 0;
        for (int i = 0; i < len; i += 1) {
            sum += nums[i];
            res = Math.min(res, sum);
        }
        return 1 - res;
    }

    private static int[] buildSegmentTree (int[] arr) {
        int len = arr.length;
        int[] st = new int[len << 1];
        System.arraycopy(arr, 0, st, len, len);
        for (int i = len - 1; i > 0; i -= 1)
            st[i] = st[i << 1] + st[i << 1 | 1];
        return st;
    }

    private static int query(int l, int r, int[] arr) {
        int len = 5, res = 0; r += 1;
        for (l += len, r += len; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) res += arr[l++];
            if ((r & 1) == 1) res += arr[--r];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-3, 2, -3, 4, 2};
        int[] st = buildSegmentTree(nums);
        int d = query(0, nums.length - 1, st);
        int res = minStartValue(nums);
    }
}
