package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/submissions/
 * */
public class KdiffPairsinanArray {
    public static int findPairs(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int elt : nums)
            map.put(elt, map.getOrDefault(elt, 0) + 1);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k > 0) {
                count += map.containsKey(entry.getKey() - k) ? 1 : 0;
            } else if (k == 0) {
                count += entry.getValue() > 1 ? 1 : 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 3};
        int k = 0;
        int res = findPairs(nums, k);
    }
}
