package Leetcode.Simple;

import java.util.*;
import java.util.stream.*;

/*
 * Given a set of distinct positive integers nums,
 * return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:
 * answer[i] % answer[j] == 0, or
 * answer[j] % answer[i] == 0
 * If there are multiple solutions, return any of them.
 *
 * https://leetcode.com/problems/largest-divisible-subset/
 * */
public class LargestDivisibleSubset {
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length, maxDPends = 0;
        int[] dp = new int[len], lds = new int[len];
        Arrays.fill(dp, 1);
        Arrays.fill(lds, -1);
        Arrays.sort(nums);
        for (int i = 1; i < len; i += 1) {
            for (int j = i - 1; j >= 0; j -= 1) {
                if (dp[j] + 1 > dp[i] && nums[i] % nums[j] == 0) {
                    dp[i] = dp[j] + 1;
                    lds[i] = j;
                }
            }
            if (dp[i] > dp[maxDPends]) maxDPends = i;
        }
        while (maxDPends >= 0) {
            res.add(nums[maxDPends]);
            maxDPends = lds[maxDPends];
        }
        return res;
    }

    private static void sort(int[] arr) {
        int len = arr.length, b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int i = 0; i < len; i += 1)
                count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b) & ((1 << b) - 1))] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
    }

    public static List<Integer> largestDivisibleSubset1(int[] nums) {
        List<Integer> lds = new ArrayList<>();
        lds.add(nums[0]);
        int len = nums.length;
        if (len == 1) return lds;
        for (int i = 1; i < len; i += 1) {
            int index = binSearch(nums[i], lds);
            int tmpIndex = Integer.MIN_VALUE;
            if (index < 0) tmpIndex = -(index + 1);
            if (index >= 0 && index < lds.size()) lds.add(nums[i]);
            else if (index < 0) lds.set(tmpIndex, nums[i]);
//            else lds.add(nums[i]);
        }
        if (lds.size() == 1) lds.remove(0);
        return lds;
    }

    private static int binSearch(int n, List<Integer> arr) {
        int len = arr.size(), l = 0, r = len - 1, mid = 0;
        while (r - l >= 0) {
            mid = (r + l) >>> 1;
            int val = arr.get(mid);
            if (val % n != 0 && n % val != 0) return -(l + 1);
            else if (val % n == val) l = mid + 1;
            else r = mid - 1;
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,8,9,27};
//        int[] nums = {6, 2,3, 18, 21};
//        int[] nums = {2,3};
//        int[] nums = {3, 6, 2, 4, 8};
        List<Integer> res = largestDivisibleSubset(nums);
    }
}
