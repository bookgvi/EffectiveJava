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
        int bitSet = getBitSet(arr);
        int bitSubSet = 0;
        do {
            List<Integer> subSet = getSubSetFromBitSet(bitSubSet, arr);
            if (subSet.size() == 1) subSets.add(subSet);
        } while((bitSubSet = (bitSubSet - bitSet) & bitSet) != 0);
        return subSets;
    }

    private static int getBitSet(int[] arr) {
        int set = 0;
        for (int i = 0, len = arr.length; i < len; i += 1) {
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
