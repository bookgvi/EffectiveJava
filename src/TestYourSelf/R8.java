package TestYourSelf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.*;

public class R8 {
    public static void main(String[] args) {
        int maxValue = 15;
        int n = ThreadLocalRandom.current().nextInt(maxValue);
        Supplier<int[]> arr = () -> fillArr(n, maxValue);
        System.out.println(Arrays.toString(sort(arr.get())));
    }

    private static int[] sort(int[] arr) {
        for (int i = 1, len = arr.length; i < len; i <<= 1) {
            for (int j = 0; j < len; j += i << 1) {
                merge(j, Math.min(j + i, len), Math.min(j + (i << 1), len), arr);
            }
        }
        return arr;
    }

    private static int[] merge(int l, int mid, int r, int[] arr) {
        int[] merge = new int[r - l];
        int it1 = 0, it2 = 0;
        while (l + it1 < mid && mid + it2 < r) {
            if (arr[l + it1] < arr[mid + it2]) {
                merge[it1 + it2] = arr[l + it1];
                it1 += 1;
            } else {
                merge[it1 + it2] = arr[mid + it2];
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge[it1 + it2] = arr[l + it1];
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge[it1 + it2] = arr[mid + it2];
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1) {
            arr[l + i] = merge[i];
        }
        return merge;
    }

    private static int[] fillArr(int size, int maxValue) {
        int[] arr = new int[size];
        IntStream.range(0, size).forEach(i -> arr[i] = ThreadLocalRandom.current().nextInt(maxValue));
        return arr;
    }

}
