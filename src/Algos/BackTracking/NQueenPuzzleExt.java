package Algos.BackTracking;

import java.util.*;

public class NQueenPuzzleExt {
    private static final int n = 5;
    private static final int[] cols = new int[n],
            ld = new int[n << 1], rd = new int[n << 1];
    private static final List<Pair<Integer, Integer>> positions = new ArrayList<>();
    private static final List<List<Pair<Integer, Integer>>> res = new ArrayList<>();

    public static void main(String[] args) {
        backTrackQueen(0);
        int ans = res.size();
    }

    private static void backTrackQueen(int row) {
        for (int col = 0; col < n; col += 1) {
            if (isNotUnderAttack(row, col)) {
                place(row, col);
                if (row + 1 == n) {
                    res.add(positions);
                } else backTrackQueen(row + 1);
                remove(row, col);
            }
        }
    }

    private static boolean isNotUnderAttack(int row, int col) {
        return cols[col] == 0 && ld[row - col + n - 1] == 0 && rd[row + col] == 0;
    }

    private static void place(int row, int col) {
        cols[col] = 1;
        ld[row - col + n - 1] = 1;
        rd[row + col] = 1;
        positions.add(new Pair<>(row, col));
    }

    private static void remove(int row, int col) {
        cols[col] = 0;
        ld[row - col + n - 1] = 0;
        rd[row + col] = 0;
    }

    private static class Pair<U, V> {
        private U first;
        private V second;

        Pair() {
        }

        Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}
