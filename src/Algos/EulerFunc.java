package Algos;

public class EulerFunc {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(getEulerFunc(n));
    }

    /*
     * Два целых числа a и b называются взаимно простыми, если gcd(a, b) = 1.
     * Функция Эйлера φ(n) определяет количество целых чисел от 1 до n, взаимно простых с n.
     * Например, φ(10) = 4, потому что числа 1, 3, 7 и 9 взаимно просты с 10.
     * */
    private static int getEulerFunc(int n) {
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
