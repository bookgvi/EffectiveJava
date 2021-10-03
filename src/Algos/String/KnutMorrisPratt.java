package Algos.String;

import java.util.*;

public class KnutMorrisPratt {
    private static final String str = "abcdabdefabooo";
    private static final String delimiter = "#";
    private static final String ss = "ab";

    public static void main(String[] args) {
        int[] z = zFunc(ss + delimiter + str);
        int[] pi = piFunc(ss + delimiter + str);
        List<Integer> indexes = searchIndexesForZ(ss, z);
        List<Integer> indexes2 = searchIndexesForPi(ss, pi);
        System.out.println(Arrays.toString(z));
        System.out.println(Arrays.toString(pi));
        System.out.println(str);
        System.out.println(ss);
        System.out.println(indexes);
        System.out.println(indexes2);
    }

    private static int[] piFunc(String str) {
        int len = str.length();
        int[] pi = new int[len];
        for (int i = 1; i < len; i += 1) {
            int k = pi[i - 1];
            while (k > 0 && str.charAt(k) != str.charAt(i)) k = pi[k - 1];
            if (str.charAt(k) == str.charAt(i)) pi[i] = k + 1;
        }
        return pi;
    }

    private static int[] zFunc(String str) {
        int len = str.length();
        int[] z = new int[len];
        for (int i = 1, l = 0, r = 0; i < len; i += 1) {
            if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
            while (i + z[i] < len && str.charAt(i + z[i]) == str.charAt(z[i])) z[i] += 1;
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }
        return z;
    }

    private static int[] sort(int[] arr, int maxValue) {
        int[] count = new int[maxValue + 1];
        for (int elt : arr) count[elt] += 1;
        int k = 0;
        for (int i = 0; i <= maxValue; i += 1) {
            List<Integer> tmpIndexes = new ArrayList<>();
            while (count[i]-- > 0) arr[k++] = i;
        }
        return arr;
    }

    private static int binSearch(int n, int[] arr) {
        int len = arr.length;
        int r = len - 1;
        int l = 0;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr[mid] == n) return mid;
            if (arr[mid] < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static int binSearchL(int n, int[] arr) {
        int len = arr.length;
        int k = len - 1;
        for (int i = len / 2; i > 0; i /= 2) {
            while (k - i >= 0 && arr[k - i] >= n) k -= i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static int binSearchR(int n, int[] arr) {
        int len = arr.length;
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && arr[i + k] <= n) k += i;
        }
        if (arr[k] == n) return k;
        return -1;
    }

    private static List<Integer> searchIndexesForZ(String str, int[] arr) {
        int len = str.length();
        int arrLen = arr.length;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < arrLen; i += 1) {
            if ((arr[i] ^ len) == 0) indexes.add(i - (len + 1));
        }
        return indexes;
    }

    private static List<Integer> searchIndexesForPi(String str, int[] arr) {
        int len = str.length();
        int lenArr = arr.length;
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i< lenArr; i += 1) {
            if ((arr[i] ^ len) == 0) indexes.add(i - (2 * len));
        }
        return indexes;
    }
}
