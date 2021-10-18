package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R5 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3232};
        System.out.println(subSets(arr));
    }

    private static List<List<Integer>> subSets(int[] arr) {
        List<List<Integer>> subSets = new ArrayList<>();
        int set = bitSet(arr), bitSubSet = 0;
        do {
            subSets.add(getSubSetFromBitSet(bitSubSet, arr));
        } while((bitSubSet = (bitSubSet - set) & set) != 0);
        return subSets;
    }

    private static int bitSet(int[] arr) {
        int set = 0, len = arr.length;
        for (int i = 0; i < len; i += 1) {
            set |= (1 << i);
        }
        return set;
    }

    private static List<Integer> getSubSetFromBitSet(int bitSubSet, int[] arr) {
        List<Integer> subSet = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i += 1) {
            if ((bitSubSet & (1 << i)) != 0) subSet.add(arr[i]);
        }
        return subSet;
    }
}
