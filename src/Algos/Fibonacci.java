package Algos;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        int n = 10;
        System.out.print(Arrays.toString(getFiboForNum(1)[1]));
        for (int i = 2; i <= n; i++) {
            if ((i & 1) == 1) System.out.print(Arrays.toString(getFiboForNum(i)[1]));
        }
    }

    private static int[][] getFiboForNum(int n) {
        int[][] FIBO = new int[][]{{0, 1}, {1, 1}};
        return matrixPow(FIBO, n);
    }

    private static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for(int i = 0, lenA = A.length; i < lenA; i += 1) {
            for (int j = 0, lenB = B.length; j < lenB; j += 1) {
                for (int k = 0; k < lenA; k += 1) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] matrixPow(int[][] M, int pow) {
        int[][] E = new int[][]{{1, 0}, {0, 1}};
        while(pow > 0) {
            if ((pow & 1) == 1) E = multiply(M, E);
            M = multiply(M, M);
            pow >>= 1;
        }
        return E;
    }
}
