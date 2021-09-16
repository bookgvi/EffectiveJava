package Algos.String;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class HashString {
    private static final int k = 31;
    private static final int mod = (int) (1e3 + 7); // не вызывает переполнение для типа int
    private static final int[] pows = pows(k, mod);

    public static void main(String[] args) {
        String str = "012345123000123123123";
        String subStr = "123";
        int[] hashes = prefixHashes(str);
        int hash = hash(str);
        int hashOfSubStr = hash(subStr);

        System.out.printf("PrefixHashes for %s:\n%s\n", str, Arrays.toString(hashes));
        System.out.printf("\n%s (HASH: %d)\n", str, hash);
        System.out.printf("\n%s (HASH: %d)\n", subStr, hashOfSubStr);

        int hash123 = removePrefix(str.substring(0, 1), hashes[subStr.length() + 1]);
        System.out.println(hash123);
    }

    private static int removePrefix(String prefix, int hash) {
        int lenP = prefix.length();
        int hashP = hash(prefix);
        int[] evkl = evklidExt(pows[lenP], mod);
        if (evkl[0] != 1) {
            System.out.printf("fuck... %d and %d не взаимно просты \n", pows[lenP], mod);
            return -1;
        }
        int invK = evkl[1];

        return (hash - hashP) * invK % mod;
    }

    private static int concatH(String A, String B) {
        int lenA = A.length();
        int hashA = hash(A);
        int hashB = hash(B);
        return hashA + pows[lenA] * hashB;
    }

    private static int hash(String str) {
        int len = str.length();
        int hash = 0;
        byte a = 0;//"a".getBytes()[0];
        byte[] strBytes = str.getBytes();
        for (int i = 0; i < len; i += 1) {
            int ch = strBytes[i] - a + 1;
            hash = (hash + (ch * pows[i])) % mod;
        }
        return hash;
    }

    private static int[] prefixHashes(String str) {
        int len = str.length();
        int[] hashes = new int[len];
        hashes[0] = 0;
        byte a = 0; //"a".getBytes()[0];
        byte[] strBytes = str.getBytes();
        for (int i = 1; i < len; i += 1) {
            int ch = strBytes[i - 1] - a + 1;
            hashes[i] = (hashes[i - 1] + (ch * pows[i - 1])) % mod;
        }
        return hashes;
    }

    private static int[] pows(int k, int mod) {
        int limit = (int) (1e5 + 1);
        int[] pows = new int[limit];
        pows[0] = 1;
        for (int i = 1; i < limit; i += 1) {
            pows[i] = (pows[i - 1] * k) % mod;
        }
        return pows;
    }

    private static int[] evklidExt(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

}
