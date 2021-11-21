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
        int[] arr2 = {1,1,0,-2147483648};
        System.out.println(Arrays.toString(arr));
        long startTime = System.nanoTime();
        radixSort(arr);
        long endTime = System.nanoTime();
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);
        System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] a) {
        int len = a.length, b= 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : a)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((a[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = a[i];
            System.arraycopy(t, 0, a, 0, len);
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
