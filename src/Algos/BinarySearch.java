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
        int[] arr1 = {2, 4, 4, 5, 6, 6, 6, 7, 7, 8};
        System.out.println(Arrays.toString(arr1));
        System.out.println(search4(n, Arrays.stream(arr1).sorted().toArray()));
    }

    private static int search(int n, int[] arr) {
        int len = arr.length;
        int l = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (l + i < len && arr[i + l] <= n) l += i;
        }
        if (arr[l] == n) return l;
        return -1;
    }

    private static int search1(int n, int[] arr) {
        int len = arr.length;
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && arr[i + k] <= n) k += i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static int search2(int n, int[] arr) {
        int len = arr.length;
        int l = 0;
        int r = len - 1;
        while (r - l >= 0) {
            int i = (r + l) / 2;
            if (arr[i] == n) return i;
            if (arr[i] < n) {
                l = i + 1;
            } else {
                r = i - 1;
            }
        }
        return -1;
    }

    private static int search3(int n, int[] arr) {
        int len = arr.length;
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && arr[i + k] <= n) k += i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static int search4(int n, int[] arr) {
        int len = arr.length;
        int l = 0;
        int r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr[mid] == n) return mid;
            else if (arr[mid] < n) l = mid + 1;
            else r = mid - 1;
        }
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
