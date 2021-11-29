package TestYourSelf;

import java.util.Arrays;

public class Combinatorics {
    private static final int maxF = (int) 1e2;
    private static final int[] fact = facts();
    private static final int[][] comb = comb();
    private static int[] arr = {1, 2, 3, 4};

    public static void main(String[] args) {

        String perm = getPermutation(4, 19);
        System.out.println(perm);

        int[] tmpArr = {1, 2, 3, 4, 5};
        reverse(1, tmpArr.length - 2, tmpArr);

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
        for (int i = n - 1; i >= 0; i -= 1) {
            int pn = k % fact[i];
            int d = k / fact[i];
            if (pn == 0) pn = fact[i];
            else d += 1;
            k = pn;
            int pos = 0;
            for (int j = 1; j <= n; j += 1) {
                if (digits[j] == 0) pos += 1;
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
        for (int i = arr.length - 2; i >= 0; i -= 1) {
            if (arr[i] < arr[i + 1]) {
                int min = i + 1;
                for (int j = min; j < arr.length; j += 1)
                    if (arr[j] < arr[min] && arr[j] > arr[i])
                        min = j;
                swap(i, min, arr);
                reverse(i + 1, arr.length - 1, arr);
                break;
            }
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(int start, int end, int[] arr) {
        for (int i = start; i <= (start + end) >> 1; i += 1) {
            int tmp = start + end - i;
            swap(tmp, i, arr);
        }
    }

    private static int[][] comb() {
        int[][] comb = new int[maxF + 1][maxF + 1];
        comb[0][0] = 1;
        for (int i = 1; i <= maxF; i += 1) {
            comb[i][0] = 1;
            comb[i][i] = 1;
            for (int j = 1; j < i; j += 1) {
                comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
            }
        }
        return comb;
    }

    private static int[] facts() {
        int[] facts = new int[maxF];
        facts[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            facts[i] = facts[i - 1] * i;
        return facts;
    }
}
