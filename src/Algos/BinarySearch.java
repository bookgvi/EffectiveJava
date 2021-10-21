package Algos;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class BinarySearch {
    public static void main(String[] args) {
        int size = 10;
        int max = 10;
        int n = 9;
        Supplier<int[]> arr = () -> fillArr(max, size);
//        int[] arr1 = Arrays.stream(fillArr(max, size)).sorted().toArray();
        int[] arr1 = {0, 0, 0, 3, 4, 4, 6, 7, 9, 9};
//        n = ThreadLocalRandom.current().nextInt(max);
        System.out.println(n);
        System.out.println(Arrays.toString(arr1));
        System.out.printf("%d ", searchL(n, Arrays.stream(arr1).sorted().toArray()));
        System.out.println(searchR(n, Arrays.stream(arr1).sorted().toArray()));
        System.out.println(binSearch(n, Arrays.stream(arr1).sorted().toArray()));
    }

    private static int searchL(int n, int[] arr) {
        int len = arr.length, k = len - 1;
        for (int i = len >> 1; i > 0; i >>= 1) {
            while(k - i >= 0 && arr[k - i] >= n) k -= i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static int searchR(int n, int[] arr) {
        int len = arr.length, k = 0;
        for(int i = len >> 1; i > 0; i >>= 1)
            while(k + i < len && arr[k + i] <= n) k += i;
        if (arr[k] == n) return k;
        return -1;
    }

    private static int binSearch(int n, int[] arr) {
        int len = arr.length, l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            if (arr[mid] == n) return mid;
            else if (arr[mid] < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static int[] fillArr(int max, int size) {
        int[] arr = new int[size];
        IntStream.range(0, size).forEach(i -> arr[i] = ThreadLocalRandom.current().nextInt(max));
        return arr;
    }
}
