package Algos.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadixSort {
    public List<Integer> lsbSort(List<Integer> arr) {
        int SIZE = 1 << Integer.BYTES * 8 / 2;
        List<List<Integer>> digits = new ArrayList<>();
        IntStream.range(0, SIZE).forEach(i -> digits.add(new ArrayList<>()));
        int[] count = new int[SIZE];

        for (Integer elt : arr) {
            int index = elt & SIZE - 1;
            digits.get(index).add(elt);
        }

//        digits.stream().flatMap(Collection::stream).forEach(elt -> count[elt >> Integer.BYTES * 8 / 2] += 1);
        for (List<Integer> eltList : digits) {
            for (Integer elt : eltList) {
                int index = elt >> Integer.BYTES * 8 / 2;
                count[index] += 1;
            }
        }

        int k = 0;
        for (int i = 0; i < SIZE; i += 1) {
            while (count[i]-- > 0) arr.set(k++, i);
        }
        return arr;
    }

    public List<Integer> sort(List<Integer> arr) {
        final int SIZE = 1 << Integer.BYTES * 8 / 2;
//        List<Integer> sortArr = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<List<Integer>> digits = getClearList(SIZE);
        List<List<Integer>> digits2 = getClearList(SIZE);

        for (Integer elt : arr) {
            digits.get(elt & SIZE - 1).add(elt);
        }

        for (List<Integer> eltList : digits) {
            for (Integer elt : eltList) {
                digits2.get(elt >> Integer.BYTES * 8 - 1).add(elt);
            }
        }
        return getSortedArray(digits2);
    }

    private List<List<Integer>> getClearList(int size) {
        List<List<Integer>> digits = new ArrayList<>();
        IntStream.range(0, size).forEach(i -> digits.add(new ArrayList<>()));
        return digits;
    }

    private List<Integer> getSortedArray(List<List<Integer>> digits) {
        return digits.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }
}
