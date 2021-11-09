package TestYourSelf.DP;

import java.util.*;
import java.util.stream.*;

public class BackPack {
    private static int[][] solve(int weight, List<Pair<Integer, Integer>> things) {
        int len = things.size();
        int[][] dp = new int[len][weight + 1];
        for (int i = 1; i < len; i += 1) {
            for (int w = 1; w <= weight; w += 1) {
                if (w - things.get(i).first >= 0 && dp[i - 1][w] < dp[i - 1][w - things.get(i).first] + things.get(i).second)
                    dp[i][w] = dp[i - 1][w - things.get(i).first] + things.get(i).second;
                else
                    dp[i][w] = dp[i - 1][w];
            }
        }
        return dp;
    }

    private static List<Pair<Integer, Integer>> getThings(int[][] dp, List<Pair<Integer, Integer>> things, int weight) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        for (int i = things.size() - 1; i > 0; i -= 1) {
            if (dp[i][weight] != dp[i - 1][weight]) {
                res.add(things.get(i));
                weight -= things.get(i).first;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int weight = 5;
        List<Pair<Integer, Integer>> thing = new ArrayList<>();
        thing.add(new Pair<>(0, 0));
        thing.add(new Pair<>(1, 15));
        thing.add(new Pair<>(2, 20));
        thing.add(new Pair<>(3, 20));
//        thing.add(new Pair<>(4, 30));

        int[][] dp = solve(weight, thing);
        List<Pair<Integer, Integer>> seq = getThings(dp, thing, weight);
        System.out.println(seq);
    }

    private static class Pair<V, U> {
        private final V first;
        private final U second;

        Pair(V f, U s) {
            this.first = f;
            this.second = s;
        }
    }
}
