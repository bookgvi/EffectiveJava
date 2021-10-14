package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class R1 {
    public static void main(String[] args) {
        int size = 10;
        int maxValue = 15;
        Supplier<List<Integer>> arr = () -> fillArr(size, maxValue);
        System.out.printf("Bubble: %s\n", bubbleSort(arr.get()));
        System.out.printf("Select: %s\n", selectSort(arr.get()));
        System.out.printf("Insert: %s\n", insertSort(arr.get()));
        System.out.printf("LSD: %s\n", lsdSort(arr.get()));

        int n = ThreadLocalRandom.current().nextInt(maxValue);
        List<Integer> sortedArr = lsdSort(arr.get());
        System.out.printf("\n%s   %d -> %d\n", sortedArr, n, binSearch(n, sortedArr));
        int lN = binSearchL(n, sortedArr), rN = binSearchR(n, sortedArr);
        System.out.printf("\n%s   %d -> [%d, %d]\n", sortedArr, n, lN, rN);
    }

    private static int binSearch(int n, List<Integer> arr) {
        int len = arr.size(), l = 0, r = len - 1;
        while (r - l > 1) {
            int mid = (r + l) / 2;
            if (arr.get(mid) == n) return mid;
            else if (arr.get(mid) < n) l = mid;
            else r = mid;
        }
        return -1;
    }

    private static int binSearchL(int n, List<Integer> arr) {
        int len = arr.size(), k = len - 1;
        for (int i = len / 2; i > 0; i /= 2) {
            while (k - i >= 0 && arr.get(k - i) >= n) k -= i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static int binSearchR(int n, List<Integer> arr) {
        int len = arr.size(), k = 0;
        for (int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && arr.get(i + k) <= n) k += i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static List<Integer> bubbleSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = 0; j < len - 1; j += 1) {
                if (arr.get(j) - arr.get(j + 1) > 0) swap(j, j + 1, arr);
            }
        }
        return arr;
    }

    private static List<Integer> selectSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i + 1; j < len; j += 1) {
                if (arr.get(i) - arr.get(j) > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> insertSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1) {
                swap(j - 1, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> lsdSort(List<Integer> arr) {
        int size = 1 << Integer.BYTES;
        List<List<Integer>> digits = IntStream.range(0, size).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        List<List<Integer>> digits2 = IntStream.range(0, size).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());

        for (Integer elt : arr) {
            digits.get(elt % size).add(elt);
        }
        for (List<Integer> eltList : digits) {
            for (Integer elt : eltList) {
                digits2.get(elt / size).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static void swap(int a, int b, List<Integer> arr) {
        arr.set(a, arr.get(a) + arr.get(b));
        arr.set(b, arr.get(a) - arr.get(b));
        arr.set(a, arr.get(a) - arr.get(b));
    }

    private static List<Integer> fillArr(int size, int maxValue) {
        return IntStream.range(0, size).mapToObj(i -> ThreadLocalRandom.current().nextInt(maxValue)).collect(Collectors.toList());
    }
}