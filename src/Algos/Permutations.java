package Algos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {
    private final static List<List<Integer>> permutationsList = new ArrayList<>();
    private final static List<Integer> list = List.of(0, 65535, 1024);
    private static final List<Boolean> isPresent = new ArrayList<>(List.of(false, false, false));

    public static void main(String[] args) {
        search();
        System.out.printf("Кол-во множеств = %d\n %s", permutationsList.size(), permutationsList);
    }

    private static void search() {
        int set = createBinSet(list.size());
        int subSet = 0;
        do {
            storeSubSet(subSet);
        } while ((subSet = (subSet - set) & set) != 0);
    }

    private static int createBinSet(int n) {
        int set = 0;
        for (int i = 0; i < n; i += 1) {
            set |= (1 << i);
        }
        return set;
    }

    private static void storeSubSet(int subSet) {
        List<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i += 1) {
            if ((subSet & (1 << i)) != 0) permutation.add(list.get(i));
        }
        permutationsList.add(permutation);
    }

//    private static void search1() {
//        if (permutation.size() == list.size()) {
//            permutationsList.add(List.copyOf(permutation));
//        }
//        for (int i = 0; i < list.size(); i++) {
//            if (isPresent.get(i)) continue;
//            isPresent.set(i, true);
//            permutation.add(i);
//            search();
//            isPresent.set(i, false);
//            permutation.remove(permutation.size() - 1);
//        }
//    }

}
