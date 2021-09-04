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

    public static int[][] getFiboForNum(int n) {
        int[][] FiboMatrix = new int[][]{{0, 1}, {1, 1}};
        return binaryPow(FiboMatrix, n);
    }

    private static int[][] matrixMultiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] binaryPow(int[][] matrix, int pow) {
        int[][] E = new int[][]{{1, 0}, {0, 1}};
        while (pow > 0) {
            if ((pow & 1) == 1) {
                E = matrixMultiply(matrix, E);
                pow--;
            }
            matrix = matrixMultiply(matrix, matrix);
            pow >>= 1;
        }
        return E;
    }
}
