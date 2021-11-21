package Algos.Dynamic;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    private static int[][] solve(int weight, List<Pair> things) {
        int count = things.size();
        int[][] dp = new int[count][weight + 1];
        dp[0][0] = 0;
        for (int i = 1; i < count; i += 1) {
            for (int w = 1; w <= weight; w += 1) {
                if (w - things.get(i).first >= 0 && dp[i - 1][w] < dp[i - 1][w - things.get(i).first] + things.get(i).second)
                    dp[i][w] = dp[i - 1][w - things.get(i).first] + things.get(i).second;
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp;
    }

    public static void main(String[] args) {
        Pair t0 = Pair.add(0, 0);
        Pair t1 = Pair.add(4, 16);
        Pair t2 = Pair.add(6, 30);
        Pair t3 = Pair.add(3, 14);
        Pair t4 = Pair.add(2, 9);
        List<Pair> things = List.of(t0, t1, t2, t3, t4);
        int w = 10;
        int[][] res = solve(w, things);
        List<Integer> seq = new ArrayList<>();
        for (int i = things.size() - 1; i > 0; i -= 1) {
            if (res[i][w] != res[i - 1][w]) {
                seq.add(i);
                w -= things.get(i).first;
            }
        }
        System.out.println(seq);
    }

    private static class Pair {
        int first;
        int second;

        public static Pair add(int first, int second) {
            return new Pair(first, second);
        }

        private Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
