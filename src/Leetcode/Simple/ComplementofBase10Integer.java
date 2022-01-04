package Leetcode.Simple;

/*
* https://leetcode.com/problems/complement-of-base-10-integer/
* */
public class ComplementofBase10Integer {
    private static String decToBin(int n) {
        StringBuilder bin = new StringBuilder();
        int hasOne = 0;
        for (int i = 31; i >= 0; i -= 1) {
            if ((n & (1 << i)) > 0) {
                bin.append(0);
                hasOne = 1;
            }
            else if (hasOne == 1) bin.append(1);
        }

        return bin.length() > 0 ? bin.toString() : "1";
    }

    public static int bitwiseComplement(int n) {
        String bin = decToBin(n);
        return Integer.parseInt(bin, 2);
    }

    public static void main(String[] args) {
        int n = 2;
        int complement = bitwiseComplement(n);
    }
}
