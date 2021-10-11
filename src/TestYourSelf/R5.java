package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R5 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3232};
        System.out.println(subSets(arr));
    }

    private static List<List<Integer>> subSets(int[] arr) {
        List<List<Integer>> allSubsets = new ArrayList<>();
        int binSet = getBinSet(arr);
        int b = 0;
        do {
            storeSubSets(allSubsets, b, arr);
        } while ((b = (b - binSet) & binSet) != 0);
        return allSubsets;
    }

    private static int getBinSet(int[] arr) {
        int set = 0;
        int index = arr.length;
        for (int i = 0; i < index; i += 1) {
            set |= (1 << i);
        }
        return set;
    }

    private static void storeSubSets(List<List<Integer>> allSubSets, int subSet, int[] arr) {
        List<Integer> subSetElts = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i += 1) {
            if ((subSet & (1 << i)) != 0) subSetElts.add(arr[i]);
        }
        allSubSets.add(subSetElts);
    }
}
