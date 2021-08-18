package Algos;

import java.util.ArrayList;
import java.util.List;

public class BitSet {
    public static void main(String[] args) {
        int set1 = 14;
        displaySubSets(set1, "Set1");
        int set2 = SubSetBuilder.newBuilder().addElt(2).addElt(1).addElt(13).addElt(0).build();
        displaySubSets(set2, "Set2");
        int set1_set2 = unionSubSets(set1, set2);
        displaySubSets(set1_set2, "Union");
        int intersectionSet1Set2 = intersectSubSets(set1, set2);
        displaySubSets(intersectionSet1Set2, "Intersection");
    }

    public static List<Integer> storeSubSet(int set) {
        List<Integer> subSet = new ArrayList<>();
        for (int elt = 0; elt < 32; elt++) {
            if ((set & (1 << elt)) != 0) subSet.add(elt);
        }
        return  subSet;
    }

    public static void displaySubSets(int set, String msg) {
        StringBuilder elements = new StringBuilder().append(msg).append(": [");
        String delimiter = ", ";
        System.out.println();
        for (int elt = 31; elt >=0; elt--) {
            elements.append((set & (1 << elt)) != 0 ? elt + delimiter : "");
        }
        elements = elements.delete(elements.length() - 2, elements.length());
        System.out.printf("%s", elements.append("]"));
    }

    public static int unionSubSets(int s1, int s2) {
        return s1 | s2;
    }
    private static int intersectSubSets(int s1, int s2) {
        return s1 & s2;
    }

    private static class SubSetBuilder {
        private int subset = 0;

        private SubSetBuilder() {}

        static SubSetBuilder newBuilder() {
            return new SubSetBuilder();
        }

        SubSetBuilder addElt(int elt) {
            subset |= (1 << elt);
            return this;
        }

        int build() {
            return subset;
        }
    }
}
