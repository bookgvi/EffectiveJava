package Algos.Combinatorics;

import java.util.Arrays;

public class Permutations {
    private static final int maxF = (int) 1e2;
    private static final int[] fact = facts();
    private static final int[] arr = {1, 2, 3, 4};

    public static void main(String[] args) {

        String perm = getPermutation(4, 9);
        System.out.println(perm);

        System.out.printf("%d)\t%s\n", 1, Arrays.toString(arr));
        int n = fact[arr.length];
        for (int i = 1; i < n; i += 1) {
            nextPermutation(arr);
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

    /*
     * Перестановки. Генерация всех перестановок. Нерекурсивный алгоритм
     *    Рассмотримнерекурсивный алгоритмгенерацииперестановоквлексикографическомпорядке.
     *    1. Последовательность элементов просматривается с конца до тех пор, пока не будет
     *        встречен первый элемент, такой что a[i]<a[i+1].
     *    2. В «хвосте» последовательности, состоящем из элементов, расположенных за найден-
     *        ным элементом, производим поиск минимального элемента min, большего, чем a[i].
     *    3. Меняем местами a[i] и найденный элемент min.
     *    4. Сортируем хвост последовательности.
     * Такой алгоритм позволяет получить все перестановки в лексикографическом порядке.
     * */
    private static int[] nextPermutation(int[] arr) {
        int len = arr.length;
        for (int i = len - 2; i >= 0; i -= 1) {
            if (arr[i] < arr[i + 1]) {
                int min = i + 1;
                for (int j = min; j < len; j += 1)
                    if (arr[j] > arr[i] && arr[j] < arr[min])
                        min = j;
                swap(i, min, arr);
                reverse(i + 1, len - 1, arr);
//                sort(i + 1, arr);
                break;
            }
        }
        return arr;
    }

    private static void reverse(int start, int end, int[] arr) {
        for (int i = start; i <= (start + end) >> 1; i += 1) {
            int tmp = start + end - i;
            swap(i, tmp, arr);
        }
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
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] facts() {
        int[] fact = new int[maxF];
        fact[0] = 1;
        for (int i = 1; i < maxF; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }
}
