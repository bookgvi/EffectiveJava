package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Intersection {
    public static void main(String[] args) {
        int[] arr1 = {0, 1, 2, 3, 4};
        int[] arr2 = {1, 2, 3, 4, 1, 2, 3};
        sort(arr1);
        sort(arr2);
        List<Integer> set = set(arr1, arr2);
        System.out.println(set);
        List<Integer> l1 = Arrays.stream(arr2).boxed().collect(Collectors.toList());
        List<Integer> tArr = unique(l1);
        System.out.println(tArr);
    }

    private static List<Integer> unique(List<Integer> arr) {
        int b = 8, dw = 4;
        List<Integer> res = new ArrayList<>(arr);
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : res)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] = 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            int i = arr.size() - 1;
            while (i >= 0) {
                int pos = --count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)];
                if (pos == -1) {
                    res.remove(i);
                    arr.remove(i);
                    i -= 1;
                } else if (pos < -1) {
                    i -= 1;
                    continue;
                } else {
                    res.set(pos, arr.get(i));
                }
                i -= 1;
            }
            IntStream.range(0, res.size()).forEach(j -> arr.set(j, res.get(j)));
        }
        return res;
    }

    private static List<Integer> set(int[] arr1, int[] arr2) {
        return set(arr1, arr2, false);
    }

    private static List<Integer> set(int[] arr1, int[] arr2, boolean duplicate) {
        List<Integer> res = new ArrayList<>();
        for (int j : arr1) {
            if (binSearch(j, arr2) != -1) {
                res.add(j);
                if (duplicate) res.add(j);
            }
        }
        return res;
    }

    private static int binSearch(int n, int[] arr) {
        int len = arr.length, l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            if (arr[mid] == n) return mid;
            else if (arr[mid] > n) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    private static void sort(int[] arr) {
        int b = 8, dw = Integer.BYTES, len = arr.length;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
    }

    private static String toBinary(int n) {
        StringBuilder res = new StringBuilder();
        for (int i = 32; i >= 0; i -= 1) {
            if ((n & (1 << i)) != 0) res.append("1");
            else res.append("0");
        }
        return res.toString();
    }
}
