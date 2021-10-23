package TestYourSelf;

import java.util.Arrays;

public class Combinatorics {
    private static final int maxF = (int) 1e2;
    private static final int[] fact = facts();
    private static int[] arr = {1, 2, 3, 4};

    public static void main(String[] args) {

        String perm = getPermutation(4, 13);
        System.out.println(perm);

        System.out.printf("%d)\t%s\n", 1, Arrays.toString(arr));
        int n = fact[arr.length];
        for (int i = 1; i < n; i += 1) {
            arr = nextPermutation(arr);
            System.out.printf("%d)\t%s\n", i + 1, Arrays.toString(arr));
        }
    }

    private static String getPermutation(int n, int k) {
        boolean[] digits = new boolean[n + 1];
        StringBuilder perm = new StringBuilder();
        for (int i = n; i > 0; i -= 1) {
            int pn = k % fact[i - 1];
            int d = k / fact[i - 1];
            if (pn == 0) pn = fact[i - 1];
            else d += 1;
            k = pn;
            int pos = 0;
            for (int j = 1; j <= n; j += 1) {
                if (!digits[j]) pos += 1;
                if (pos == d) {
                    digits[j] = true;
                    perm.append(j);
                    break;
                }
            }
        }
        return perm.toString();
    }

    private static int[] nextPermutation(int[] arr) {
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
        return arr;
    }

    private static int[] sort(int start, int[] arr) {
        int len = arr.length, b = 8, dw = Integer.BYTES;
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
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static int[] facts() {
        int[] fact = new int[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}
