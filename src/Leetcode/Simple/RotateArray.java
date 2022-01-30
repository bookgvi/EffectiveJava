package Leetcode.Simple;

import java.util.Arrays;

/*
* https://leetcode.com/problems/rotate-array/submissions/
* */
public class RotateArray {
    public static void rotate(int[] nums, int k) {
        int len = nums.length, j = 0;
        int[] tmpArr = Arrays.copyOf(nums, len);
        if (k < 1) return;
        k = len - (k % len);
        while (j < len) {
            k %= len;
            nums[j++] = tmpArr[k++];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int k = 5;
        rotate(nums, k);
    }
}
