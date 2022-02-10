package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/subarray-sum-equals-k/submissions/
 * */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {0, 1, 1};
        int k = 1;
        int res = SolutionI.subarraySum(nums, k);
        int res2 = SolutionII.subarraySum(nums, k);
    }

    private static class SolutionII {
        // success
        public static int subarraySum(int[] nums, int k) {
            int len = nums.length, sum = 0, count = 0;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < len; i += 1) {
                sum += nums[i];
                if (map.containsKey(sum - k))
                    count += map.get(sum - k);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    private static class SolutionI {
        // TLE
        public static int subarraySum(int[] nums, int k) {
            int len = nums.length, val = 0;
            Map<Integer, Integer> dp = new HashMap<>();
            for (int i = 0; i < len; i += 1) {
                val = nums[i] == k ? dp.getOrDefault(nums[i], 0) + 1 : Integer.MIN_VALUE;
                if (val != Integer.MIN_VALUE) dp.put(nums[i], val);
                val = nums[i];
                for (int j = i - 1; j >= 0; j -= 1) {
                    val += nums[j];
                    if (val == k) dp.put(nums[i], dp.getOrDefault(nums[i], 0) + 1);
                }
            }
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : dp.entrySet())
                count += entry.getValue();
            return count;
        }

    }
}
