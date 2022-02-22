package Leetcode.Simple;

/*
 * https://leetcode.com/problems/excel-sheet-column-number/submissions/
 * */
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        String t = "ZY";
        int res = titleToNumber(t);
    }

    public static int titleToNumber(String columnTitle) {
        int res = 0;
        for (int i = columnTitle.length() - 1, p = 0; i >= 0; i -= 1, p += 1) {
            int num = columnTitle.charAt(i) - 'A' + 1;
            res += num * pow(26, p);
        }
        return res;
    }

    private static int pow(int n, int pow) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res *= n;
            n *= n;
            pow >>= 1;
        }
        return res;
    }
}
