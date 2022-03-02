package Algos.BackTracking;

import java.util.*;

public class Fragmentation {
    private static final List<List<Integer>> res = new ArrayList<>();
    private static final List<Integer> positions = new ArrayList<>();
    private static int sum = 0;

    public static void main(String[] args) {
        int n = 5;
        backTrack(n, new ArrayList<>());
    }

    private static void backTrack(int n, List<Integer> fragments) {
        for (int i = 1; i <= n; i += 1) {
            if (check(n, i)) {
                place(i, fragments);
                if (sum == n) {
                    res.add(List.copyOf(fragments));
                } else
                    backTrack(n, fragments);
                removeLast(fragments);
            }
        }
    }

    private static boolean check(int n, int num) {
        boolean ans = (sum + num) <= n;
        ans &= !positions.isEmpty() ? positions.get(positions.size() - 1) <= num : ans;
        return ans;
    }

    private static void place(int num, List<Integer> fragments) {
        fragments.add(num);
        positions.add(num);
        sum += num;
    }

    private static void removeLast(List<Integer> fragments) {
        int last = !fragments.isEmpty() ? fragments.remove(fragments.size() - 1) : 0;
        if (!positions.isEmpty()) positions.remove(positions.size() - 1);
        sum -= last;
    }
}
