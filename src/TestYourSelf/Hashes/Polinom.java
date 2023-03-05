package TestYourSelf.Hashes;

public class Polinom {
    private static final int mod = (int) 1e9 + 7;
    private static final int k = (int) 1e5 + 7;
    private static final char firstChar = '1';
    private static long[] directPrefixHashes;
    private static long[] reversePrefixHashes;
    private static final long[] pows = pows();

    private static long[] pows() {
        int len = 100;
        long[] pows = new long[len];
        pows[0] = 1;
        for (int i = 1; i < len; i += 1) {
            pows[i] = pows[i - 1] * k % mod;
        }
        return pows;
    }

    private static long[] getDirectPrefixHashes(String str) {
        int len = str.length();
        long[] directPrefixHashes = new long[len];
        byte[] strBytes = str.getBytes();
        directPrefixHashes[0] = (strBytes[0] - firstChar + 1) * pows[0];
        for (int i = 1; i < len; i += 1) {
            directPrefixHashes[i] = directPrefixHashes[i - 1] + (strBytes[i] - firstChar + 1) * pows[i] % mod;
        }
        return  directPrefixHashes;
    }

    private static long[] getReversePrefixHashes(String str) {
        int len = str.length();
        long[] reversePrefixHashes = new long[len];
        byte[] strBytes = str.getBytes();
        reversePrefixHashes[0] = (strBytes[0] - firstChar + 1) * pows[len - 1] % mod;
        for (int i = 1; i < len; i += 1) {
            reversePrefixHashes[i] = reversePrefixHashes[i - 1] + (strBytes[i] - firstChar + 1) * pows[len - i - 1] % mod;
        }
        return reversePrefixHashes;
    }

    public static void main(String[] args) {
        String str = "12334321";
        int len = str.length();
        int d = (len & 1) == 1 ? 1 : 0;
        String str1 = str.substring(0, len >> 1);
        String str2 = str.substring((len >> 1) + d, len);
        directPrefixHashes = getDirectPrefixHashes(str1);
        reversePrefixHashes = getReversePrefixHashes(str2);
        if (directPrefixHashes[directPrefixHashes.length - 1] == reversePrefixHashes[reversePrefixHashes.length - 1]) {
            System.out.println(str + " - polinom");
        } else {
            System.out.println(str + " - not a polinom");
        }
    }
}
