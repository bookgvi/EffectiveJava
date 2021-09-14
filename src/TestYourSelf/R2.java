package TestYourSelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class R2 {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(Arrays.deepToString(new int[][]{fibo(n)[1]}));
    }

    private static int[][] matrixMul(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j += 1) {
                for (int k = 0; k < A.length; k += 1) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] matrixPow(int[][] matrix, int pow) {
        int[][] E = new int[][] {{1, 0}, {0, 1}};
        while (pow > 0) {
            if ((pow & 1) == 1) E = matrixMul(matrix, E);
            matrix = matrixMul(matrix, matrix);
            pow >>= 1;
        }
        return E;
    }

    private static int[][] fibo(int n) {
        int[][] fiboMatrix = new int[][] {{0, 1}, {1, 1}};
        return matrixPow(fiboMatrix, n);
    }
}
