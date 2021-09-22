package TestYourSelf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class R1 {
    public static final int maxValue = (int) 1e3 + 5;

    public static void main(String[] args) {
        final int size = 25;
        Supplier<int[]> arr = () -> fillArr(size);
        System.out.println(Arrays.toString(countingSort(arr.get())));
        System.out.println(Arrays.toString(selectSort(arr.get())));
        System.out.println(Arrays.toString(insertSort(arr.get())));
        System.out.println(Arrays.toString(bubbleSort(arr.get())));
    }

    private static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = 0; j < arr.length - 1; j += 1) {
                if (arr[j] - arr[j + 1] > 0) swap(j, j + 1, arr);
            }
        }
        return arr;
    }

    private static int[] insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j -= 1)
                swap(j, j - 1, arr);
        }
        return arr;
    }

    private static int[] countingSort(int[] arr) {
        int[] countArr = new int[maxValue];
        for (int elt : arr) countArr[elt] += 1;
        int k = 0;
        for (int i = 0; i < maxValue; i += 1)
            while (countArr[i]-- > 0) arr[k++] = i;
        return arr;
    }

    private static int[] selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = i + 1; j < arr.length; j += 1) {
                if (arr[i] - arr[j] > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
        ;
    }

    private static int[] fillArr(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i += 1) {
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        }
        return arr;
    }
}