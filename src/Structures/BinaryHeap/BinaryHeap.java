package Structures.BinaryHeap;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class BinaryHeap {
    private static final int DEFAULT_SIZE = 11;
    private static final int MAX = 10;
    private static int[] heap = new int[DEFAULT_SIZE];

    public static void main(String[] args) {
        int[] tmpHeap = fillArr();
        System.out.println(Arrays.toString(tmpHeap));

        heap = Arrays.copyOf(tmpHeap, DEFAULT_SIZE);
        buildHeap();
        System.out.println(Arrays.toString(heap));

        heap = Arrays.copyOf(tmpHeap, DEFAULT_SIZE);
        buildReverseHeap();
        System.out.println(Arrays.toString(heap));
    }

    private static void buildReverseHeap() {
        for (int i = 1; i <= DEFAULT_SIZE - 1; i += 1)
            siftUp(i);
    }

    private static void buildHeap() {
        for (int i = DEFAULT_SIZE; i > 0; i -= 1)
            siftDown(i);
    }

    private static void siftUp(int n) {
        while (n > 1 && heap[n] < heap[n >> 1]) {
            swap(n, n >> 1);
            n >>= 1;
        }
    }

    private static void siftDown(int n) {
        int len = heap.length;
        while (n << 1 <= len - 1) {
            int l = n << 1;
            int r = (n << 1) + 1;
            int t = r <= len - 1 && heap[r] < heap[l] ? r : l;
            if (heap[n] <= heap[t]) break;
            swap(n, t);
            n = t;
        }
    }

    private static void swap(int i, int j) {
        heap[i] += heap[j];
        heap[j] = heap[i] - heap[j];
        heap[i] = heap[i] - heap[j];
    }

    private static int[] fillArr() {
        int[] tmpArr = new int[DEFAULT_SIZE];
        IntStream.range(1, DEFAULT_SIZE).forEach(i -> tmpArr[i] = ThreadLocalRandom.current().nextInt(MAX));
        return tmpArr;
    }
}
