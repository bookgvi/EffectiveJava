package Algos;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    private static final List<Integer> subset = new ArrayList<>();
    private static final List<List<Integer>> subsetsList = new ArrayList<>();
    private static final int SET_SIZE = 3;

    public static void main(String[] args) {
        search(1);
        System.out.printf("Кол-во подмножеств = %d\n %s", subsetsList.size(), subsetsList);
    }

    private static void search(int k) {
        if (k > SET_SIZE) {
            subsetsList.add(new ArrayList<>(subset));
        } else {
            subset.add(k);
            search(++k);
            subset.remove(subset.size() - 1);
            search(k);
        }
    }
}
