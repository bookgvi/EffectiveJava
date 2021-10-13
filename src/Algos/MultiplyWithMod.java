package Algos;

public class MultiplyWithMod {
    public static void main(String[] args) {
        int a = Integer.MAX_VALUE / 8 * 7, b = Integer.MAX_VALUE / 10 * 9, mod = (int) 1e9 + 7, k = (int) 1e5 + 5;
        int[] ev = evklidExt(k, mod);
        System.out.println(ev[0]);
        long res = mul(a, b);
        System.out.println(res);
        res = mulMod(a, b, mod);
        System.out.println(res);
    }

    private static long mul(long a, int b) {
        long res = 0;
        while (b > 0) {
            if ((b & 1) == 1) res += a;
            a += a;
            b >>= 1;
        }
        return res;
    }

    private static int mulMod(int a, int b, int mod) {
        int res = 0;
        while(b > 0) {
            if ((b & 1) == 1) res = res % mod + a % mod;
            a = a % mod + a % mod;
            b >>= 1;
        }
        return res;
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
