package Algos.Sort;

import java.util.*;
import java.util.stream.*;

public class LexicographicString {
    private static int comparator(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length(), len = Math.min(len1, len2);
        for (int i = 0; i < len; i += 1) {
            int ii = str1.charAt(i) - str2.charAt(i);
            if (ii != 0) return ii;
        }
        return len1 - len2;
    }

    private static List<String> sort(List<String> arr) {
        return arr.size() < 10 ? insertSort(arr) : mergeSort(arr);
    }

    private static List<String> insertSort(List<String> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1)
            for (int j = i; j > 0 && comparator(arr.get(j - 1), arr.get(j)) > 0; j -= 1)
                swap(j - 1, j, arr);
        return arr;
    }

    private static List<String> mergeSort(List<String> arr) {
        for (int i = 1, len = arr.size(); i < len; i <<= 1)
            for (int j = 0; j < len - i; j += i << 1)
                merge(j, j + i, Math.min(len, j + (i << 1)), arr);
        return arr;
    }

    private static void merge(int l, int mid, int r, List<String> arr) {
        int it1 = 0, it2 = 0;
        String[] merge = new String[r - l];
        while (l + it1 < mid && mid + it2 < r) {
            if (comparator(arr.get(l + it1), arr.get(mid + it2)) < 0) {
                merge[it1 + it2] = arr.get(l + it1);
                it1 += 1;
            } else {
                merge[it1 + it2] = arr.get(mid + it2);
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge[it1 + it2] = arr.get(l + it1);
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge[it1 + it2] = arr.get(mid + it2);
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1)
            arr.set(i + l, merge[i]);
    }

    private static void swap(int i, int j, List<String> arr) {
        String t = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, t);
    }

    private static List<String> getSubsets(String str, int len) {
        List<String> res = new ArrayList<>();
        int set = getSet(str);
        int b = 0;
        do {
            String ss = getStrFromSubSet(b);
            if (ss.length() == len) res.add(ss);
        } while((b = (b - set) & set) != 0);
        return res;
    }

    private static int getSet(String str) {
        int set = 0;
        for (int i = 0, len = str.length(); i < len; i += 1)
            set |= (1 << (str.charAt(i) - "a".getBytes()[0]));
        return set;
    }

    private static String getStrFromSubSet(int subSet) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i += 1)
            if ((subSet & (1 << i)) != 0) res.append(Character.toString(i + "a".getBytes()[0]));
        return res.toString();
    }

    public static void main(String[] args) {
        String str = "fbclusd";
        List<String> res = getSubsets(str, 4);
        sort(res);
        for (String s : res) System.out.println(s);
    }
}
