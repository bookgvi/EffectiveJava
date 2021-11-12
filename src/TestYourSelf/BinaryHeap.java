package TestYourSelf;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BinaryHeap {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 11, 9, 10, 5, 6, 8, 1, 2, 4, 10, 4, 0};
        System.out.println(Arrays.toString(arr));

        arr = sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static int[] sort(int[] arr) {
        int len = arr.length;
        int[] sorted = new int[len];
        build(arr);
        for (int i = 0; i < len; i += 1) {
            sorted[i] = arr[0];
            arr[0] = Integer.MAX_VALUE;
            siftDown(0, arr);
        }
        return sorted;
    }

    private static void build(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i -= 1)
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
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
