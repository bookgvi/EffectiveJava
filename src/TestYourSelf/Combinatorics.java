package TestYourSelf;

import java.util.Arrays;

public class Combinatorics {
    private static final int maxF = (int) 1e2;
    private static final int[] fact = facts();
    private static int[] arr = {1, 2, 3, 4};

    public static void main(String[] args) {

        String perm = getPermutation(4, 9);
        System.out.println(perm);

        System.out.printf("%d)\t%s\n", 1, Arrays.toString(arr));
        int n = fact[arr.length];
        for (int i = 1; i < n; i += 1) {
            arr = nextPermutation(arr);
            System.out.printf("%d)\t%s\n", i + 1, Arrays.toString(arr));
        }
    }

    private static String getPermutation(int n, int k) {
        StringBuilder res = new StringBuilder();
        int[] digits = new int[n + 1];
        for (int i = n; i > 0; i -= 1) {
            int np = k % fact[i - 1];
            int d = k / fact[i - 1];
            if (np == 0) np += fact[i - 1];
            else d += 1;
            k = np;
            int pos = 0;
            for (int j = 1; j <= n; j += 1) {
                if (digits[j] == 0) pos++;
                if (pos == d) {
                    digits[j] = 1;
                    res.append(j);
                    break;
                }
            }
        }
        return res.toString();
    }

    private static int[] nextPermutation(int[] arr) {
        for (int len = arr.length, i = len - 2; i >= 0; i -= 1) {
            if (arr[i] < arr[i + 1]) {
                int min = i + 1;
                for (int j = min; j < len; j += 1)
                    if (arr[j] > arr[i] && arr[j] < arr[min])
                        min = j;
                swap(i, min, arr);
                sort(i + 1, arr);
                return arr;
            }
        }
        return null;
    }

    private static void sort(int start, int[] arr) {
        int b = 8, dw = Integer.BYTES, len = arr.length;
        int[] t = new int[len - start];
        for (int p = 0; p < dw; p += 1) {
            int[] c = new int[1 << b];
            for (int i = start; i < len; i += 1)
                c[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                c[i] += c[i - 1];
            for (int i = len - 1; i >= start; i -= 1)
                t[--c[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, start, len - start);
        }
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
