package Algos.BackTracking;

import java.util.*;

public class CoinExchangeUnlimit {
    private static int n, coinsLen, sum;
    private static List<Integer> nextExchange, index;
    private static List<List<Integer>> allExchanges;

    public static void main(String[] args) {
        int[] coins = {2, 3, 5, 10};
        n = 15;
        coinsLen = coins.length;
        nextExchange = new ArrayList<>();
        allExchanges = new ArrayList<>();
        index = new ArrayList<>();
        backtrackExchange(coins);
        int count = allExchanges.size();
    }

    private static void backtrackExchange(int[] coins) {
        int startIndex = index.size() > 0 ? index.get(index.size() - 1) : 0;
        for (int i = startIndex; i < coinsLen; i += 1) {
            if (check(coins, i)) {
                store(coins, i);
                if (sum == n) allExchanges.add(List.copyOf(nextExchange));
                else if (sum < n) backtrackExchange(coins);
                removeLastCoin();
            }
        }
    }

    private static boolean check(int[] coins, int curCoin) {
        if (coins[curCoin] > n) return false;
        if (index.size() > 0 && curCoin < index.get(index.size() - 1)) return false;
        return sum + coins[curCoin] <= n;
    }

    private static void store(int[] coins, int curCoin) {
        nextExchange.add(coins[curCoin]);
        index.add(curCoin);
        sum += coins[curCoin];
    }

    private static void removeLastCoin() {
        int lastCoin = nextExchange.remove(nextExchange.size() - 1);
        index.remove(index.size() - 1);
        sum -= lastCoin;
    }
}
