package Algos.String;

public class HashString {

    public static void main(String[] args) {
        int n = 2;
        int pow = 7;
        int mod = 97;
        int k = 3;
        int A = 65;
        int B = 66;
        int C = 67;
        int[] str = new int[] {A, B, A, B, C};
        int strHash = getHash(str, k , mod);
        System.out.println(strHash);
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

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            pow >>= 1;
            n = (n * n) % mod;
        }
        return res;
    }

    /*
    * Вычисление хэша строки
    * (s[0]An−1 + s[1]An−2 + ... + s[n − 1]A0) mod B
    * */
    private static int getHash(int[] str, int k, int mod) {
        int hash = 0;
        for (int index = 0, len = str.length - 1; index <= len; index += 1) {
            int ch = str[index];
            int pow = pow(k, len - index);
            hash += ch * pow;
        }
        return hash % mod;
    }
}
