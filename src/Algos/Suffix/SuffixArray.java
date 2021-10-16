package Algos.Suffix;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

public class SuffixArray {
    private static final String str = "abracadabra";
    //    private static final String str = "abaab";
    private static final String firstChar = "a";
    private static final byte firstCharByte = firstChar.getBytes()[0];

    public static void main(String[] args) {
        System.out.println(Arrays.toString(lexicoGraphikSuffixSort(str)));
        for(int pos : lexicoGraphikSuffixSort(str)){
            System.out.println(str.substring(pos));
        }
    }

    private static int[] lexicoGraphikSuffixSort(String str) {
        int b = 8, dw = Integer.BYTES, len = str.length();
        int[] count = new int[1 << b], p= new int[len], c = new int[len];
        int firstCharByte = "a".getBytes()[0];
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        for (int elt : strBytes) {
            int code = elt - firstCharByte + 1;
            count[code] += 1;
        }
        for (int i = 1; i < 1 << b; i += 1) {
            count[i] += count[i - 1];
        }
        for (int i = len - 1; i >= 0; i -= 1) {
            int code = strBytes[i] - firstCharByte + 1;
            p[--count[code]] = i;
        }
        int classes = 1;
        for (int i = 0; i < len - 1; i += 1) {
            if (str.charAt(p[i]) != str.charAt(p[i + 1])) classes += 1;
            c[p[i]] = classes - 1;
        }
        return p;
    }
}
