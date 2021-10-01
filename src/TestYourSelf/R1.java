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
        System.out.println(countSort(maxValue, arr.get()));

        List<Integer> sortArr = countSort(maxValue, arr.get());
        int findMe = ThreadLocalRandom.current().nextInt(maxValue);
        int index = binSearch(findMe, sortArr);
        System.out.printf("%s in %s, index = %d\n", findMe, sortArr, index);
        int indexL = binSearchL(findMe, sortArr);
        int indexR = binSearchR(findMe, sortArr);
        String range = Arrays.toString(IntStream.rangeClosed(indexL, indexR).boxed().toArray());
        System.out.printf("%s in %s, indexes = %s\n", findMe, sortArr, range);
    }

    private static int binSearchR(int n, List<Integer> arr) {
        int len = arr.size();
        int k = 0;
        for (int i = len / 2; i >= 1; i /= 2) {
            while (i + k < len && arr.get(i + k) <= n) k += i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static int binSearchL(int n, List<Integer> arr) {
        int len = arr.size();
        int k = len;
        for (int i = len / 2; i >= 1; i /= 2) {
            while (k - i >= 0 && arr.get(k - i) >= n) k -= i;
        }
        if (arr.get(k) == n) return k;
        return -1;
    }

    private static int binSearch(int n, List<Integer> arr) {
        int l = 0;
        int r = arr.size() - 1;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr.get(mid) == n) return mid;
            else if (arr.get(mid) < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static List<Integer> countSort(int maxValue, List<Integer> arr) {
        int[] countArr = new int[maxValue];
        for(int elt : arr) countArr[elt] += 1;
        int k = 0;
        for (int i = 0; i < maxValue; i += 1) {
            while(countArr[i]-- > 0) arr.set(k++, i);
        }
        return arr;
    }

    private static List<Integer> insertSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i; j > 0 && arr.get(j - 1) - arr.get(j) > 0; j -= 1) {
                swap(j, j - 1, arr);
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

    private static List<Integer> selectSort(List<Integer> arr) {
        for (int i = 0, len = arr.size(); i < len; i += 1) {
            for (int j = i + 1; j < len; j += 1) {
                if (arr.get(i) - arr.get(j) > 0) swap(i, j, arr);
            }
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
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