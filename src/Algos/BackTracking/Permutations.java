package Algos.BackTracking;

import java.util.*;

public class Permutations {
    private static int len;
    private static List<Integer> permutation, indexes;
    private static List<List<Integer>> allPermutations;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        len = nums.length;
        permutation = new ArrayList<>();
        indexes = new ArrayList<>();
        allPermutations = new ArrayList<>();
        backtrack(nums);
        int count = allPermutations.size();
    }

    private static void backtrack(int[] nums) {
        for (int next = 0; next < len; next += 1) {
            if (check(nums, next)) {
                place(nums, next);
                if (permutation.size() == len)
                    allPermutations.add(List.copyOf(permutation));
                else
                    backtrack(nums);
                removeLast();
            }
        }
    }

    private static boolean check(int[] nums, int next) {
        if (indexes.size() == 0) return true;
        for (int index : indexes)
            if (next == index) return false;
        return true;
    }

    private static void place(int[] nums, int next) {
        permutation.add(nums[next]);
        indexes.add(next);
    }

    private static void removeLast() {
        permutation.remove(permutation.size() - 1);
        indexes.remove(indexes.size() - 1);
    }
}
