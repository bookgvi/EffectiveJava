package Algos;

/*
* Решение уравнений вида a*x+b*y=с
* x2 = y1 - (b / a) * x1
* y2 = x1
* k = c / gcd(a, b)
* x = k * x2
* y = k * y2
* */
public class Diofant {
    public static void main(String[] args) {
        int a = 39;
        int b = 15;
        int c = 13;
        int[] ev = evklidExt(a, b);
        int gcd = ev[0];
        System.out.printf("%dx + %dy = %d\n", a, b, c);
        if ((c % gcd) != 0) {
            System.out.println("fail... Решений не существует");
            return;
        }
        int x = ev[1] * c / gcd;
        int y = ev[2] * c / gcd;
        System.out.printf("x = %d, y = %d\n", x, y);
    }

    private static int[] evklidExt(int a, int b) {
        int[] result = new int[3];
        if (a == 0) {
            result[0] = b;
            result[2] = 1;
            return result;
        }
        result = evklidExt(b % a, a);
        int tmp = result[1];
        result[1] = result[2] - (b / a) * result[1];
        result[2] = tmp;
        return result;
    }
}