package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R2 {
    public static void main(String[] args) {
        int SET = 0;
//        SET = addSElementIntoSet(SET, 0);
        SET = addSElementIntoSet(SET, 1);
        SET = addSElementIntoSet(SET, 2);
        SET = addSElementIntoSet(SET, 3);
        System.out.printf("Set %d contains %d elements: %s\n", SET, getElementsFromSet(SET).size(), getElementsFromSet(SET));
        System.out.printf("Set %d has %d subsets\n", SET, getSubSetsCount(SET));
        System.out.println(subSets(SET));
        subSets(SET).forEach(subSet -> System.out.println(getElementsFromSet(subSet)));
    }

    private static List<Integer> subSets(int set) {
        List<Integer> subSets = new ArrayList<>();
        int subSet = 0;
        do {
            subSets.add(subSet);
        } while ((subSet = (subSet - set) & set) != 0);
        return subSets;
    }

    private static int getSubSetsCount(int set) {
        int count = 0;
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((set & (1 << i)) != 0) count += 1;
        }
        return 1 << count;
    }

    private static int addSElementIntoSet(int set, int element) {
        if (element < 0) return set;
        return set | (1 << element);
    }

    private static List<Integer> getElementsFromSet(int set) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((set & (1 << i)) != 0) elements.add(i);
        }
        return elements;
    }
}
