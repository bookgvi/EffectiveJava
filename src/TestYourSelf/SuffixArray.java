package TestYourSelf;

public class SuffixArray {
    public static void main(String[] args) {
        String str = "abracadabra";
        int[] p = sortCyclicStrings(str);

        for (int i = 0; i < str.length(); i += 1)
            System.out.println(str.substring(p[i]));
    }
    private static int[] sortCyclicStrings(String str) {
        int len = str.length();
        int[] p = new int[len], c = new int[len];
        int[] count = new int[1 << 8];
        for (int i = 0; i < len; i += 1) {
            count[str.charAt(i)] += 1;
        }
        for (int i = 1; i < 1 << 8; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            p[--count[str.charAt(i)]] = i;
        c[p[0]] = 0;
        int classes = 1;
        for (int i = 1; i < len; i += 1) {
            if (str.charAt(p[i - 1]) != str.charAt(p[i])) classes += 1;
            c[p[i]] = classes - 1;
        }

        int[] pn = new int[len], cn = new int[len];
        for (int h = 0; (1 << h) < len; h += 1) {
            count = new int[1 << 8];
            for (int i = 0; i < len; i += 1) {
                pn[i] = p[i] - (1 << h);
                if (pn[i] < 0) pn[i] += len;
            }
            for (int i = 0; i < len; i += 1)
                count[c[pn[i]]] += 1;
            for (int i = 1; i < 1 << 8; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                p[--count[c[pn[i]]]] = pn[i];
            cn[p[0]] = 0;
            classes = 1;
            for (int i = 1; i < len; i += 1) {
                int mid1 = (p[i] + (1 << h)) % len, mid2 = (p[i - 1] + (1 << h)) % len;
                if (c[p[i]] != c[p[i - 1]] || c[mid1] != c[mid2]) classes += 1;
                cn[p[i]] = classes - 1;
            }
            System.arraycopy(cn, 0, c, 0, len);
        }
        return p;
    }
}
