package TestYourSelf;

import java.util.Arrays;
import java.util.PriorityQueue;

public class BinaryHeap {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 11, 9, 10, 5, 6, 8, 1, 2, 4, 10, 4, 0};
        System.out.println(Arrays.toString(arr));

        int[] heap = build(arr);
//        heap = insert(3, heap);
        arr = sort(arr);
        System.out.println(Arrays.toString(heap));
        System.out.println(Arrays.toString(arr));
    }

    private static int[] siftDown(int v, int[] heap) {
        int len = heap.length, half = len >> 1;
        while (v < half) {
            int l = (v << 1) + 1;
            int r = l + 1;
            int t = r < len && heap[r] < heap[l] ? r : l;
            if (heap[v] <= heap[t]) break;
            swap(v, t, heap);
            v = t;
        }
        return heap;
    }

    private static void siftUp(int v, int[] heap) {
        while(v > 1 && heap[v] < heap[v >> 1]) {
            swap(v, v >> 1, heap);
            v >>= 1;
        }
    }

    private static int[] build(int[] arr) {
        int len = arr.length;
        int[] heap = Arrays.copyOf(arr, len);
        for (int i = len - 1; i >= 0; i -= 1)
            siftDown(i, heap);
        return heap;
    }

    private static int[] sort(int[] arr) {
        int len = arr.length;
        int[] heap = build(arr), sorted = new int[len];
        for (int i = 0; i < len; i += 1) {
            sorted[i] = heap[0];
            heap[0] = Integer.MAX_VALUE;
            siftDown(0, heap);
        }
        return sorted;
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
