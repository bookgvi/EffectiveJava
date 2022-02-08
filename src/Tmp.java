import java.util.*;

public class Tmp {
    private static final int k = (int) 1e5 + 1;
    private static final int mod = (int) 1e9 + 7;
    private static final long[] pows = pows();
    private static final int digits = 4;
    private static final int base = (int) 1e4;
    private static final int radix = 10;

    private static String listToStr(List<Integer> num) {
        StringBuilder res = new StringBuilder();
        for (int len = num.size(), i = len - 1; i >= 0; i -= 1) {
            String tmp = String.valueOf(num.get(i));
            if (tmp.length() < digits) {
                res.append("0".repeat(digits - tmp.length()));
            }
            res.append(tmp);
        }
        while (res.charAt(0) == '0') res.delete(0, 1);
        if (res.length() < 1) res.append("0");
        return res.toString();
    }

    private static List<Integer> strToList(String num) {
        List<Integer> res = new ArrayList<>();
        for (int i = num.length(); i > 0; i -= digits) {
            if (i < digits) res.add(Integer.parseInt(num.substring(0, i), radix));
            else res.add(Integer.parseInt(num.substring(i - digits, i), radix));
        }
        return res;
    }

    private static String sum(String num1, String num2) {
        List<Integer> a = strToList(num1), b = strToList(num2);
        int len = Math.max(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (a.size() == i) a.add(0);
            int valB = i < b.size() ? b.get(i) : 0;
            int valA = a.get(i);
            a.set(i, valA + valB + carry);
            carry = a.get(i) > base ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - base);
        }
        return listToStr(a);
    }

    private static long getHash(String str) {
        int len = str.length();
        long hash = 0;
        for (int i = 0; i < len; i += 1)
            hash = (hash + (str.charAt(i) - 'a' + 1) * pows[i]) % mod;
        return hash;
    }

    private static long[] pows() {
        int max = (int) 1e4 + 3;
        long[] pows = new long[max];
        pows[0] = 1;
        for (int i = 1; i < max; i += 1)
            pows[i] = pows[i - 1] * k % mod;
        return pows;
    }

    private static int phi(int n) {
        int res = n;
        for (int i = 2; i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * n % mod;
            n = n * n % mod;
            pow >>= 1;
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

    private static int gcd(int a, int b) {
        if (a == 0) return b;
        return gcd(b % a, a);
    }

    private static List<Integer> erat(int n) {
        boolean[] erat = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i += 1) {
            if (erat[i]) continue;
            primes.add(i);
            for (int j = i; j <= n; j += i)
                erat[j] = true;
        }
        return primes;
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

    private static int binSum(int n) {
        if (n == 1) return n;
        int d = (n & 1) == 1 ? pow((n >> 1) + 1, 2) : pow(n >> 1, 2);
        return (binSum(n >> 1) << 1) + d;
    }

    private static int binMul(int n, int m) {
        int res = 0;
        while(m > 0) {
            if ((m & 1) == 1) res += n;
            n <<= 1;
            m >>= 1;
        }
        return res;
    }

    private static void classes(String str) {
        int classes = 1, len = str.length();
        int[] c = new int[1 << 8];
        for (int i = 0; i < len; i += 1)
            c[str.charAt(i)] = 1;
        for (int i = 1; i < 1 << 8; i += 1)
            c[i] += c[i - 1];
        int res = c[(1 << 8) - 1];
    }

    public static void main(String[] args) {
        int a = 5, b = 6;
        int[] res = evklidExt((int) 1e9 + 7, (int) 1e5);
        int[] res2 = evklidExt(a, b);
        String str1 = "melioration";
        String str2 = "demystify";
        long h1 = getHash(str1);
        long h2 = getHash(str2);
        String num1 = "1234", num2 = "900";
        String sum = sum(num1, num2);

        int n = 5;
        int binSum = binSum(n);
        int calcRes = n * (n + 1) / 2;

        int mul = binMul(a, b);

        classes("aaaabbbaa");
    }
}
