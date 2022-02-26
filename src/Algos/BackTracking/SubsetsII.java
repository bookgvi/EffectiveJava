package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/subsets-ii/submissions/
 * */
public class SubsetsII {
    private static List<Integer> positions, subset;

    public static void main(String[] args) {
        int[] nums = {1, 1};
        List<List<Integer>> res = subsetsWithDup(nums);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        positions = new ArrayList<>();
        subset = new ArrayList<>();
        Map<Integer, Integer> numsCount = new HashMap<>();
        for (int num : nums)
            numsCount.put(num, numsCount.getOrDefault(num, 0) + 1);
        int[] uniqueNums = numsCount.keySet().stream().mapToInt(i -> i).toArray();
        backTrack(uniqueNums, numsCount, res);
        return res;
    }

    private static List<List<Integer>> backTrack(int[] uniqueNums, Map<Integer, Integer> numsCount, List<List<Integer>> ans) {
        for (int n = 0; n < uniqueNums.length; n += 1) {
            int num = uniqueNums[n];
            if (canUse(n)) {
                for (int i = 1; i <= numsCount.get(num); i += 1) {
                    place(num, n, i);
                    backTrack(uniqueNums, numsCount, ans);
                    ans.add(List.copyOf(subset));
                    removeLast(i);
                }
            }
        }
        return ans;
    }

    private static boolean canUse(int pos) {
        if (positions.isEmpty()) return true;
        return positions.get(positions.size() - 1) < pos;
    }

    private static void place(int num, int pos, int count) {
        for (int i = 0; i < count; i += 1)
            subset.add(num);
        positions.add(pos);
    }

    private static void removeLast(int count) {
        if (!positions.isEmpty()) positions.remove(positions.size() - 1);
        for (int i = 0; i < count; i += 1)
            if (!subset.isEmpty()) subset.remove(subset.size() - 1);
    }
}
