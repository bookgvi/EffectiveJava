package TestYourSelf.DP;

import java.util.*;

public class BackPack {
    private static int[][] solve(int w, List<Pair> things) {
        int count = things.size() - 1;
        int[][] d = new int[count][w + 1];
        List<Integer> first = new ArrayList<>();
        first.add(0);
        d[0][0] = 0;
        for (int i = 1; i < count; i += 1) {
            first.add(0);
            for (int j = 1; j <= w; j += 1) {
                if (j - things.get(i).first >= 0 && d[i - 1][j] < d[i - 1][j - things.get(i).first] + things.get(i).second) {
                    d[i][j] = d[i - 1][j - things.get(i).first] + things.get(i).second;
                } else {
                    d[i][j] = d[i - 1][j];
                }
                first.set(i, d[i][j]);
            }
        }
        return d;
    }

    public static void main(String[] args) {
        Pair t0 = Pair.add(0, 0);
        Pair t1 = Pair.add(6, 30);
        Pair t2 = Pair.add(3, 14);
        Pair t3 = Pair.add(4, 16);
        Pair t4 = Pair.add(2, 9);
        List<Pair> things = List.of(t0, t1, t2, t3, t4);
        int w = 10;
        int[][] res = solve(w, things);
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
