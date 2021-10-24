package Leetcode.Simple;

import java.util.Arrays;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = {1,2,-4};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {
        int len = nums.length;
        for (int i = len - 2; i >= 0; i -= 1) {
            if (nums[i] < nums[i + 1]) {
                int min = i + 1;
                for (int j = min; j < len; j += 1)
                    if (nums[j] < nums[min] && nums[j] > nums[i])
                        min = j;
                swap(i, min, nums);
                sort(i + 1, nums);
                return;
            }
        }
        sort(0, nums);
    }

    public static void swap(int i, int j, int[] arr) {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static void sort(int start, int[] arr) {
        int len = arr.length, b = 16, dw = 2;
        int[] t = new int[len - start];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int i = start; i < len; i += 1)
                count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= start; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, start, len - start);
        }
    }
}
