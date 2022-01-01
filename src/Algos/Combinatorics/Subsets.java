package Algos.Combinatorics;

import java.util.Arrays;

public class Subsets {
    private static final long[] fact = fact();

    private static int[] nextSubSet(int[] subSet) {
        int len = subSet.length, i, j;
        int[] tmpSubSet = Arrays.copyOf(subSet, len);
        for (i = len - 2; i >= 0; i -= 1)
            if (tmpSubSet[i] == 0 && tmpSubSet[i + 1] == 1) break;
        tmpSubSet[i] = 1;
        int ones = 0;
        for (j = i + 1; j < len; j += 1)
            ones += tmpSubSet[j];
        for (j = i + 1; j <= len - ones; j += 1)
            tmpSubSet[j] = 0;
        for (; j < len; j += 1)
            tmpSubSet[j] = 1;
        return tmpSubSet;
    }

    private static long[] fact() {
        int max = 20;
        long[] fact = new long[max];
        fact[0] = 1;
        for (int i = 1; i < max; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 0, 0, 0, 0};

        int count = 1;
        System.out.printf("%d) %s\n", count++, Arrays.toString(arr));
        for (int k = 1, len = arr.length; k <= len; k += 1) {
            for (int j = 1; j <= k; j += 1) {
                arr[len - j] = 1;
            }
            long Cn = fact[len] / (fact[k] * fact[len - k]);
            System.out.printf("%d) %s\n", count++, Arrays.toString(arr));
            for (int i = 1; i < Cn; i += 1) {
                arr = nextSubSet(arr);
                System.out.printf("%d) %s\n", count++, Arrays.toString(arr));
            }
            arr = new int[]{0, 0, 0, 0, 0};
        }
    }
}
