package TestYourSelf;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;

public class R1 {
    static int inSum = 28;
    static int dWord = Integer.BYTES * 8;
    static List<Integer> array = List.of(1, 3, 7, 15, 2);

    public static void main(String[] args) {
        System.out.println(findSubArray(array));
    }

    public static List<List<Integer>> findSubArray(List<Integer> arr) {
        List<List<Integer>> subResult = new ArrayList<>();
        int bitSet = transformTobitSet(arr);
        subResult.add(storeBitSet(bitSet, dWord));
        subResult.clear();
        int subSet = 0;
        do {
            subResult.add(storeBitSet(subSet, dWord));
        } while ((subSet = (subSet - bitSet) & bitSet) != 0);
        return subResult;
    }

    private static int transformTobitSet(List<Integer> arr) {
        int set = 0;
        for (int elt : arr) {
            set |= (1 << elt);
        }
        return set;
    }

    private static List<Integer> storeBitSet(int bitSet, int size) {
        List<Integer> arraySet = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i <= size; i++) {
            int shift = 1 << i;
            int bit = bitSet & shift;
            if ((bitSet & (1 << i)) != 0) {
                arraySet.add(i);
                sum += i;
            }

        }
        return arraySet;
    }



}