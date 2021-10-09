package Algos;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

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
//        System.out.println(search2(n, Arrays.stream(arr1).sorted().toArray()));
    }

    private static int searchR(int n, int[] sortedArr) {
        int len = sortedArr.length;
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && sortedArr[i + k] <= n) k += i;
        }
        if (sortedArr[k] == n) return k;
        return -1;
    }

    private static int searchL(int n, int[] sortedArr) {
        int len = sortedArr.length;
        int k = len - 1;
        for (int i = len / 2; i > 0; i /= 2) {
            while(k - i >= 0 && sortedArr[k - i] >= n) k -= i;
        }
        if (sortedArr[k] == n) return k;
        return -1;
    }

    private static int[] fillArr(int max, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i += 1) {
            arr[i] = ThreadLocalRandom.current().nextInt(max);
        }
        return arr;
    }
}
