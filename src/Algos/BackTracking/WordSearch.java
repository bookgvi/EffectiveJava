package Algos.BackTracking;

import java.util.*;

/*
 * TLE
 * */
public class WordSearch {
    private static char[][] grid;
    private static StringBuilder letters;
    private static final char emptyCh = '.', deleted = '-';
    private static List<Pair<Integer, Integer>> positions;
    private static boolean res;
    private static List<char[][]> solutions;


    public static void main(String[] args) {
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'W'}
//        };
//        char[][] board = {
//                {'C', 'A', 'A'},
//                {'A', 'A', 'A'},
//                {'B', 'C', 'D'}
//        };
//        char[][] board = {
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}};
//        String word = "SEED";
//        char[][] board = {
//                {'a'}};
//        String word = "a";
        char[][] board = {
                {'a', 'b'}};
        String word = "ba";
        boolean res = exist(board, word);
    }

    public static boolean exist(char[][] board, String word) {
        grid = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i += 1)
            for (int j = 0; j < board[0].length; j += 1)
                grid[i][j] = emptyCh;
        positions = new ArrayList<>();
        solutions = new ArrayList<>();
        letters = new StringBuilder();
        res = false;

        for (int row = 0; row < grid.length; row += 1) {
            for (int col = grid[0].length > 2 ? 1 : 0; col < grid[0].length; col += 1)
                if (!res) backTrack(board, row, col, word, 0);
        }
        return res;
    }

    private static void backTrack(char[][] board, int row, int col, String word, int pos) {
        List<Pair<Integer, Integer>> moves = possibleMoves(row, col);
        for (int it = 0; it < moves.size(); it += 1) {
            Pair<Integer, Integer> nextCell = moves.get(it);
            row = nextCell.first;
            col = nextCell.second;
            if (check(row, col, board, word, pos)) {
                pos = place(board, nextCell, pos);
                if (letters.toString().equals(word)) {
                    res = true;
                    char[][] tmpRes = new char[grid.length][grid[0].length];
                    for (int i = 0; i < grid.length; i += 1) {
                        System.arraycopy(grid[i], 0, tmpRes[i], 0, grid[0].length);
                    }
                    solutions.add(tmpRes);
                } else
                    backTrack(board, row, col, word, pos);
                pos = removeLast(row, col, pos);
            }
        }
    }

    private static List<Pair<Integer, Integer>> possibleMoves(int row, int col) {
        List<Pair<Integer, Integer>> moves = new ArrayList<>();
        if (grid[0].length == 1) {
            moves.add(new Pair<>(0, 0));
            return moves;
        }
//        moves.add(new Pair<>(row, col));

        if (row - 1 >= 0 && Objects.equals(grid[row - 1][col], emptyCh))
            moves.add(new Pair<>(row - 1, col));
        if (row + 1 < grid.length && Objects.equals(grid[row + 1][col], emptyCh))
            moves.add(new Pair<>(row + 1, col));

        if (col > 0 && Objects.equals(grid[row][col - 1], emptyCh))
            moves.add(new Pair<>(row, col - 1));
        if (col + 1 < grid[0].length && Objects.equals(grid[row][col + 1], emptyCh))
            moves.add(new Pair<>(row, col + 1));

        return moves;
    }

    private static boolean check(int row, int col, char[][] board, String word, int pos) {
        boolean ans = Objects.equals(grid[row][col], emptyCh);
        ans &= Objects.equals(word.charAt(pos), board[row][col]);
        if (positions.size() > 0) {
            Pair<Integer, Integer> prev = positions.get(positions.size() - 1);
            ans &= (prev.first != row || prev.second != col);
        }
        return ans;
    }

    private static int place(char[][] board, Pair<Integer, Integer> cell, int pos) {
        int row = cell.first, col = cell.second;
        char ch = board[row][col];
        grid[row][col] = ch;
        letters.append(ch);
        positions.add(cell);
        return pos + 1;
    }

    private static int removeLast(int row, int col, int pos) {
        grid[row][col] = emptyCh;
        letters.delete(letters.length() - 1, letters.length());
        positions.remove(positions.size() - 1);
        return pos - 1;
    }

    private static class Pair<U, V> {
        U first;
        V second;

        Pair() {
        }

        Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return first + " " + second;
        }
    }
}
