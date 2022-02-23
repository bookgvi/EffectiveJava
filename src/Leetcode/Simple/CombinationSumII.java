package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/combination-sum-ii/submissions/
 * */
public class CombinationSumII {
    private static List<Integer> result, positions;
    private static List<List<Integer>> allResults;
    private static Map<Integer, Integer> quantity;

    public static void main(String[] args) {
//        int[] candidates = {10, 1, 2, 7, 6, 1, 5}; // 8
//        int[] candidates = {2,5,2,1,2}; // 5
//        int[] candidates = {3, 3, 1, 3, 5, 1, 1}; // 8
//        int[] candidates = {1,1,1,1,1,2,2}; // 2
//        int[] candidates = {1,1}; // 2
        int[] candidates = {5, 4, 5, 1, 5, 3, 1, 4, 5, 5, 4}; // 10
        int target = 10;
        List<List<Integer>> res = combinationSum2(candidates, target);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        result = new ArrayList<>();
        positions = new ArrayList<>();
        allResults = new ArrayList<>();
        quantity = new HashMap<>();
        for (int elt : candidates) {
            quantity.put(elt, quantity.getOrDefault(elt, 0) + 1);
        }
        candidates = quantity.keySet().stream().mapToInt(i -> i).toArray();
        backTrack(candidates, target);
        return allResults;
    }

    private static void backTrack(int[] candidates, int target) {
        int len = candidates.length;
        for (int pos = 0; pos < len; pos += 1) {
            if (isNext(pos)) {
                for (int count = 1; count <= quantity.get(candidates[pos]); count += 1) {
                    if (count * candidates[pos] <= target) {
                        target = place(candidates, pos, target, count);
                        if (target == 0) {
                            allResults.add(List.copyOf(result));
                        } else
                            backTrack(candidates, target);
                        target = remove(candidates, pos, target, count);
                    }
                }
            }
        }
    }

    private static boolean isNext(int pos) {
        return positions.isEmpty() || positions.get(positions.size() - 1) < pos;
    }

    private static int place(int[] candidates, int pos, int target, int count) {
        for (int i = 0; i < count; i += 1) {
            target -= candidates[pos];
            result.add(candidates[pos]);
        }
        positions.add(pos);
        return target;
    }

    private static int remove(int[] candidates, int pos, int target, int count) {
        int lastPos = positions.get(positions.size() - 1);
        for (int i = 0; i < count; i += 1) {
            target += candidates[lastPos];
            result.remove(result.size() - 1);
        }
        positions.remove(positions.size() - 1);
        return target;
    }

}
