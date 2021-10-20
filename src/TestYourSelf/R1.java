package TestYourSelf;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class R1 {
    public static void main(String[] args) {
        int size = 1;
        int maxValue = 30;
        Supplier<List<Integer>> arr = () -> fillArr(size, maxValue);
        System.out.printf("Bubble: %s\n", bubbleSort(arr.get()));
        System.out.printf("Select: %s\n", selectSort(arr.get()));
        System.out.printf("Insert: %s\n", insertSort(arr.get()));
        System.out.printf("LSD: %s\n", lsdSort(arr.get()));
        System.out.printf("radix: %s\n", radixSort(arr.get()));
        List<Integer> unArr = arr.get();
        System.out.println(unArr);
        System.out.printf("Merge: %s\n", mergeSort(unArr));

    }

    private static List<Integer> bubbleSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = 1; j < len; j += 1) {
                if (arr.get(j - 1) - arr.get(j) > 0) swap(j - 1, j, arr);
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
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1)
                swap(j - 1, j, arr);
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
        for (List<Integer> eltList : digits) {
            for (int elt : eltList) {
                digits2.get(elt / max).add(elt);
            }
        }
        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static List<Integer> radixSort(List<Integer> arr) {
        int b = 8, dw = Integer.BYTES, len = arr.size();
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr.get(i);
            IntStream.range(0, len).forEach(i -> arr.set(i, t[i]));
        }
        return arr;
    }

    private static List<Integer> mergeSort(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        if (arr.size() == 1) return arr;
        for (int i = 1, len = arr.size(); i < len; i <<= 1) {
            for (int j = 0; j < len - i; j += i << 1) {
                res = merge(j, Math.min(j + i, len), Math.min(j + (i << 1), len), arr);
            }
        }
        return res;
    }

    private static List<Integer> merge(int l, int mid, int r, List<Integer> arr) {
        List<Integer> merge = IntStream.range(0, r - l).boxed().collect(Collectors.toList());
        int it1 = 0, it2 = 0;
        while (l + it1 < mid && mid + it2 < r) {
            if (arr.get(l + it1) < arr.get(mid + it2)) {
                merge.set(it1 + it2, arr.get(l + it1));
                it1 += 1;
            } else {
                merge.set(it1 + it2, arr.get(mid + it2));
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge.set(it1 + it2, arr.get(it1 + l));
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge.set(it1 + it2, arr.get(mid + it2));
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1) {
            arr.set(l + i, merge.get(i));
        }
        return merge;
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