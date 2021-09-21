package Algos.Sort;

import java.util.Arrays;

public class InsertSort extends AbstractSort {

    @Override
    public int[] sort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int  i = 0; i < arr.length; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j -= 1) {
                swap(j - 1, j, arr);
            }
        }
        return arr;
    }
}
