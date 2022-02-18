package Algos.BackTracking;

import java.util.*;

public class NQueensPuzzle {
    private static final int n = 5;
    private static final List<Pair<Integer, Integer>> prevPositions = new ArrayList<>();
    private static final List<List<Pair<Integer, Integer>>> allPositions = new ArrayList<>();

    /**
     * Определить количество способов разместить Ферзя на поле N x N
     * Рекурсивный backtracking алгоритм
     *
     * @param row   - горизонталь
     */
    private static void backtrackQueen(int row) {
        for (int col = 0; col < n; col += 1) {
            if (isNotUnderAttack(row, col)) {
                placeQueen(row, col);
                if (row + 1 == n) { // мы достигли конца поля
                    allPositions.add(List.copyOf(prevPositions));
                } else backtrackQueen(row + 1);
                removeQueen(); // удалить последнюю позицию
            }
        }
    }

    private static boolean isNotUnderAttack(int row, int col) {
        if (prevPositions.isEmpty()) return true;
        boolean ans = true;
        for (Pair<Integer, Integer> pos : prevPositions) {
            int prevRow = pos.first;
            int prevCol = pos.second;
//            ans &= prevRow != row; // горизонталь - избыточная проверка
            ans &= prevCol != col; // вертикаль
            ans &= Math.abs(prevRow - row) != Math.abs(prevCol - col); // диагональ
        }
        return ans;
    }

    private static void placeQueen(int row, int col) {
        Pair<Integer, Integer> pos = new Pair<>(row, col);
        prevPositions.add(pos);
    }

    private static void removeQueen() {
        int size = prevPositions.size();
        if (size > 0) prevPositions.remove(size - 1);
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

    public static void main(String[] args) {
        backtrackQueen(0);
        int res = allPositions.size();
    }
}
