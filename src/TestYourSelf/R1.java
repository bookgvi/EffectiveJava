package TestYourSelf;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class R1 {
    public static void main(String[] args) {
        final int maxValue = 100;
        final int size = 12;
        Supplier<int[]> arr = () -> fillArr(maxValue, size);
        System.out.printf("Bubble: %s\n", Arrays.toString(bubbleSort(arr.get())));
        System.out.printf("Count: %s\n", Arrays.toString(countingSort(arr.get(), maxValue)));
        System.out.printf("Select: %s\n", Arrays.toString(selectSort(arr.get())));
        System.out.printf("Inject: %s\n", Arrays.toString(insertSort(arr.get())));
    }

    private static int[] insertSort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j -= 1)
                swap(j - 1, j, arr);
        }
        return arr;
    }

    private static int[] selectSort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = i + 1; j < arr.length; j += 1) {
                if (arr[i] - arr[j] > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static int[] countingSort(int[] arrOrig, int maxValue) {
        int[] count = new int[maxValue];
        int[] arr = new int[arrOrig.length];
        for (int i : arrOrig) count[i] += 1;
        int k = 0;
        for (int i = 0; i < maxValue; i += 1) {
            while (count[i]-- > 0) arr[k++] = i;
        }
        return arr;
    }

    private static int[] bubbleSort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = 0; j + 1 < arr.length; j += 1) {
                if (arr[j + 1] < arr[j]) swap(j + 1, j, arr);
            }
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }

    private static int[] fillArr(int maxValue, int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i += 1) {
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        }
        return Arrays.copyOf(arr, arr.length);
    }
}