package Leetcode.Simple;

import java.util.Arrays;

public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int n = removeDuplicates(nums);
        System.out.println(n);
        System.out.println(Arrays.toString(nums));
    }

    public static int removeDuplicates(int[] nums) {
        int n = 0;
        int len = nums.length, i = len;
        while (i > 0) {
            int l = binSearchL(nums[i - 1], nums);
            int r = binSearchR(nums[i - 1], nums);
            if (l != r) {
                System.arraycopy(nums, r + 1, nums, l + 1, len - r - 1);
            }
            n += 1;
            i = i - (r - l) - 1;
        }
        return n;
    }
    public static int binSearchL(int n, int[] arr) {
        int len = arr.length, k = len - 1;
        for (int i = len >> 1; i > 0; i >>= 1)
            while (k - i >= 0 && arr[k - i] >= n) k -= i;
        if (arr[k] == n) return k;
        return -1;
    }

    public static int binSearchR(int n, int[] arr) {
        int len = arr.length, k = 0;
        for (int i = len >> 1; i > 0; i >>= 1)
            while (i + k < len && arr[k + i] <= n) k += i;
        if (arr[k] == n) return k;
        return -1;
    }
}
