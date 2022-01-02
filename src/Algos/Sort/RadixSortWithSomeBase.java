package Algos.Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class RadixSortWithSomeBase {
    private static int[] sort(int[] arr, int mod) {
        int len = arr.length;
        int[] t = new int[mod], count = new int[mod];
        for (int i = 0; i < len; i += 1)
            count[arr[i] % mod] += 1;
        for (int i = 1; i < mod; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            t[--count[arr[i] % mod]] = arr[i];
        System.arraycopy(t, 0, arr, 0, len);
        return arr;
    }

    private static int[] fillArr(int count, int maxValue) {
        int[] arr = new int[count];
        for (int i = 0; i < count; i += 1) {
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int count = (int) 10;
        int maxValue = 150;
        Supplier<int[]> a = () -> fillArr(count, maxValue);
        int[] arr = a.get();
        int[] arr2 = {1,1,0,-2147483648};
        System.out.println(Arrays.toString(arr));
        long startTime = System.nanoTime();
        arr = sort(arr, maxValue);
        long endTime = System.nanoTime();
        System.out.printf("%.8f\n", (endTime - startTime) / 1e9);
        System.out.println(Arrays.toString(arr));
    }

}
