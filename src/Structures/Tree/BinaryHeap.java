package Structures.Tree;

import java.util.Arrays;

/*
 * Двоичное дерево на основе кучи
 * */
public class BinaryHeap {
    private static int minElt = Integer.MAX_VALUE;
    private static int maxElt = Integer.MIN_VALUE;

    private static void swap(int i, int j, int[] arr) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static void siftDown(int v, int[] arr) {
        int len = arr.length, half = len >> 1;
        while (v < half) {
            int l = (v << 1) + 1;
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
            v >>= 1;
        }
    }

    private static int[] insert(int value, int[] heap) {
        int len = heap.length;
        int[] newHeap = new int[len + 1];
        System.arraycopy(heap, 0, newHeap, 0, len);
        newHeap[len] = value;
        siftUp(len, newHeap);
        return newHeap;
    }

    private static int[] build(int[] arr) {
        int len = arr.length;
        int[] heap = Arrays.copyOf(arr, len);
        for (int i = len >> 1; i >= 0; i -= 1)
            siftDown(i, heap);
        return heap;
    }

    private static void symmOrder(int v, int[] arr) {
        int l = (v << 1) + 1;
        int r = l + 1;
        int len = arr.length;
        if (l < len) symmOrder(l, arr);
        System.out.printf("%d ", arr[v]);
        minElt = Math.min(arr[v], minElt);
        if (r < len) symmOrder(r, arr);
    }

    private static void preOrder(int v, int[] arr) {
        int l = (v << 1) + 1;
        int r = l + 1;
        int len = arr.length;
        System.out.printf("%d ", arr[v]);
        maxElt = Math.max(arr[v], maxElt);
        if (l < len) preOrder(l, arr);
        if (r < len) preOrder(r, arr);
    }

    private static void postOrder(int v, int[] arr) {
        int l = (v << 1) + 1;
        int r = l + 1;
        int len = arr.length;
        if (l < len) postOrder(l, arr);
        if (r < len) postOrder(r, arr);
        System.out.printf("%d ", arr[v]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 16, 11, 9, 10, 5, 6, 8, 1, 2, 4, 10, 4, 0};
//        int[] arr = new int[]{1, 16, 11};
        int[] heap = build(arr);
        int[] newHeap = insert(3, heap);
        System.out.println(Arrays.toString(heap));
        symmOrder(0, heap);
        System.out.println();
        preOrder(0, heap);
        System.out.println();
        postOrder(0, heap);
        System.out.printf("\nMin elt in tree is %d\n", minElt);
        System.out.printf("Max elt in tree is %d\n", maxElt);
    }
}
