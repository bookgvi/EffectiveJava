package Algos;

public class GCD {
    public static void main(String[] args) {
        int a = 122, b = 30;
        System.out.println(gcd(a, b));
        System.out.println(gcdNotRecursion(a, b));
    }

    /*
     * Алоритм Евклида для нахождения НОД
     * */
    static public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int gcdNotRecursion(int a, int b) {
        int x = 0;
        do {
            x = b;
            b = a % b;
            a = x;
        } while (b != 0);
        return a;
    }
}
