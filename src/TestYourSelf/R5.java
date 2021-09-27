package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R5 {
    public static void main(String[] args) {
        int n = 12321;
        System.out.println(allSubsets(n));
        System.out.println(allSubsets(n).size());
        System.out.println(getEltInSet(n));
    }

    private static List<List<Integer>> allSubsets(int set) {
        List<List<Integer>> subsetsList = new ArrayList<>();
        int b = 0;
        do {
            subsetsList.add(getEltInSet(b));
        } while ((b = (b - set) & set) != 0);
        return subsetsList;
    }

    private static List<Integer> getEltInSet(int set) {
        List<Integer> elts = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i += 1) {
            if ((set & (1 << i)) != 0) elts.add(i);
        }
        return elts;
    }
}
