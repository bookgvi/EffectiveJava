package Algos.Sort;

import java.util.*;
import java.util.stream.*;

public class LexicographicString {
    private static int comparator(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length(),
                len = Math.min(len1, len2);
        for (int i = 0; i < len; i += 1) {
            int i1 = str1.charAt(i) - str2.charAt(i);
            if (i1 != 0) return i1;
        }
        return len1 - len2;
    }

    private static List<String> insertSort(List<String> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1)
            for (int j = i; j > 0 && comparator(arr.get(j - 1), arr.get(j)) > 0; j -= 1)
                swap(j - 1, j, arr);
        return arr;
    }

    private static List<String> sort(List<String> arr) {
        return insertSort(arr);
    }

    private static int getSet(String str) {
        int set = 0;
        for (int i = 0, len = str.length(); i < len; i += 1)
            set |= 1 << (str.charAt(i) - "a".getBytes()[0]);
        return set;
    }

    private static String getString(int subset) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 32; i += 1)
            if ((subset & (1 << i)) != 0) res.append(Character.toString(i + "a".getBytes()[0]));
        return res.toString();
    }

    private static List<String> getSubsets(String str, int len) {
        List<String> res = new ArrayList<>();
        int set = getSet(str);
        int b = 0;
        do {
            String ss = getString(b);
            if (ss.length() == len) res.add(ss);
        } while((b = (b - set) & set) != 0);
        return res;
    }

    private static void swap(int i, int j, List<String> arr) {
        String tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public static void main(String[] args) {
        String str = "fbcltfousd";
        List<String> res = getSubsets(str, 4);
        sort(res);
        for (String s : res) System.out.println(s);
    }
}
