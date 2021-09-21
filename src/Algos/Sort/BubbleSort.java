package Algos.Sort;

import java.util.Arrays;

public class BubbleSort extends AbstractSort {

    @Override
    public int[] sort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 0; i < arr.length; i += 1) {
            for (int  j = 0; j < arr.length - 1; j += 1) {
                if (arr[j] > arr[j + 1]) swap(j, j + 1, arr);
            }
        }
        return arr;
    }
}
