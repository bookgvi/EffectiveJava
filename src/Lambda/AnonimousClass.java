package Lambda;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AnonimousClass {
    private static void sorter() {
        List<Integer> intList = new java.util.ArrayList<>(List.of(2, 4, 1, 3, 5, 9, 78, 4, 88));
        Collections.sort(intList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0, len = intList.size(); i < len; i += 1) {
            int elt = intList.get(i);
            System.out.println(elt);
        }
    }

    public static void main(String[] args) {
        sorter();
    }
}
