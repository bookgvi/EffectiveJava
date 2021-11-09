package Leetcode.Simple;

import java.util.*;
import java.util.stream.*;

/*
* With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
* 1) word contains the first letter of puzzle.
* 2) For each letter in word, that letter is in puzzle.
* For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
* invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
* Return an array answer, where answer[i] is the number of words in the given word list words that is valid with respect to the puzzle puzzles[i].
* https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/
*/
class NumberofValidWordsforEachPuzzle {
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

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> result = IntStream.range(0, puzzles.length).mapToObj(i -> 0).collect(Collectors.toList());
        int[] wordsSet = new int[words.length];
        int[] puzzlesSet = new int[puzzles.length];
        for (int i = 0, len = words.length; i < len; i += 1) wordsSet[i] = getSet(words[i]);
        for (int i = 0, len = puzzles.length; i < len; i += 1) puzzlesSet[i] = getSet(puzzles[i]);

        for (int p = 0, psLen = puzzlesSet.length; p < psLen; p += 1) {
            int pSet = puzzlesSet[p], fpSet = 1 << (puzzles[p].charAt(0) - firstCharByte);
            for (int w = 0, wLen = words.length; w < wLen; w += 1) {
                int intersect = intersect(wordsSet[w], pSet);
                int firstLetter = intersect(wordsSet[w], fpSet);
                if (intersect == wordsSet[w] && firstLetter == fpSet)
                    result.set(p, result.get(p) + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words = {"aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {"aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        List<Integer> res = findNumOfValidWords(words, puzzles);
    }
}