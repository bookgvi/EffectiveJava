package TestYourSelf;

import java.util.Arrays;

public class Fibbonachi {
    public static void main(String[] args) {
        int n = 15;
        for (int i = 0; i < n; i += 1) {
            int[][] F = getFibo(i);
            System.out.printf("%d ", F[1][0]);
        }
    }

    private static int[][] getFibo(int n) {
        int[][] FIBO = new int[][]{{0, 1}, {1 , 1}};
        return matrixPow(FIBO, n);
    }

    private static int[][] matrixPow(int[][] M, int pow) {
        int[][] E = new int[][]{{1, 0}, {0, 1}};
        while (pow > 0) {
            if ((pow & 1) == 1) E = matrixMultiply(E, M);
            M = matrixMultiply(M, M);
            pow >>= 1;
        }
        return E;
    }

    private static int[][] matrixMultiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0, lenA = A.length; i < lenA; i += 1)
            for (int j = 0, lenB = B.length; j < lenB; j += 1)
                for (int k = 0; k < lenA; k += 1)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }
}
