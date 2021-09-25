package TestYourSelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class R4 {

    public static void main(String[] args) {
        int maxValue = 100;
        int size = 20;
        Supplier<int[]> arr = () -> fillArr(maxValue, size);
        System.out.println(Arrays.toString(bubbleSort(arr.get())));
        System.out.println(Arrays.toString(selectSort(arr.get())));
        System.out.println(Arrays.toString(insertSort(arr.get())));
        System.out.println(Arrays.toString(countSort(arr.get(), maxValue)));
    }

    private static int[] countSort(int[] arr, int maxValue) {
        int[] count = new int[maxValue];
        int[] res = new int[arr.length];
        for (int elt : arr) count[elt] += 1;
        int k = 0;
        for (int i = 0; i < maxValue; i += 1) {
            while (count[i]-- > 0) res[k++] = i;
        }
        return res;
    }

    private static int[] insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] - arr[j] > 0; j -= 1) {
                swap(j, j - 1, arr);
            }
        }
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

    private static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = 0; j < arr.length - 1; j += 1) {
                if (arr[j] - arr[j + 1] > 0) swap(j, j + 1, arr);
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
        IntStream.range(0, size).forEach(i -> {
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        });
        return arr;
    }
}
