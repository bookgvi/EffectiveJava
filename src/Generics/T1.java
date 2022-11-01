package Generics;

import java.util.Set;

public class T1 {
    private static final Set<Integer> s1 = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final Set<Integer> s2 = Set.of(1, 3, 7, 9, 13, 11);

    private static int interceptionCount(Set<?> s1, Set<?> s2) {
        int counter = 0;
        for (Object elt : s1) {
            counter = s2.contains(elt) ? counter + 1 : counter;
        }
        return counter;
    }

    public static void main(String[] args) {
        int count = interceptionCount(s1, s2);
        System.out.println(count);
    }
}
