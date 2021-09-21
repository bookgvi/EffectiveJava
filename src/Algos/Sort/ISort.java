package Algos.Sort;

public interface ISort {
    int[] sort(int[] arr);

    default void swap(int a, int b, int[] arr) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}
