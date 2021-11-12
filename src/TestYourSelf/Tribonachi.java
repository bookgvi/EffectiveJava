package TestYourSelf;

public class Tribonachi {
    public static int tribonacci(int n) {
        int[][] TRIBO = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 1, 1}};
        int[][] M = matrixPow(TRIBO, n);
        return M[2][0];
    }

    private static int[][] matrixPow(int[][] M, int pow) {
        int[][] E = new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        while (pow > 0) {
            if ((pow & 1) == 1) E = multiply(E, M);
            M = multiply(M, M);
            pow >>= 1;
        }
        return E;
    }

    private static int[][] multiply(int[][] A, int[][] B) {
        int lenA = A.length, lenB = B.length;
        int[][] C = new int[lenA][lenB];
        for (int i = 0; i < lenA; i += 1)
            for (int j = 0; j < lenB; j += 1)
                for (int k = 0; k < lenA; k += 1)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    public static void main(String[] args) {
        int n = 25;
        int res = tribonacci(n);
    }
}
