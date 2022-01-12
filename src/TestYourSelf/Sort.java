package TestYourSelf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.*;

public class Sort {
    public static void main(String[] args) {
        int size = 10, maxVal = 100;
        Supplier<int[]> arr = () -> fillArr(size, maxVal);
        System.out.printf("Bubble: %s\n", Arrays.toString(bubbleSort(arr.get())));
        System.out.printf("Select: %s\n", Arrays.toString(selectSort(arr.get())));
        System.out.printf("Insert: %s\n", Arrays.toString(insertSort(arr.get())));
        System.out.printf("Radix: %s\n", Arrays.toString(radixSort(arr.get())));
        System.out.printf("Count: %s\n", Arrays.toString(countSort(arr.get())));
        System.out.printf("Merge: %s\n", Arrays.toString(mergeSort(arr.get())));
    }

    private static int[] bubbleSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1)
            for (int j = 0; j < len - 1; j += 1)
                if (arr[j] - arr[j + 1] > 0) swap(j, j + 1, arr);
        return arr;
    }

    private static int[] selectSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1)
            for (int j = i + 1; j < len; j += 1)
                if (arr[i] - arr[j] > 0) swap(i, j, arr);
        return arr;
    }

    private static int[] insertSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1)
            for (int j = i; j > 0 && arr[j - 1] - arr[j] > 0; j -= 1)
                swap(j - 1, j, arr);
        return arr;
    }

    private static int[] radixSort(int[] arr) {
        int max = 1 << 16;
        List<List<Integer>> digits = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        List<List<Integer>> digits2 = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        Arrays.stream(arr).forEach(elt -> digits.get(elt % max).add(elt));
        digits.forEach(eltList -> eltList.forEach(elt -> digits2.get(elt / max).add(elt)));
        return digits2.stream().flatMapToInt(eltList -> eltList.stream().mapToInt(i -> i)).toArray();
    }

    private static int[] countSort(int[] arr) {
        int len = arr.length, b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
        return arr;
    }

    private static int[] mergeSort(int[] arr) {
        for (int i = 1, len = arr.length; i < len; i <<= 1)
            for (int j = 0; j < len - i; j += i << 1)
                merge(j, j + i, Math.min(len, j + (i << 1)), arr);
        return arr;
    }

    private static void merge(int l , int mid, int r, int[] arr) {
        int it1 = 0, it2 = 0;
        int[] merge = new int[r - l];
        while(l + it1 < mid && mid + it2 < r) {
            if (arr[l + it1] < arr[mid + it2]) {
                merge[it1 + it2] = arr[l + it1];
                it1 += 1;
            } else {
                merge[it1 + it2] = arr[mid + it2];
                it2 += 1;
            }
        }
        while(l + it1 < mid) {
            merge[it1 + it2] = arr[l + it1];
            it1 += 1;
        }
        while(mid + it2 < r) {
            merge[it1 + it2] = arr[mid + it2];
            it2 += 1;
        }
        System.arraycopy(merge, 0, arr, l, it1 + it2);
    }

    private static void swap(int j, int i, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static int[] fillArr(int size, int maxVal) {
        int[] res = new int[size];
        IntStream.range(0, size).forEach(i -> res[i] = ThreadLocalRandom.current().nextInt(maxVal));
        return res;
    }
}
