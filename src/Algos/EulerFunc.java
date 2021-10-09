package Algos;

public class EulerFunc {

    public static void main(String[] args) {
        int n = 561;
        System.out.println(getEulerFunc(n));
    }


    private static int getEulerFunc(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while(n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res /n;
        return res;
    }

    /*
     * Два целых числа a и b называются взаимно простыми, если gcd(a, b) = 1.
     * Функция Эйлера φ(n) определяет количество целых чисел от 1 до n, взаимно простых с n.
     * Например, φ(10) = 4, потому что числа 1, 3, 7 и 9 взаимно просты с 10.
     * */
    private static int getEulerFunc1(int n) {
        int result = n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                result -= result / i;
            }
        }
        if (n > 1) result -= result / n;
        return result;
    }
}
