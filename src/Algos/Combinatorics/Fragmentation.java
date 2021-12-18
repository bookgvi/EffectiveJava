package Algos.Combinatorics;

import java.util.*;

public class Fragmentation {
    private static int[] nextFragment(int[] arr) {
        int len = arr.length;
        int off = len - 1;
        while (arr[off] == 0) off -= 1;
        int s = Math.max(off - 1, 0), index = len - 1;
        while (s > 0 && arr[s - 1] <= arr[s]) {
            s -= 1;
        }
        arr[s] += 1;
        while (arr[index] == 0) index -= 1;
        arr[index] -= 1;
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1};
        System.out.println(Arrays.toString(arr));
        while (arr[0] != arr.length) {
            arr = nextFragment(arr);
            System.out.println(Arrays.toString(arr));
        }
    }
}
