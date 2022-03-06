package Algos.Dynamic;

import java.util.Stack;

/*
 * https://leetcode.com/problems/maximal-rectangle/submissions/
 * */
public class RectangleArea {
    private static final int[][] rect1 = {
            {0, 1, 0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4, 0, 0},
            {1, 0, 1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5, 6, 7},
            {1, 0, 1, 2, 3, 4, 5},
            {1, 2, 0, 0, 1, 2, 0}
    };
    private static final int[][] rect2 = {
            {0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 2, 3},
            {1, 2, 3, 4, 5, 6, 7},
            {0, 0, 0, 1, 2, 3, 4}
    };

    private static int[][] createHelpMatrix(char[][] matrix) {
        int lenI = matrix.length, lenJ = matrix[0].length;
        int[][] helperMatrix = new int[lenI][lenJ];
        for (int i = 0; i < lenI; i += 1) {
            Stack<Integer> cummulative = new Stack<>();
            for (int j = 0; j < lenJ; j += 1) {
                int val = matrix[i][j] == '1' ? 1 : 0, prevVal = 0;
                if (!cummulative.isEmpty()) prevVal = cummulative.peek();
                cummulative.push((val + prevVal) * val);
            }
            helperMatrix[i] = cummulative.stream().mapToInt(elt -> elt).toArray();
        }
        return helperMatrix;
    }

    private static int calculateArea(char[][] matrix) {
        int[][] rect = createHelpMatrix(matrix);
        int lenI = rect.length, lenJ = rect[0].length, ans = Integer.MIN_VALUE;
        int[][] res = new int[lenI][lenJ], L = new int[lenI][lenJ], R = new int[lenI][lenJ];
        for (int j = 0; j < lenJ; j += 1)
            for (int i = 0; i < lenI; i += 1) {
                if (rect[i][j] > 0) {
                    int k = i;
                    while (k - 1 >= 0 && rect[i][j] <= rect[k - 1][j]) {
                        L[i][j] += 1;
                        k -= 1;
                    }
                    k = i;
                    while (k + 1 < lenI && rect[i][j] <= rect[k + 1][j]) {
                        R[i][j] += 1;
                        k += 1;
                    }
                }
            }
        for (int i = 0; i < lenI; i += 1)
            for (int j = 0; j < lenJ; j += 1) {
                res[i][j] = rect[i][j] * (L[i][j] + R[i][j] + 1);
                ans = Math.max(res[i][j], ans);
            }
        return ans;
    }


    public static void main(String[] args) {
        char[][] matrix = {
                {'0', '0', '0', '1', '0', '0'},
                {'0', '0', '1', '1', '0', '0'},
                {'0', '0', '1', '1', '0', '0'},
                {'0', '0', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1'}
        };
//        char[][] matrix = {
//                {'1', '0', '1', '0', '0'},
//                {'1', '0', '1', '1', '1'},
//                {'1', '1', '1', '1', '1'},
//                {'1', '0', '0', '1', '0'}
//        };
//        char[][] matrix = {};
//        char[][] matrix = {{'1'}};
//        char[][] matrix = {{'0'},{'0'}};
//        char[][] matrix = {{'1', '0'},{'1', '0'}};
//        char[][] matrix = {{'0', '1', '1'},
//                      {'1', '1', '1'}};
//        char[][] matrix = {{'1', '1'}};
        calculateArea(matrix);
    }
}
