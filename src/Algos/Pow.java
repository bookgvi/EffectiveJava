package Algos;

public class Pow {
    public static void main(String[] args) {
        int n = 2, pow = 3;
        System.out.println(pow(n, pow));
        System.out.println(binPow(n, pow));
    }

    public static int pow(int n, int pow) {
        if (pow == 0) return 1;
        else if ((pow & -pow) == 1) return pow(n, pow - 1) * n;
        else return pow(n, pow / 2) * pow(n, pow / 2);
    }

    public static int binPow(int n, int pow) {
        int result = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) {
                result *= n;
                pow--;
            }
            n *= n;
            pow >>= 1;
        }
        return result;
    }
}
