package Leetcode.Simple;

import java.util.*;
import java.util.stream.*;

// Правильный ответ
//https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/submissions/

//First, we mod the duration of each song by 60 and count the number of different remainders
//        for t in time: cnt[t % 60] += 1
//        If sum of 2 numbers divisible by 60, there are 3 possible situations
//        both are divisible by 60, so after mod 60 the remainders are both 0
//        cnt[0] * (cnt[0] - 1) // 2
//        cnt[0] - 1 because one cannot pair with itself
//// 2 because (a, b) and (b, a) are the same pair and we can only add once
//        both are left 30 after mod 60
//        cnt[30] * (cnt[30] - 1) // 2
//        the same as cnt[0] above
//        their remainders are not the same, but the sum of remainders is equal to 60
//        for i in range(1, 30): ans += cnt[i] * cnt[60 - i]
//        we only loop from 1 to 29, because 60 - i is in the range of (31, 59). In this way, we enumerate all pairs and will not repeat like (a, b) and (b, a).

//class Solution {
//    public int numPairsDivisibleBy60(int[] time) {
//        int ans = 0, cnt[] = new int[60];
//        for (int i = 0; i < time.length; i ++) cnt[time[i] % 60] += 1;
//        for (int i = 1; i< 30; i ++) ans += cnt[i] * cnt[60 - i];
//        return ans + cnt[0] * (cnt[0] - 1) / 2 + cnt[30] * (cnt[30] - 1) / 2;
//    }
//}


// TLE
public class PairsofSongsWithTotalDurationsDivisibleby60 {
    private static final int MAX = 501;
    private static final List<List<Integer>> subsets = new ArrayList<>();


    private static List<Integer> nextSubset(List<Integer> orig) {
        List<Integer> arr= new ArrayList<>(List.copyOf(orig));
        int len = arr.size(), fst, k;
        for (fst = len - 2; fst >= 0; fst -= 1)
            if (arr.get(fst) == 0 && arr.get(fst + 1) == 1) break;
        if (fst < 0) List.of();
        arr.set(fst, 1);
        int ones = 0;
        for (int j = fst + 1; j < len; j += 1)
            ones += arr.get(j);
        for (k = fst + 1; k <= len - ones; k += 1)
            arr.set(k, 0);
        for (; k < len; k += 1)
            arr.set(k, 1);
        return arr;
    }

    public static int numPairsDivisibleBy60(int[] time) {
        int len = time.length, sum = 0, mod = 60, sumCount = 0;
        int res = 0, maxIter = len * (len - 1) / 2;
        if (len > 1) {
            List<Integer> subset = IntStream.range(0, len).mapToObj(i -> 0).collect(Collectors.toList());
            subset.set(len - 1, 1);
            subset.set(len - 2, 1);
            subsets.add(subset);
            for (int i = 1; i < maxIter; i += 1) {
                subsets.add(nextSubset(subsets.get(i - 1)));
            }
            for (int i = 0; i < maxIter; i += 1) {
                subset = subsets.get(i);
                for (int j = 0; j <= (len - 1) >> 1; j += 1) {
                    int tmp = (len - 1) - j;
                    if (subset.get(j) == 1) {
                        sum += time[j];
                        sumCount += 1;
                    }
                    if (tmp != j && subset.get(tmp) == 1) {
                        sum += time[tmp];
                        sumCount += 1;
                    }
                    if (sumCount == 2) break;
                }
                res = (sum % mod) == 0 ? res + 1 : res;
                sum = 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{60,60};
        int[] arr = new int[]{60,1};
//        int[] arr = new int[]{15, 63, 451, 213, 37, 209, 343, 319};
//        int[] arr = new int[]{60};
//        int[] arr = new int[]{30, 20, 150, 100, 40};
//        int[] arr = new int[]{60, 60, 60};
        System.out.println(numPairsDivisibleBy60(arr));
    }
}
