package TestYourSelf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class R1 {
    static int dWord = Integer.BYTES * 8;
    static int findThisSum = 1;
    static List<Integer> array = List.of(1, 3);

    public static void main(String[] args) {
        System.out.println(findSubArray(array));
    }

    public static List<List<Integer>> findSubArray(List<Integer> arr) {
        List<List<Integer>> subResult = new ArrayList<>();
        List<Map<Integer, List<Integer>>> subResult1 = new ArrayList<>();
        int bitSet = transformTobitSet(arr);
        int subSet = 0;
        do {
            Map<Integer, List<Integer>> hm = storeBitSet(subSet, dWord);
            List<Integer> findedList = hm.get(findThisSum);
            if (findedList != null) subResult.add(findedList);
            subResult1.add(hm);
        } while ((subSet = (subSet - bitSet) & bitSet) != 0);
        System.out.println(subResult1);
        return subResult;
    }

    private static int transformTobitSet(List<Integer> arr) {
        int set = 0;
        for (int elt : arr) {
            set |= (1 << elt);
        }
        return set;
    }

    private static Map<Integer, List<Integer>> storeBitSet(int bitSet, int size) {
        Map<Integer, List<Integer>> resultMap = new HashMap<>();
        List<Integer> arraySet = new ArrayList<>();
        for (Integer i = 0; i <= size; i++) {
            Integer sum = 0;
            if ((bitSet & (1 << i)) != 0) {
                arraySet.add(i);
                sum += i;
            }
            if (sum > 0) resultMap.put(sum, arraySet);
        }
        return resultMap;
    }



}