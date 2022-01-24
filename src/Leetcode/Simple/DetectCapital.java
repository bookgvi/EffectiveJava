package Leetcode.Simple;

/*
* https://leetcode.com/problems/detect-capital/submissions/
* */
public class DetectCapital {
    public static boolean detectCapitalUse(String word) {
        boolean res = false, isFirst = false;
        int lowerCaseCount = 0, upperCaseCount = 0, len = word.length();;
        for (int i = 0; i < len; i += 1) {
            if (word.charAt(i) < 'A' || word.charAt(i) > 'Z') {
                lowerCaseCount += 1;
                if (upperCaseCount > 1) break;
            }
            else upperCaseCount += 1;
            if (i == 0 && word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') isFirst = true;
        }
        if (isFirst) {
            if (lowerCaseCount == len - 1) res = true;
        }
        if (upperCaseCount > 1) res = false;
        return res || lowerCaseCount == len || upperCaseCount == len;
    }

    public static void main(String[] args) {
        String str = "aaaaaaaaQ";
        boolean res = detectCapitalUse(str);
    }
}
