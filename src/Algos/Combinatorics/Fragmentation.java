package Algos.Combinatorics;

import java.util.*;

public class Fragmentation {
    private static int[] nextFragment(int[] arr, int n) {
        int len = arr.length, i;
        for (i = 0; i < len - 1; i += 1)
            if (arr[i] == 1) break;
        if (i == len - 1) {
            for (i = 0; i < len - 2; i += 1)
                if (arr[i] == arr[i + 1]) break;
        }
        int tmpInc = arr[i] + 1, sum = tmpInc;
        for (int j = 0; j < i; j += 1)
            sum += arr[j];
        int newLen = n - sum + i + 1;
        int[] newArr = new int[newLen];
        for (int j = 0; j < newLen; j += 1)
            if (j < i) newArr[j] = arr[j];
            else if (j == i) newArr[j] = tmpInc;
            else newArr[j] = 1;
        return newArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        int count = Arrays.stream(arr).reduce(0, Integer::sum);
        while (arr.length > 0) {
            System.out.println(Arrays.toString(arr));
            arr = nextFragment(arr, count);
        }
    }
}
