package Leetcode.Simple;

import java.util.*;

/*
 * https://leetcode.com/problems/n-queens/submissions/
 * */
public class NQueens {
    private static final String Q = "Q";
    private static final String emptyCell = ".";
    private static int[] ld, rd, cols;
    private static List<String> positions;

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> res = solveNQueens(n);
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> allPositions = new ArrayList<>();
        ld = new int[n << 1];
        rd = new int[n << 1];
        cols = new int[n];
        positions = new ArrayList<>();
        backTrack(n, 0, allPositions);
        return allPositions;
    }

    private static void backTrack(int n, int row, List<List<String>> allPositions) {
        for (int col = 0; col < n; col += 1) {
            if (isNotUnderAttack(n, row, col)) {
                place(n, row, col);
                if (row + 1 == n) {
                    allPositions.add(List.copyOf(positions));
                } else
                    backTrack(n, row + 1, allPositions);
                remove(n, row, col);
            }
        }
    }

    private static boolean isNotUnderAttack(int n, int row, int col) {
        return cols[col] == 0 && ld[row - col + n - 1] == 0 && rd[row + col] == 0;
    }

    private static void place(int n, int row, int col) {
        StringBuilder pos = new StringBuilder();
        for (int i = 0; i < n; i += 1) {
            if (i == col) pos.append(Q);
            else pos.append(emptyCell);
        }
        positions.add(pos.toString());
        cols[col] = 1;
        ld[row - col + n - 1] = 1;
        rd[row + col] = 1;
    }

    private static void remove(int n, int row, int col) {
        positions.remove(positions.size() - 1);
        cols[col] = 0;
        ld[row - col + n - 1] = 0;
        rd[row + col] = 0;
    }
}
