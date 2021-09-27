package Leetcode.Simple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {
    public static void main(String[] args) {
        int lowBound = 6;
        int upperBound = 13;
        List<Integer> range = IntStream.range(lowBound + 1, upperBound).boxed().collect(Collectors.toList());
        int set = createSet(range);
        System.out.println(range);
        System.out.println(getSubSets(set).stream().filter(subset -> subset.size() == 6).collect(Collectors.toList()));
        System.out.println(getSubSets(set).size());
    }
    private static List<List<Integer>> getSubSets(int n) {
        List<List<Integer>> subSets = new ArrayList<>();
        int bitSet = 0;
        do {
            subSets.add(getElements(bitSet));
        } while ((bitSet = (bitSet - n) & n) != 0);
        return subSets;
    }

    private static int createSet(List<Integer> range) {
        int set = 0;
        for(int elt : range) {
            set |= 1 << elt;
        }
        return set;
    }

    private static List<Integer> getElements(int set) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i += 1) {
            if ((set & 1 << i) != 0) elements.add(i);
        }
        return elements;
    }
}
