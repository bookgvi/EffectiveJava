package Algos.BackTracking;

import java.util.*;

/*
 * https://leetcode.com/problems/sudoku-solver/submissions/
 * */
public class SudokuSolver {
    private static final char emptyCh = '.';
    private static int len;
    private static char[][] origBoard;

    public static void main(String[] args) {
        char[][] boardL = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] boardLL = {
                {'5', '3', '.', '.', '7', '.'},
                {'6', '.', '.', '1', '9', '5'},
                {'.', '9', '8', '.', '.', '.'},
                {'8', '.', '.', '.', '6', '.'},
                {'4', '.', '.', '8', '.', '3'},
                {'7', '.', '.', '.', '2', '.'}
        };
        char[][] boardM = {
                {'5', '3', '.'},
                {'6', '.', '.'},
                {'.', '9', '8'}
        };
        len = boardL.length;

        origBoard = new char[len][len];
        for (int row = 0; row < len; row += 1)
            origBoard[row] = Arrays.copyOf(boardL[row], len);
        backTrack(boardL, 0);
    }


    private static void backTrack(char[][] board, int row) {
        int col;
        for (col = 0; col < len; col += 1) {
            if (isEmptyCell(board, row, col)) {
                for (char i = '1'; i <= '9'; i += 1) {
                    if (canPlace(board, row, col, i)) {
                        placeNum(board, row, col, i);
                        if (col + 1 <= len)
                            backTrack(board, row);
                        remove(board, row, col);
                    }
                }
            }
            if (isEmptyCell(board, row, col)) {
                break;
            }
        }
        if (col == len && row + 1 < len) {
            backTrack(board, row + 1);
            remove(board, row, col);
        } else if (row + 1 == len && col == len) {
            System.arraycopy(board, 0, origBoard, 0, len);
        }
    }

    private static boolean isEmptyCell(char[][] board, int row, int col) {
        return board[row][col] == emptyCh;
    }

    private static boolean canPlace(char[][] board, int row, int col, char val) {
        for (char elt : board[row]) {
            if (val == elt) return false;
        }
        for (int i = 0; i < len; i += 1)
            if (board[i][col] == val) return false;
        int row33 = row - (row % 3);
        int col33 = col - (col % 3);
        for (int i = row33; i < row33 + 3; i += 1)
            for (int j = col33; j < col33 + 3; j += 1)
                if (val == board[i][j]) return false;
        return true;
    }

    private static void placeNum(char[][] board, int row, int col, char val) {
        board[row][col] = val;
    }

    private static void remove(char[][] board, int row, int col) {
        if (col >= 0 && col < len && Objects.equals(origBoard[row][col], emptyCh)) board[row][col] = emptyCh;
    }

}
