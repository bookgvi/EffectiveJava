package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;

/*
 * https://leetcode.com/problems/permutations/submissions/
 * */
public class Permutations {
    private static final int max = 7;
    private static final int[] fact = fact();
    private static int[] getPermutation(int[] numsOrig) {
        int len = numsOrig.length;
        int[] nums = Arrays.copyOf(numsOrig, len);
        for (int i = len - 2; i >= 0; i -= 1) {
            if (nums[i] < nums[i + 1]) {
                int min = i + 1;
                for (int j = min; j < len; j += 1)
                    if (nums[j] < nums[min] && nums[j] > nums[i])
                        min = j;
                swap(i, min, nums);
                reverse(i + 1, len - 1, nums);
                break;
            }
        }
        return nums;
    }

    private static void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void reverse(int start, int end, int[] arr) {
        for (int i = start; i <= (start + end) >> 1; i += 1) {
            int tmp = start + end - i;
            swap(i, tmp, arr);
        }
    }

    private static int[] fact() {
        int[] fact = new int[max];
        fact[0] = 1;
        for (int i = 1; i < max; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }

    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        nums = Arrays.stream(nums).sorted().toArray();
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        for (int i = 1; i < fact[len]; i += 1) {
            nums = getPermutation(nums);
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        List<List<Integer>> res = permute(arr);
    }
}
