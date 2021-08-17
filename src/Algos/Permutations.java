package Algos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {
    private static final List<Integer> subset = new ArrayList<>();
    private static final List<List<Integer>> subsetsList = new ArrayList<>();
    private static final int SET_SIZE = 3;

    public static void main(String[] args) {
        search(1);
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
