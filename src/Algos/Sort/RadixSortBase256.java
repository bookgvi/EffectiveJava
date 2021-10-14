package Algos.Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class RadixSortBase256 {
    public static void main(String[] args) {
        int count = (int) 10;
        int maxValue = 20;
        Supplier<int[]> a = () -> fillArr(count, maxValue);
        int[] arr = a.get();
        int[] arr2 = a.get();
        System.out.println(Arrays.toString(arr));
        long startTime = System.nanoTime();
        radixSort(arr);
        long endTime = System.nanoTime();
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] a) {
        final int d = 8;
        final int w = 32;
        int[] t = new int[a.length];
        for (int p = 0; p < w / d; p++) {
            // counting-sort
            int[] cnt = new int[1 << d];
            for (int i = 0; i < a.length; i++) {
                ++cnt[((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)];
            }
            for (int i = 1; i < cnt.length; i++)
                cnt[i] += cnt[i - 1];
            for (int i = a.length - 1; i >= 0; i--)
                t[--cnt[((a[i] ^ Integer.MIN_VALUE) >>> (d * p)) & ((1 << d) - 1)]] = a[i];
            System.arraycopy(t, 0, a, 0, a.length);
        }
    }

    private static int[] fillArr(int count, int maxValue) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i += 1) {
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        }
        return arr;
    }
}
