package Algos.Dynamic;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    private static final List<Integer> way = new ArrayList<>();

    private static int[][] solve(int[][] matrix) {
        int lenI = matrix.length, lenJ = matrix[0].length;
        int[][] dp = new int[lenI][lenJ];
        dp[lenI - 1][0] = matrix[lenI - 1][0]; // Начинаем в левом нижнем углу матрицы
        for (int i = lenI - 2; i >= 0; i -= 1)
            dp[i][0] = dp[i + 1][0] + matrix[i][0];  // инициализируем нулевой столбец
        for (int j = 1; j < lenJ; j += 1)
            dp[lenI - 1][j] = dp[lenI - 1][j - 1] + matrix[lenI - 1][j]; // инициализируем нижнюю строку
        for (int i = lenI - 2; i >= 0; i -= 1)
            for (int j = 1; j < lenJ; j += 1)
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + matrix[i][j]; // добавляем максимальные суммы, пока не доберемся до верхнего правого угла
        return dp;
    }

    /**
     * Сохранение пути до верхней правой ячейки
     *
     * @param i      - row
     * @param j      - col
     * @param dp     - calculated sum
     * @param matrix - starting matrix with values
     *               <p>
     *               Идем в обратном направлении от верхней правой ячейки DP до левой нижней вычитая значения из ячеек исходной матрицы
     */
    private static void getWay(int i, int j, int[][] dp, int[][] matrix) {
        int lenI = matrix.length;
        if (i == lenI - 1 && j == 0) return; // дошли до левой нижней ячейки, конец
        if (i == lenI - 1 && j > 0)
            getWay(i, j - 1, dp, matrix); // уперлись в нижнюю строку, ниже нельзя, можно только влево (на уменьшение столбцов)
        else if (i < lenI - 1 && j == 0)
            getWay(i + 1, j, dp, matrix); // уперлись в нулевой столбец, теперь только вниз на увеличение строк
        else {
            if (dp[i][j] - matrix[i][j] == dp[i + 1][j])
                getWay(i + 1, j, dp, matrix);  // вниз (увеличение индекса строки)
            else getWay(i, j - 1, dp, matrix);  // влево (уменьшение индекса колонки)
        }
        way.add(matrix[i][j]); // сохраняем полученый результат
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {9, 8, 6, 2},
                {10, 11, 13, 11},
                {3, 7, 12, 8},
                {5, 9, 13, 9}
        };
        int[][] res = solve(matrix);
        getWay(0, matrix[0].length - 1, res, matrix);
    }
}
