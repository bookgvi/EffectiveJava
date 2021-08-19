package Algos;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    private final static List<Integer> permutation = new ArrayList<>();
    private final static List<List<Integer>> permutationsList = new ArrayList<>();
    private final static List<Integer> list = List.of(0, 1, 2);
    private static final List<Boolean> isPresent = new ArrayList<>(List.of(false, false, false));

    public static void main(String[] args) {
        search();
        System.out.printf("Кол-во множеств = %d\n %s", permutationsList.size(), permutationsList);
    }

    private static void search() {
        if (permutation.size() == list.size()) {
            permutationsList.add(List.copyOf(permutation));
        }
        for (int i = 0; i < list.size(); i++) {
            if (isPresent.get(i)) continue;
            isPresent.set(i, true);
            permutation.add(i);
            search();
            isPresent.set(i, false);
            permutation.remove(permutation.size() - 1);
        }
    }

}
