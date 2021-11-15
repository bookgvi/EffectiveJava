package TestYourSelf.DP;

import java.util.*;

public class LIS {
    /*
    * nums = {2, 1, 3, 4, 1, 1}
    * values = {0, 0, 0, 0, 0, 0}
    * counter = 0, i = 1, j = 0
    * nums[i] = 1, nums[j] = 2
    *
    * */
    private static int[] solve(int[] arr) {
        int len = arr.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i = 1; i < len; i += 1) {
            int max = 0;
            for (int j = i - 1; j >= 0; j -= 1) {
                if (dp[j] > max && arr[j] < arr[i])
                    max = dp[j];
            }
            dp[i] = max + 1;
        }
        return dp;
    }

    private static List<Integer> solveExt(int[] arr) {
        int len = arr.length;
        List<Integer> dp = new ArrayList<>();
        for (int i = 0; i < len; i += 1) {
            int index = binSearch(arr[i], dp);
            if (index < 0) index = -(index + 1);
            if (index >= dp.size()) dp.add(arr[i]);
            else dp.set(index, arr[i]);
        }
        return dp;
    }

    private static int binSearch(int n, List<Integer> arr) {
        int len = arr.size(), l = 0, r = len - 1;
        while(r - l >= 0) {
            int mid = (r + l) >>> 1;
            int res = arr.get(mid);
            if (res == n) return mid;
            else if (res < n) l = mid + 1;
            else r = mid - 1;
        }
        return -(l + 1);
    }

    public static void main(String[] args) {
//        int[] nums = {1, 4, 2, 5, 6, 3, 7};
        int[] nums = {2, 1, 3, 4, 1, 1};
        int[] res = solve(nums);
        List<Integer> resE = solveExt(nums);
    }
}
