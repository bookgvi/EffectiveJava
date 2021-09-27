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
        int maxValue = 10;
        int size = 13;
        Supplier<int[]> arr = () -> fillArr(maxValue, size);
        System.out.println(Arrays.toString(bubbleSort(arr.get())));
        System.out.println(Arrays.toString(selectSort(arr.get())));
        System.out.println(Arrays.toString(insertSort(arr.get())));
        System.out.println(Arrays.toString(countSort(arr.get(), maxValue)));

        int findMe = 8; //ThreadLocalRandom.current().nextInt(maxValue);
        int[] tmpArr = {0, 1, 1, 3, 4, 4, 5, 6, 7, 8, 8, 8, 9}; // arr.get();
        System.out.printf("\n%d in %s, index == %d\n", findMe, Arrays.toString(countSort(tmpArr, maxValue, -1)), binSearch(findMe, tmpArr));
    }

    private static int binSearch(int n, int[] arr) {
        int len = arr.length;
        int l = 0;
        int r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr[mid] == n) return mid;
            else if (arr[mid] < n) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    private static int[] bubbleSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            for (int j = 0; j < len - 1; j += 1) {
                if (arr[j] - arr[j + 1] > 0) swap(j, j + 1, arr);
            }
        }
        return arr;
    }

    private static int[] selectSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            for (int j = i + 1; j < len; j += 1) {
                if (arr[i] - arr[j] > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static int[] insertSort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] - arr[j] > 0; j -= 1) {
                swap(j - 1, j, arr);
            }
        }
        return arr;
    }

    public static int[] countSort(int[] arr, int maxValue) {
        int[] counts = new int[maxValue];
        for (int elt : arr) counts[elt] += 1;
        int  k = 0;
        for (int i = 0; i < maxValue; i += 1) {
            while (counts[i]-- > 0) arr[k++] = i;
        }
        return arr;
    }

    /**
     * direction == 1 - from low to high;
     * direction == -1 - from high to low;
     * */
    private static int[] countSort(int[] arr, int maxValue, int direction) {
        int[] counts = new int[maxValue];
        for (int elt : arr) counts[elt] += 1;
        int k = 0;
        if (direction == -1) {
            for (int i = maxValue - 1; i >= 0; i -= 1) {
                while (counts[i]-- > 0) arr[k++] = i;
            }
        } else if (direction == 1) {
            for (int i = 0; i < maxValue; i += 1) {
                while (counts[i]-- > 0) arr[k++] = i;
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
        IntStream.range(0, size).forEach(i -> arr[i] = ThreadLocalRandom.current().nextInt(maxValue));
        return arr;
    }
}
