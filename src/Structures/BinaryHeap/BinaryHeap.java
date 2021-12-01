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

//        heap = Arrays.copyOf(tmpHeap, DEFAULT_SIZE);
//        buildReverseHeap();
//        System.out.println(Arrays.toString(heap));
    }

    private static void siftDown(int v) {
        int len = heap.length, half = len >> 1;
        while (v < half) {
            int l = (v << 1) + 1;
            int r = l + 1;
            int t = r < len && heap[r] < heap[l] ? r : l;
            if (heap[v] <= heap[t]) break;
            swap(v, t);
            v = t;
        }
    }

    private static void buildHeap() {
        for (int i = heap.length; i > 0; i -= 1) {
            siftDown(i);
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
