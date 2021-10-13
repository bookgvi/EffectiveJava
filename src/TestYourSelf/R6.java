package TestYourSelf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class R6 {
    private static final int k = (int) 1e5 + 5;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final long[] invP = invP();
    private static final String firstChar = "A";
    private static final byte firstCharByte = firstChar.getBytes()[0];
    private static final String str1 = "VOTEFORTHEGREATALBANIAFORYOU";
    private static final String str2 = "CHOOSETHEGREATALBANIANFUTURE";
    private static final long[] prefHash1 = prefixHashes(str1);
    private static final long[] prefHash2 = prefixHashes(str2);

    public static void main(String[] args) {
        int pos1 = 7;
        int pos2 = 6;
        int len = 15;
        long startTime = System.nanoTime();
        boolean isEqual = compareTwoString(prefHash1, pos1, prefHash2, pos2, len);
        long endTime = System.nanoTime();
        System.out.printf("%b\t%.8f\n", isEqual, (endTime - startTime) / 1e9);

        List<String> cyclicStr = getCyclicStrings(str1);
        System.out.println(cyclicStr);
    }

    private static List<Long> sort(List<Long> arr) {
        final long SIZE = Long.BYTES * 8 / 2;
        List<List<Long>> digits = LongStream.range(0, SIZE).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());
        List<List<Long>> digits2 = LongStream.range(0, SIZE).mapToObj(i -> new ArrayList<Long>()).collect(Collectors.toList());

        for (Long elt : arr) {
            digits.get((int) (elt % SIZE)).add(elt);
        }

        for (List<Long> eltList : digits) {
            for (Long elt : eltList) {
                digits2.get((int) (elt / SIZE)).add(elt);
            }
        }

        return digits2.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static boolean compareTwoString(long[] prefHash1, int pos1, long[] prefHash2, int pos2, int len) {
        int k = 0;
        for(int i = len / 2; i > 0; i /= 2) {
            while (i + k < len && hash(prefHash1, pos1, i + k) == hash(prefHash2, pos2, i + k)) k += i;
        }
        return k +1 == len && str1.charAt(pos1 + k) == str2.charAt(pos2 + k);
    }

    private static long hash(long[] hashes, int start, int end) {
        long h1 = hashes[start + end];
        long p1 = start > 0 ? hashes[start - 1] : 0;
        if (h1 - p1 < 0) h1 += mod;
        return (h1 - p1) * invP[start] % mod;
    }

    private static long[] prefixHashes(String str) {
        int len = str.length();
        long[] hashes = new long[len];
        byte[] strBytes = str.getBytes();
        hashes[0] = (strBytes[0] - firstCharByte + 1) * pows[0] % mod;
        for (int i = 1; i < len; i += 1) {
            hashes[i] = (hashes[i - 1] + (strBytes[i] - firstCharByte + 1) * pows[i]) % mod;
        }
        return hashes;
    }

    private static long[] pows() {
        final int MAX_VALUE = (int) 1e5 + 5;
        long[] pows = new long[MAX_VALUE];
        pows[0] = 1;
        for (int i = 1; i < MAX_VALUE; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] invP() {
        final int count = pows.length;
        long[] invP = new long[count];
        long phi = phi(mod) - 1;
        for (int i = 0; i < count; i += 1) {
            invP[i] = modPow(pows[i], phi, mod);
        }
        return invP;
    }

    private static long modPow(long n, long pow, int mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    private static long phi(long n) {
        long res = n;
        for (int i = 2; (long) i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static String shift(String str, int shift) {
        if (shift == 0) return str;
        shift %= str.length();
        return str.substring(shift); // + str.substring(0, shift);
    }

    private static List<String> getCyclicStrings(String str) {
        List<String> cyclicStr = new ArrayList<>();
        for (int i = 0, len = str.length(); i < len; i += 1) cyclicStr.add(shift(str, i));
        return cyclicStr;
    }
}
