package Algos.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadixSort {
    public List<Integer> sort(int[] arr) {
        final int SIZE = 1 << Integer.BYTES * 8 / 2;
        List<Integer> sortArr = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<List<Integer>> digits = getClearList(SIZE);

        for (int elt : sortArr) {
            int index = elt & SIZE - 1;
            List<Integer> elts = digits.get(index);
            elts.add(elt);
            digits.set(index, elts);
        }
        sortArr = getSortedArray(digits);

        digits = getClearList(SIZE);
        for (int elt : sortArr) {
            int index = elt >>> Integer.BYTES * 8 / 2;
            List<Integer> elts = digits.get(index);
            elts.add(elt);
            digits.set(index, elts);
        }

        return getSortedArray(digits);
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
