package Algos.Arrays;

import java.util.*;
import java.util.stream.*;

public class SubSequence {
    private static boolean isSubsequence(List<Integer> seq1, List<Integer> seq2) {
        int i = 0, j = 0, len1 = seq1.size(), len2 = seq2.size();
        if (len1 < len2) return false;
        List<Integer> ans = new ArrayList<>();
        while (i < len1 && j < len2) {
            if (seq1.get(i) == seq2.get(j)) {
                ans.add(seq1.get(i));
                i += 1;
                j += 1;
            } else i += 1;
        }
        return ans.size() == len2;
    }

    private static int[] subSeqSizeDP(List<Integer> seq1, List<Integer> seq2) {
        int len1 = seq1.size(), len2 = seq2.size(), i = 1, j = 1;
        int[] dp = new int[len2];
        dp[0] = seq1.get(0) == seq2.get(0) ? 1 : 0;
        int max = dp[0];
        while (i < len1 && j < len2) {
            if (seq1.get(i) == seq2.get(j)) {
                dp[j] = dp[j - 1] + 1;
                max = Math.max(max, dp[j]);
                i += 1; j += 1;
            } else {
                i += 1;
            }
        }
        for (int k = len2 - 1; k >= 0 && dp[k] == 0; k -= 1)
            dp[k] = max;
        return dp;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 3, 5, 6, 7, 8, 8, 8, 8, 8};
        int[] arr2 = new int[]{1, 3, 6, 5, 7};
        List<Integer> seq1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        List<Integer> seq2 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        isSubsequence(seq1, seq2);
        subSeqSizeDP(seq1, seq2);
    }
}
