package TestYourSelf.numberTheory;

public class CompareInv {
    private static final int mod = (int) 1e9 + 7;
    private static final int k = (int) 1e5 + 5;

    private static long binPow(int n, int pow) {
        long res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) {
                res = res * n % mod;
            }
            n = n * n % mod;
            pow >>= 1;
        }
        return res;
    }

    /**
     * Малая теорема Ферма говорит, что для любого простого числа p и любого целого числа a,
     * a^p ≡ a  (mod p)
     * Теперь два раза «поделим» этот известный результат на a
     * a^p ≡ a => a^(p-1) ≡ 1 => a^(p-2) ≡ a^(-1)
     *
     * @param n - число для которого ищем обратное по модулю mod
     * @return обратный элемент как a^(p-2)
     */
    private static long inv(int n) {
        return binPow(n, mod - 2);
    }

    public static void main(String[] args) {
        int a = 3, p = k;
        long pow = binPow(a, p);
        long invA = inv(a);
    }
}
