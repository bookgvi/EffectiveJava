package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/majority-element/submissions/
 * */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {3, 2, 3};
        int major = majorityElement(nums);
    }

    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> numAndCounts = new HashMap<>();
        for (int elt : nums)
            numAndCounts.put(elt, numAndCounts.getOrDefault(elt, 0) + 1);
        int max = 0, major = 0;
        for (Map.Entry<Integer, Integer> entry : numAndCounts.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                major = entry.getKey();
            }
        }
        return major;
    }

    public static int majorityElementII(int[] nums) {
        int pos = nums.length / 2;
        sort(nums);
        return nums[pos];
    }

    private static void sort(int[] nums) {
        int len = nums.length, b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : nums)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((nums[i] ^ Integer.MIN_VALUE) >>> (p * b)) & (1 << b) - 1]] = nums[i];
            System.arraycopy(t, 0, nums, 0, len);
        }
    }
}
