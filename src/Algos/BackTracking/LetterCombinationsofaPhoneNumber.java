package Algos.BackTracking;

import java.util.*;

/*
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/submissions/
 * */
public class LetterCombinationsofaPhoneNumber {
    private static List<String> lettersComb;
    private static StringBuilder lettersString;

    public static void main(String[] args) {
        String digits = "22";
        List<String> combs = letterCombinations(digits);
    }

    public static List<String> letterCombinations(String digits) {
        lettersString = new StringBuilder();
        lettersComb = new ArrayList<>();
        if (digits.length() > 0) {

            Map<Character, String> phoneKeys = new HashMap<>();
            phoneKeys.put('1', "");
            phoneKeys.put('2', "abc");
            phoneKeys.put('3', "def");
            phoneKeys.put('4', "ghi");
            phoneKeys.put('5', "jkl");
            phoneKeys.put('6', "mno");
            phoneKeys.put('7', "pqrs");
            phoneKeys.put('8', "tuv");
            phoneKeys.put('9', "wxyz");
            phoneKeys.put('0', "");

            List<char[]> lettersList = new ArrayList<>();
            for (int i = 0, len = digits.length(); i < len; i += 1)
                lettersList.add(phoneKeys.get(digits.charAt(i)).toCharArray());

            backTrack(lettersList, 0, lettersList.size());
        }
        return lettersComb;
    }

    private static void backTrack(List<char[]> otherLetters, int arrPos, int digitsCount) {
        char[] letters = otherLetters.get(arrPos);
        for (char letter : letters) {
            place(letter);
            if (lettersString.length() == digitsCount) {
                lettersComb.add(lettersString.toString());
            } else
                backTrack(otherLetters, arrPos + 1, digitsCount);
            removeLast();
        }
    }

    private static void place(char ch1) {
        lettersString.append(ch1);
    }

    private static void removeLast() {
        int lettersLen = lettersString.length();
        lettersString.delete(lettersLen - 1, lettersLen);
    }
}
