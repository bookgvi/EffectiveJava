package TestYourSelf;

import java.util.ArrayList;
import java.util.List;

public class R2 {
    public static void main(String[] args) {
        int set = addElem(0, 15);
        set = addElem(set, 4);
        System.out.println(set);
        System.out.println(getElements(set));

    }

    private static int addElem(int set, int elem) {
        return set | 1 << elem;
    }

    private static List<Integer> getElements(int set) {
        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((set & (1 << i)) != 0) elements.add(i);
        }
        return elements;
    }
}
