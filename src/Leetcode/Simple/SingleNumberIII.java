package Leetcode.Simple;

/*
 * https://leetcode.com/problems/single-number-iii/
 *
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 * */

import java.util.*;

public class SingleNumberIII {
    public static int[] singleNumber(int[] nums) {
        int len = nums.length, k = 0;
        int[] res = new int[2];
        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 0; i < len; i += 1) {
           dp.put(nums[i], dp.getOrDefault(nums[i], 0) + 1);
        }

        for (int i = 0; i < len; i += 1) {
            if (dp.get(nums[i]) != null && dp.get(nums[i]) == 1) {
                res[k] = nums[i];
                if (k == 1) return res;
                k += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2, 14};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }
}
