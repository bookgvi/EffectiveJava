package Leetcode.Simple;

import java.util.*;

/*
* https://leetcode.com/problems/sequential-digits/submissions/
* */
public class SequentialDigits {
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        int  mask = 0;
        int seq = 0;
        for (int i = 1, j = 1; i <= low; i *= 10, j += 1) {
            mask += i;
            seq = seq * 10 + j;
        }
        if (seq >= low && seq <= high) res.add(seq);
        while (seq <= high) {
            if (seq % 10 == 9) {
                mask = mask * 10 + 1;
                int tmpSeq = 0;
                for (int i = 1, j = 1; i <= seq * 10; i *= 10, j += 1)
                    tmpSeq = tmpSeq * 10 + j;
                seq = tmpSeq - mask;
            }
            seq += mask;
            if (seq >= low && seq <= high) res.add(seq);
        }
        return res;
    }

    public static void main(String[] args) {
        int low = 50, high = 100;
        List<Integer> res = sequentialDigits(low, high);
    }
}
