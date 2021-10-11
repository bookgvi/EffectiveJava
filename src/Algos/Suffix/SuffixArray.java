package Algos.Suffix;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SuffixArray {
    private static final String str = "abracadabra";
//    private static final String str = "abaab";
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(lexicoGraphikSuffixSort(str)));
    }

    private static String[] lexicoGraphikSuffixSort(String str) {
        final int ALPHABET_SIZE = 31;
        String[] res = new String[str.length()];
        int[] count = new int[ALPHABET_SIZE], p = new int[ALPHABET_SIZE];
        byte[] strBytes = str.getBytes();
        for (byte chByte : strBytes) {
            int charCode = chByte - firstCharByte + 1;
            count[charCode] += 1;
        }
        for (int i = 1; i < ALPHABET_SIZE; i += 1) {
            count[i] += count[i - 1];
        }
        int i = 0;
        for (byte chByte : strBytes) {
            int charCode = chByte - firstCharByte + 1;
            p[count[charCode]--] = i++;
        }
        i = 0;
        while (i < str.length()) {
            res[i++] = str.substring(p[i]);
        }
        return res;
    }
}
