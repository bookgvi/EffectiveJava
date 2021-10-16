package Algos;

import java.util.*;
import java.util.stream.*;

public class Permutations {
    private final static List<List<Integer>> permutationsList = new ArrayList<>();
    private final static List<Integer> list = List.of(0, 65535, 1024);
    private static final List<Boolean> isPresent = new ArrayList<>(List.of(false, false, false));

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        System.out.println(arr);
        for (int i = 1; i < 24; i += 1) {
            nextPermutations(arr);
            System.out.println(arr);
        }

    }

    /*
     * Перестановки. Генерация всех перестановок. Нерекурсивный алгоритм
     *    Рассмотримнерекурсивный алгоритмгенерацииперестановоквлексикографическомпорядке.
     *    1. Последовательность элементов просматривается с конца до тех пор, пока не будет
     *        встречен первый элемент, такой что a[i]<a[i+1].
     *    2. В «хвосте» последовательности, состоящем из элементов, расположенных за найден-
     *        ным элементом, производим поиск минимального элемента min, большего, чем a[i].
     *    3. Меняем местами a[i] и найденный элемент min.
     *    4. Сортируем хвост последовательности.
     * Такой алгоритм позволяет получить все перестановки в лексикографическом порядке.
     * */
    private static List<Integer> nextPermutations(List<Integer> arr) {
        int len = arr.size();
        for (int i = len - 2; i >= 0; i -= 1) {
            if (arr.get(i) < arr.get(i + 1)) {
                int min = i + 1;
                for (int j = min; j < len; j += 1) {
                    if (arr.get(j) > arr.get(i) && arr.get(j) < arr.get(min)) {
                        min = j;
                    }
                }
                swap(i, min, arr);
                sort(i + 1, arr);
                return arr;
            }
        }
        return null;
    }

    private static void sort(int start, List<Integer> arr) {
        int b = 8, dw = Integer.BYTES, len = arr.size();
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int i = start; i < len; i += 1) {
                count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (b * p)) & ((1 << b) - 1)] += 1;
            }
            for (int i = 1; i < 1 << b; i += 1) {
                count[i] += count[i - 1];
            }
            for (int i = len - 1; i >= start; i -= 1) {
                int elt = arr.get(i);
                t[--count[((elt ^ Integer.MIN_VALUE) >>> (b * p)) & ((1 << b) - 1)]] = elt;
            }
            IntStream.range(0, len - start).forEach(i -> arr.set(i + start, t[i]));
        }
    }

    private static void swap(int i, int j, List<Integer> arr) {
        arr.set(i, arr.get(i) + arr.get(j));
        arr.set(j, arr.get(i) - arr.get(j));
        arr.set(i, arr.get(i) - arr.get(j));
    }
}
