package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/combination-sum-iii/submissions/
 * */
public class CombinationSumIII {
    private static int[] used;
    private static int sum;
    private static List<Integer> nums;

    private static List<List<Integer>> combinationSum3(int k, int n) {
        used = new int[10];
        sum = 0;
        nums = new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        backTrack(k, n, res);
        return res;
    }

    private static void backTrack(int k, int n, List<List<Integer>> res) {
        for (int i = 1; i <= 9; i += 1) {
            if (canUse(k, n, i)) {
                place(i);
                if (isFinish(k, n)) {
                    res.add(List.copyOf(nums));
                } else
                    backTrack(k, n, res);
                removeLast(i);
            }
        }
    }

    private static boolean canUse(int k, int n, int num) {
        boolean ans = used[num] == 0;
        ans &= sum + num <= n;
        ans &= nums.size() + 1 <= k;
        ans &= nums.isEmpty() ? num > 0 : num > nums.get(nums.size() - 1);
        return ans;
    }

    private static boolean isFinish(int k, int n) {
        boolean ans = sum == n;
        ans &= nums.size() == k;
        return ans;
    }

    private static void place(int num) {
        used[num] = 1;
        sum += num;
        nums.add(num);
    }

    private static void removeLast(int num) {
        used[num] = 0;
        sum -= num;
        nums.remove(nums.size() - 1);
    }

    public static void main(String[] args) {
        int n = 1, k = 4;
        List<List<Integer>> res = combinationSum3(k, n);
    }
}
