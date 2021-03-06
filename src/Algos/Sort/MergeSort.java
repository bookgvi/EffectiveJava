package Algos.Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args) {
        int count = 3, max = 10;
        Supplier<int[]> arr = () -> fillArr(count, max);
        int[] arr2 = arr.get();
        System.out.println(Arrays.toString(arr2));
        arr2 = sort(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    private static int[] sort(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 1, len = arr.length; i < len; i <<= 1) {
            for(int j = 0; j < len; j += i << 1) {
                res = merge(j, Math.min(j + i, len), Math.min(j + (i << 1), len), arr);
            }
        }
        return res;
    }

    private static int[] merge(int l, int mid, int r, int[] arr) {
        int[] merge = new int[r - l];
        int it1 = 0, it2 = 0;
        while (l + it1 < mid && mid + it2 < r) {
            if (arr[l + it1] < arr[mid + it2]) {
                merge[it1 + it2] = arr[it1 + l];
                it1 += 1;
            } else {
                merge[it1 + it2] = arr[mid + it2];
                it2 += 1;
            }
        }
        while (it1 + l < mid) {
            merge[it1 + it2] = arr[it1 + l];
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge[it1 + it2] = arr[mid + it2];
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1)
            arr[l + i] = merge[i];
        return merge;
    }

    private static int[] fillArr(int count, int max) {
        int[] arr = new int[count];
        IntStream.range(0, count).forEach(i -> arr[i] = ThreadLocalRandom.current().nextInt(max));
        return arr;
    }
}
