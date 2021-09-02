package Leetcode.Simple;

import java.util.Arrays;

public class TwoSum {
    static int[] value = new int[2];

    public static void main(String[] args) {
        int[] nums = new int[]{-3, 4, 3, 90};
        int target = 0;
        System.out.printf("Indexes: %s\n", Arrays.toString(twoSum(nums, target)));
        System.out.printf("Values: %s\n", Arrays.toString(value));
    }


    /*
    * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    * You may assume that each input would have exactly one solution, and you may not use the same element twice.
    * You can return the answer in any order.
    * */
    private static int[] twoSum(int[] nums, int target) {
        int[] indexes = new int[2];
        for (int firstNum = 0; firstNum < nums.length; firstNum++) {
            value[0] = nums[firstNum];
            indexes[0] = firstNum;
            for (int secondNum = nums.length - 1; secondNum > firstNum; secondNum--) {
                if (target - nums[firstNum] == nums[secondNum]) {
                    value[1] = nums[secondNum];
                    indexes[1] = secondNum;
                    return indexes;
                }
            }
        }
        return indexes;
    }
}
