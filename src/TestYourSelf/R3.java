package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class R3 {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 5, 10};
        System.out.println(Arrays.toString(coins));
        long nFact = fact(coins.length);
        for (int i = 1; i < nFact; i += 1) {
            nextPermutation(coins);
            System.out.println(Arrays.toString(coins));
        }
    }

    private static void nextPermutation(int[] arr) {
        int len = arr.length;
        for (int i = len - 2; i >= 0; i -= 1) {
            if (arr[i] < arr[i + 1]) {
                int min = i + 1;
                for (int j = min; j < len; j += 1) {
                    if (arr[j] < arr[min] && arr[j] > arr[i])
                        min = j;
                }
                swap(i, min, arr);
                sort(i + 1, arr);
                break;
            }
        }
    }

    private static void sort(int start, int[] arr) {
        int b = 8, dw = Integer.BYTES, len = arr.length;
        int[] t = new int[len];
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

    private static void swap(int i, int j, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static long fact(int n) {
        return getFact(1, n);
    }

    private static long getFact(int l, int r) {
        if (l == r) return l;
        if (r - l == 1) return (long) l * r;
        int mid = (r + l) >> 1;
        return getFact(l, mid) * getFact(mid + 1, r);
    }
}
