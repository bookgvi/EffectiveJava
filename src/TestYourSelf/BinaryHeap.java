package TestYourSelf;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BinaryHeap {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 16, 11, 9, 10, 5, 6, 8, 1, 2, 4, 10, 4, 0};
        System.out.println(Arrays.toString(arr));
        int len = arr.length;

        buildHeap(arr);
        System.out.println(Arrays.toString(arr));

        int[] sortedArr = new int[len];
        for (int i = 1; i < len; i += 1) {
            sortedArr[i] = arr[1];
            arr[1] = Integer.MAX_VALUE;
            siftDown(1, arr);
        }

        System.out.println(Arrays.toString(sortedArr));
    }

    private static void buildHeap(int[] arr) {
        int len = arr.length;
        for (int i = len; i > 0; i -= 1)
            siftDown(i, arr);
    }

    private static void siftDown(int v, int[] arr) {
        int len = arr.length, half = len >>> 1;
        half = (len & 1) == 1 ? half + 1 : half;
        while (v < half) {
            int l = v << 1;
            int r = l + 1;
            int t = r < len && arr[r] < arr[l] ? r : l;
            if (arr[v] <= arr[t]) break;
            swap(v, t, arr);
            v = t;
        }
    }

    private static void siftUp(int v, int[] arr) {
        while (v > 1 && arr[v] < arr[v >> 1]) {
            swap(v, v >> 1, arr);
            v = v >> 1;
        }
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] += arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
