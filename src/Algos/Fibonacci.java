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
        int[][] fibo = new int[][]{{0, 1}, {1, 1}};
        return matrixPow(fibo, n);
    }

    private static int[][] matrixPow(int[][] M, int pow) {
        int[][] E = new int[][]{{1, 0}, {0, 1}};
        while(pow > 0) {
            if ((pow & 1) == 1) E = matrixMultiply(E, M);
            M = matrixMultiply(M, M);
            pow >>= 1;
        }
        return E;
    }

    private static int[][] matrixMultiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i += 1) {
            for (int j = 0; j < B.length; j += 1) {
                for (int k = 0; k < A.length; k += 1) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
