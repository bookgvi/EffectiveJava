package TestYourSelf.DP;

import java.util.*;

public class CoinsGreedy {
    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        sort(coins);
        while (n > 0) {
            int finalN = n;
            coins = Arrays.stream(coins).filter(coin -> finalN - coin >= 0).toArray();
            int maxCoin = coins[coins.length - 1];
            n -= maxCoin;
            first.add(maxCoin);
        }
        return first;
    }

    private static void sort(int[] arr) {
        int len = arr.length, b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr[i] ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr[i];
            System.arraycopy(t, 0, arr, 0, len);
        }
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 5, 10, 25};
        int n = 17;
        List<Integer> res = solve(n, coins);
    }
}
