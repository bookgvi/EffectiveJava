package Algos;

public class Pow {
    public static void main(String[] args) {
        int n = 2, pow = 4;
        System.out.println(pow(n, pow));
    }

    public static int pow(int n, int pow) {
        if (pow == 0) return 1;
        else if ((pow & -pow) == 1) return pow(n, pow - 1) * n;
        else return pow(n, pow / 2) * pow(n, pow / 2);
    }
}
