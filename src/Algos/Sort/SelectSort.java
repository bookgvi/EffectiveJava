package Algos.Sort;

import java.util.Arrays;

public class SelectSort extends AbstractSort {
    @Override
    public int[] sort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 0; i < arr.length; i += 1) {
            for (int j = i + 1; j < arr.length; j += 1) {
                if (arr[i] > arr[j]) swap(i, j, arr);
            }
        }
        return arr;
    }
}
