package Algos.BackTracking;

import java.util.*;

public class Combinations {
    private static int n, m;
    private static final List<Integer> nextComb = new ArrayList<>();
    private static final List<List<Integer>> allCombs = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = {2, 5, 7, 8, 9};
        n = nums.length;
        m = 3;
        backTrackComb(nums, 0);
        int res = allCombs.size();
    }

    private static void backTrackComb(int[] arr, int j) {
        for (int i = 0; i < n; i += 1) {
            if (isNotConstraint(arr, i)) {
                place(arr, i);
                if (j + 1 == m) {
                    allCombs.add(List.copyOf(nextComb));
                } else backTrackComb(arr, j + 1);
                remove();
            }
        }
    }

    private static boolean isNotConstraint(int[] arr, int i) {
        int len = nextComb.size();
        if (len == 0) return true;
        boolean ans = true;
        for (int prevNum : nextComb) {
            for (int k = i; k < n; k += 1) {
                ans = prevNum != arr[k];
                if (!ans) return false;
            }
        }
        return ans;
    }

    private static void place(int[] arr, int i) {
        nextComb.add(arr[i]);
    }

    private static void remove() {
        nextComb.remove(nextComb.size() - 1);
    }

}
