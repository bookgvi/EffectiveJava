package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class R1 {
    public static final int k = (int) 1e3 + 5;
    public static final int mod = (int) 1e9 + 7;
    public static final String str = "1012101";
    public static final String firstCh = "0";
    public static final byte firstChByte = firstCh.getBytes()[0];
//    public static final long[] pows = pows();
//    public static final long[] prefixHashes = prefixHashes(str);

    public static void main(String[] args) {
        int max = 10;
        int maxValue = 15;
        Supplier<List<Integer>> arr = () -> fillArr(max, maxValue);
        System.out.println(bubbleSort(arr.get()));
        System.out.println(selectSort(arr.get()));
        System.out.println(insertSort(arr.get()));
        System.out.printf("LSB: %s\n", lsbSort(arr.get()));
        List<Integer> sortArr = countSort(maxValue, arr.get());
        int n = 13;
        System.out.println();
        System.out.println(n);
        System.out.println(sortArr);
        System.out.println(binarySearchL(n, sortArr));
        System.out.println(binarySearchR(n, sortArr));
    }

    private static int binarySearchR(int n, List<Integer> sortArr) {
        int len = sortArr.size();
        int k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && sortArr.get(i + k) <= n) k += i;
        }
        if (sortArr.get(k) == n) return k;
        return -1;
    }

    private static int binarySearchL(int n, List<Integer> sortArr) {
        int len = sortArr.size();
        int k = len - 1;
        for (int i = len / 2; i > 0; i /= 2) {
            while (k - i >= 0 && sortArr.get(k - i) >= n) k -= i;
        }
        if (sortArr.get(k) == n) return k;
        return -1;
    }

    private static List<Integer> lsbSort(List<Integer> arr) {
        int SIZE = 1 << Integer.BYTES * 8 / 2;
        List<List<Integer>> digits = new ArrayList<>();
        List<Integer> sortArr = List.copyOf(arr);
        IntStream.range(0, SIZE).forEach(i -> digits.add(new ArrayList<>()));

        for (Integer elt : sortArr) {
            int index = elt & SIZE - 1;
            digits.get(index).add(elt);
        }

        sortArr = digits.stream().flatMap(Collection::stream).collect(Collectors.toList());
        IntStream.range(0, SIZE).forEach(i -> digits.set(i, new ArrayList<>()));
        for (Integer elt : sortArr) {
            int index = elt >>> Integer.BYTES * 8 / 2;;
            digits.get(index).add(elt);
        }

        sortArr = digits.stream().flatMap(Collection::stream).collect(Collectors.toList());
        return sortArr;
    }

    private static List<Integer> countSort(int maxVaule, List<Integer> arr) {
        int[] count = new int[maxVaule];
        for (int elt : arr) count[elt] += 1;
        int k = 0;
        for (int i = 0; i < maxVaule; i += 1) {
            while (count[i]-- > 0) arr.set(k++, i);
        }
        return arr;
    }

    private static List<Integer> insertSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1)
                swap(j, j - 1, arr);
        }
        return arr;
    }

    private static List<Integer> selectSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len - 1; i += 1) {
            for (int j = i + 1; j < len; j += 1) {
                if (arr.get(i) - arr.get(j) > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> bubbleSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = 0; j < len - 1; j += 1) {
                if (arr.get(j) - arr.get(j + 1) > 0) swap(j, j + 1, arr);
            }
        }
        return arr;
    }

    private static void swap(int i, int j, List<Integer> arr) {
        arr.set(i, arr.get(i) + arr.get(j));
        arr.set(j, arr.get(i) - arr.get(j));
        arr.set(i, arr.get(i) - arr.get(j));
    }

    private static List<Integer> fillArr(int size, int maxValue) {
        return IntStream.range(0, size).mapToObj(index -> ThreadLocalRandom.current().nextInt(maxValue)).collect(Collectors.toList());
    }
}