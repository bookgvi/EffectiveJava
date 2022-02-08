package Structures.BinaryHeap;

import java.util.Arrays;

public class BinHeap {
    private static void swap(int i, int j, int[] arr) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void siftUp(int[] heap, int v) {
        while (v > 1 && heap[v] < heap[v >> 1]) {
            swap(v, v >> 1, heap);
            v >>= 1;
        }
    }

    private static void siftDown(int[] heap, int v) {
        int len = heap.length, half = len >> 1;
        while (v < half) {
            int l = (v << 1) + 1;
            int r = l + 1;
            int tmp = r < len && heap[r] < heap[l] ? r : l;
            if (heap[v] <= heap[tmp]) break;
            swap(v, tmp, heap);
            v = tmp;
        }
    }
    
    private static int[] build(int[] arr) {
        int len = arr.length;
        int[] heap = Arrays.copyOf(arr, len);
        for (int i = len >> 1; i >= 0; i -= 1)
            siftDown(heap, i);
        return heap;
    }

    public static void main(String[] args) {
        int[] arr = {2,5,3,12,24,9,1,2,2,5};
        int[] heap = build(arr); // 2, 2, 1, 2, 24, 9, 3, 12, 5
    }
}
