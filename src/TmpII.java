import java.util.*;

public class TmpII {
    private static int[] suffixArray(String str) {
        int len = str.length(), b = 8;
        int[] p = new int[len], c = new int[len], count = new int[1 << b];
        for (int i = 0; i < len; i += 1)
            count[str.charAt(i)] += 1;
        for (int i = 1; i < 1 << b; i += 1)
            count[i] += count[i - 1];
        for (int i = len - 1; i >= 0; i -= 1)
            p[--count[str.charAt(i)]] = i;
        c[p[0]] = 0;
        int classes = 1;
        for (int i = 1; i < len; i += 1) {
            if (str.charAt(p[i]) != str.charAt(p[i - 1])) classes += 1;
            c[p[i]] = classes - 1;
        }
        int[] pn = new int[len], cn = new int[len];
        for (int h = 0; (1 << h) < len; h += 1) {
            for (int i = 0; i < len; i += 1) {
                pn[i] = p[i] - (1 << h);
                if (pn[i] < 0) pn[i] += len;
            }
            count = new int[1 << b];
            for (int i = 0; i < len; i += 1)
                count[c[pn[i]]] += 1;
            for (int i = 1; i < 1 << b; i += 1)
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

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private static List<Integer> factorization(int n) {
        List<Integer> factor = new ArrayList<>();
        for (int i = 2; (long) i * i <= n; i += 1) {
            while(n % i == 0) {
                factor.add(i);
                n /= i;
            }
        }
        if (n > 1) factor.add(n);
        return factor;
    }

    private static long pow(int n, int pow) {
        int res = 1;
        while(pow >  0) {
            if ((pow & 1) == 1) res *= n;
            n *=n;
            pow >>= 1;
        }
        return res;
    }

    private static void fact(int n) {
        int pow = 0, p = 3, k = p;
        while(n/p > 0) {
            pow += n / p;
            p *= k;
        }
        long pw = pow(k, pow);
    }

    public static void main(String[] args) {
        List<Integer> factor = factorization(1000);
//        long fact = fact(10);
        String s = "abc";
        int[] p = suffixArray(s);

        int a = 10, b = 8;
        int gcd = gcd(a, b);
    }
}
