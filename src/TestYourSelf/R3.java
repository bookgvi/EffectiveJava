package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class R3 {

    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 7};
        int n = 15;
        List<Integer> first = solve(n, coins);
        while (n > 0) {
            System.out.println(first.get(n));
            n -= first.get(n);
        }
    }

    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(i);
            values.add(Integer.MAX_VALUE / 4 * 3);
            for (int coin : coins) {
                if (i - coin >= 0 && (values.get(i - coin) + 1) - values.get(i) < 0) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }
}
