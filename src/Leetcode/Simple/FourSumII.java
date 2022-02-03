package Leetcode.Simple;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sumMap = new HashMap<>();
        int ans = 0, n = nums1.length;
        for (int i = 0; i < n; i += 1)
            for (int j = 0; j < n; j += 1) {
                int sum = nums1[i] + nums2[j];
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            }
        for (int i = 0; i < n; i += 1)
            for (int j = 0; j < n; j += 1) {
                int sum = nums3[i] + nums4[j];
                if (sumMap.containsKey(-sum)) ans += sumMap.get(-sum);
            }
        return ans;
    }

    public static void main(String[] args) {
        int[] n1 = {-1, -1}, n2 = {-1, 1}, n3 = {-1, 1}, n4 = {1, -1};
        int res = fourSumCount(n1, n2, n3, n4);
    }
}
