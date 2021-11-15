package Algos.Sort;

import java.util.*;
import java.util.stream.*;

public class LexicographicString {
    private static int comparator(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        for (int i = 0; i < len; i += 1) {
            int ii = str1.charAt(i) - str2.charAt(i);
            if (ii != 0) return ii;
        }
        return str1.length() - str2.length();
    }

    private static void swap(int i, int j, String[] arr) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void insertSort(String[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            for (int j = i; j > 0 && comparator(arr[j - 1], arr[j]) > 0; j -= 1)
                swap(j - 1, j, arr);
        }
    }

    private static void mergeSort(String[] arr) {
        for (int i = 1, len = arr.length; i < len; i <<= 1)
            for (int j = 0; j < len - i; j += i << 1)
                merge(j, i + j, Math.min(len, j + (i << 1)), arr);
    }

    private static void merge(int l, int mid, int r, String[] arr) {
        int it1 = 0, it2 = 0;
        String[] merge = new String[r - l];
        while (l + it1 < mid && mid + it2 < r) {
            if (comparator(arr[l + it1], arr[mid + it2]) < 0) {
                merge[it1 + it2] = arr[l + it1];
                it1 += 1;
            } else {
                merge[it1 + it2] = arr[mid + it2];
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge[it1 + it2] = arr[l + it1];
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge[it1 + it2] = arr[mid + it2];
            it2 += 1;
        }
        System.arraycopy(merge, 0, arr, l, it1 + it2);
    }

    private static void sort(String[] str) {
        if (str.length < 7) insertSort(str);
        else mergeSort(str);
    }

    private static int getSet(String str) {
        int set = 0;
        for (int i = 0, len = str.length(); i < len; i += 1)
            set |= 1 << (str.charAt(i) - "a".getBytes()[0]);
        return set;
    }

    private static String getElt(int set) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 32; i += 1)
            if ((set & (1 << i)) != 0) str.append(Character.toString(i + "a".getBytes()[0]));
        return str.toString();
    }

    private static List<String> getSubsets(String str, int len) {
        int set = getSet(str);
        int b = 0;
        List<String> res = new ArrayList<>();
        do {
            String subSet = getElt(b);
            if (subSet.length() == 4) res.add(subSet);
        } while ((b = (b - set) & set) != 0);
        return res;
    }

    public static void main(String[] args) {
        String str =  "fbcltfou";
        List<String> res = getSubsets(str, 4);
        String[] strRes = new String[res.size()];
        IntStream.range(0, res.size()).forEach(i -> strRes[i] = res.get(i));
        sort(strRes);
        for (String s : strRes) System.out.println(s);
    }
}
