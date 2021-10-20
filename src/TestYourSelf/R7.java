package TestYourSelf;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.*;
import java.util.concurrent.ThreadLocalRandom;

public class R7 {
    public static void main(String[] args) {
        int maxValue = 15;
        int n = ThreadLocalRandom.current().nextInt(maxValue);
        Supplier<List<Integer>> arr = () -> fillArr(n, maxValue);
        List<Integer> sortedArr = lsdSort(arr.get());
        System.out.printf("\n%s   %d -> %d\n", sortedArr, n, binSearch(n, sortedArr));
        int lN = binSearchL(n, sortedArr), rN = binSearchR(n, sortedArr);
        System.out.printf("\n%s   %d -> [%d, %d]\n", sortedArr, n, lN, rN);
    }

    private static int binSearch(int n, List<Integer> arr) {
        int len = arr.size(), l = 0, r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) >> 1;
            if (arr.get(mid) == n) return mid;
            else if (arr.get(mid) < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static int binSearchL(int n, List<Integer> arr) {
        int len = arr.size(), k = len - 1;
        for (int i = len >> 1; i > 0; i >>= 1) {
            while (k - i >= 0 && arr.get(k - i) >= n) k -= i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static int binSearchR(int n, List<Integer> arr) {
        int len = arr.size(), k = 0;
        for (int i = len >> 1; i > 0; i >>= 1) {
            while (k + i < len && arr.get(k + i) <= n) k += i;
        }
        if (arr.get(k) == n) return k;
        return -1;

    }

    private static List<Integer> lsdSort(List<Integer> arr) {
        int max = 1 << 16;
        List<List<Integer>> digits = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        List<List<Integer>> digits2 = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        for (int elt : arr) {
            digits.get(elt % max).add(elt);
        }
        for (List<Integer> eltList : digits) {
            for (int elt : eltList) {
                digits2.get(elt / max).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<Integer> fillArr(int size, int maxValue) {
        return IntStream.range(0, size).mapToObj(i -> ThreadLocalRandom.current().nextInt(maxValue)).collect(Collectors.toList());
    }
}