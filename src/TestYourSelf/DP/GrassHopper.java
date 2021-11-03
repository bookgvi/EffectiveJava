package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

public class GrassHopper {
    private static int[] solve1(int k, int[] coins) {
        int len = coins.length;
        int[] d = new int[len], steps = new int[len + 1];
        for (int i = 1; i <= len; i += 1) {
            int maxElt = Math.max(0, i - 1);
            for (int j = Math.max(i - k, 1); j < i; j += 1) {
                if (d[j] > d[maxElt]) maxElt = j;
            }
            if (i < len) d[i] = d[maxElt] + coins[Math.min(i, len - 1)];
            steps[i] = maxElt;
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] coins = new int[]{0, 1, 2, -3, 5, 2, 3};
        int[] res = solve1(3, coins);
        int i = res.length - 1;
        List<Integer> ans = new ArrayList<>();
        do {
            i = res[i];
            ans.add(i);
        } while (i > 0);

        System.out.println(ans);
//        while (i > 1) {
//            System.out.println(res[i]);
//            i -= res[i];
//        }
    }
}
