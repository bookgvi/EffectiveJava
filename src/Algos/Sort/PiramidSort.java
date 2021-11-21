package Algos.Sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class PiramidSort {

    public static void main(String[] args) {
        int max = 15, count = 15;
        Supplier<int[]> supArr = () -> fillArr(max, count);
        int[] arr = supArr.get();
        System.out.println(Arrays.toString(arr));
        arr = sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] arr) {
        int len = arr.length;
        int[] tmpArr = Arrays.copyOf(arr, len), sorted = new int[len];
        build(tmpArr);
        for (int i = 0; i < len; i += 1) {
            sorted[i] = tmpArr[0];
            tmpArr[0] = Integer.MAX_VALUE;
            siftDown(0, tmpArr);
        }
        return sorted;
    }

    private static void build(int[] arr) {
        for (int i = arr.length; i >= 0; i -= 1)
            siftDown(i, arr);
    }

    private static void siftDown(int v, int[] arr) {
        int len = arr.length, half = len >> 1;
        while (v < half) {
            int l = (v << 1) + 1;
            int r = l + 1;
            int tmp = r < len && arr[r] < arr[l] ? r : l;
            if (arr[v] <= arr[tmp]) break;
            swap(v, tmp, arr);
            v = tmp;
        }
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static int[] fillArr(int maxVal, int count) {
        return IntStream.range(0, count).map(i -> ThreadLocalRandom.current().nextInt(maxVal)).toArray();
    }
}
