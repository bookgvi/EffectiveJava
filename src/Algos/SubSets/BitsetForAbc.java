package Algos.SubSets;

import java.util.*;
import java.util.stream.*;

public class BitsetForAbc {
    private static final String firstChar = "a";
    private static final int firstCharByte = firstChar.getBytes()[0];

    private static int getSet(String str) {
        int set = 0;
        for (int i = 0, len = str.length(); i < len; i += 1) {
            int code = str.charAt(i) - firstCharByte;
            set |= (1 << code);
        }
        return set;
    }

    private static int intersect(int set1, int set2) {
        return set1 & set2;
    }

    public static void main(String[] args) {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};

        List<Integer> result = IntStream.range(0, puzzles.length).mapToObj(i -> 0).collect(Collectors.toList());
        for (int w = 0, wLen = words.length; w < wLen; w += 1) {
            int wSet = getSet(words[w]);
            for (int p = 0, pLen = puzzles.length; p < pLen; p += 1) {
                int pSet = getSet(puzzles[p]), interset = intersect(wSet, pSet);
                int fpSet = 1 << (puzzles[p].charAt(0) - firstCharByte);
                int firstLetter = intersect(wSet, fpSet);
                if (interset == wSet && firstLetter == fpSet) result.set(p, result.get(p) + 1);
            }
        }
    }
}
