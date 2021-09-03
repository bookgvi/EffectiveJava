package Algos;

import java.util.Arrays;

public class Matrix {
    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 4}, {3, 9}, {8, 6}};
        int[][] B = new int[][]{{1, 6}, {2, 9}};
        System.out.println(A.length);
        System.out.println(B.length);
        int[][] C = multiply(A, B);
        System.out.println(Arrays.deepToString(C));
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] C = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                for (int k = 0; k < B.length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
