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
        int maxValue = 30;
        Supplier<List<Integer>> arr = () -> fillArr(size, maxValue);
        System.out.printf("Bubble: %s\n", bubbleSort(arr.get()));
        System.out.printf("Select: %s\n", selectSort(arr.get()));
        System.out.printf("Insert: %s\n", insertSort(arr.get()));
        System.out.printf("LSD: %s\n", lsdSort(arr.get()));
        List<Integer> unArr = arr.get();
        System.out.println(unArr);
        System.out.printf("radix: %s\n", radixSort(unArr));

        int n = ThreadLocalRandom.current().nextInt(maxValue);
        List<Integer> sortedArr = lsdSort(arr.get());
        System.out.printf("\n%s   %d -> %d\n", sortedArr, n, binSearch(n, sortedArr));
        int lN = binSearchL(n, sortedArr), rN = binSearchR(n, sortedArr);
        System.out.printf("\n%s   %d -> [%d, %d]\n", sortedArr, n, lN, rN);
    }

    private static int binSearch(int n, List<Integer> arr) {
        int len = arr.size(), l = 0, r = len + 1, mid;
        while(r - l > 1) {
            mid = (r + l) >> 1;
            if (arr.get(mid) == n) return mid;
            else if (arr.get(mid) < n) l = mid;
            else r = mid;
        }
        return -1;
    }

    private static int binSearchR(int n, List<Integer> arr) {
        int len = arr.size(), k = 0;
        for(int i = len >> 1; i > 0; i >>= 1) {
            while(k + i < len && arr.get(i + k) <= n) k += i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static int binSearchL(int n, List<Integer> arr) {
        int len = arr.size(), k = len - 1;
        for(int i = len >> 1; i > 0; i >>= 1) {
            while(k - i >=0 && arr.get(k - i) >= n) k -= i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static List<Integer> bubbleSort(List<Integer> arr) {
        for(int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = 1; j < len; j += 1) {
                if (arr.get(j - 1) - arr.get(j) > 0) swap(j - 1, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> selectSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i + 1; j < len; j += 1) {
                if(arr.get(i) - arr.get(j) > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> insertSort(List<Integer> arr) {
        for(int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1) {
                swap(j - 1, j, arr);
            }
        }
        return arr;
    }

    private static List<Integer> lsdSort(List<Integer> arr) {
        int max = 1 << 16;
        List<List<Integer>> digits = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        List<List<Integer>> digits2 = IntStream.range(0, max).mapToObj(i -> new ArrayList<Integer>()).collect(Collectors.toList());
        for (int elt : arr) {
            digits.get(elt % max).add(elt);
        }
        for(List<Integer> eltList : digits) {
            for(int elt : eltList) {
                digits2.get(elt / max).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<Integer> radixSort(List<Integer> arr) {
        int b = 8, dw = Integer.BYTES, len = arr.size();
        int[] t = new int[len];
        for (int p = 0;  p < dw; p += 1) {
            int[] count = new int[1 << b];
            for(int elt : arr) {
                count[((elt ^ Integer.MIN_VALUE) >>> (b * p)) & ((1 << b) - 1)] += 1;
            }
            for (int i = 1; i < 1 << b; i += 1) {
                count[i] += count[i - 1];
            }
            for(int i = len - 1; i >= 0; i -= 1) {
                t[--count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr.get(i);
            }
            IntStream.range(0, len).forEach(i -> arr.set(i, t[i]));
        }
        return arr;
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