package Leetcode.Simple.CombinIter;

import java.util.ArrayList;
import java.util.List;

/*
* Design the CombinationIterator class:
* 1) CombinationIterator(string characters, int combinationLength) Initializes the object with a string characters
*               of sorted distinct lowercase English letters and a number combinationLength as arguments.
* 2) next() Returns the next combination of length combinationLength in lexicographical order.
* 3) hasNext() Returns true if and only if there exists a next combination.
*
* https://leetcode.com/problems/iterator-for-combination/
* */
public class CombinationIterator {
    private final int firstCharByte = "a".getBytes()[0];
    private final List<String> subSets;
    private int iterator = 0;

    public CombinationIterator(String characters, int combinationLength) {
        int set = set(characters);
        this.subSets = sort(subSets(set, combinationLength));
    }

    public String next() {
        String str = "";
        try {
            str = subSets.get(iterator);
            iterator += 1;
        } catch (Exception ignored) {
        }
        return str;
    }

    public boolean hasNext() {
        return iterator < subSets.size();
    }

    private int set(String str) {
        int set = 0;
        for (int i = 0, len = str.length(); i < len; i += 1) {
            set |= (1 << (str.charAt(i) - firstCharByte + 1));
        }
        return set;
    }

    private String getElt(int set) {
        StringBuilder elts = new StringBuilder();
        for (int i = 0; i < 32; i += 1) {
            if ((set & (1 << i)) != 0) elts.append(Character.toString(i + firstCharByte - 1));
        }
        return elts.toString();
    }

    private List<String> subSets(int set, int len) {
        List<String> subSets = new ArrayList<>();
        int b = 0;
        do {
            String elts = getElt(b);
            if (elts.length() == len) subSets.add(elts);
        } while ((b = (b - set) & set) != 0);
        return subSets;
    }

    private List<String> sort(List<String> strings) {
        strings.sort(String::compareTo);
        return strings;
    }

}
