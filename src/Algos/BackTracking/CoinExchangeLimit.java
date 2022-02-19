package Algos.BackTracking;

import java.util.*;


public class CoinExchangeLimit {
    private static int n, coinsLen, sum = 0;
    private static final List<Integer> nextExchange = new ArrayList<>(), prevIndex = new ArrayList<>();
    private static final List<List<Integer>> exchangeList = new ArrayList<>();

    /*
     * Можно использовать значения из массива coins только один раз
     * Цель - набрать в сумме n
     * */
    public static void main(String[] args) {
        int[] coins = {1, 2, 3, 5, 7};
        coinsLen = coins.length;
        n = 15;
        backTrackCoin(coins);
        int exchangeCount = exchangeList.size();
    }

    private static void backTrackCoin(int[] coins) {
        for (int i = 0; i < coinsLen; i += 1) {
            if (check(coins, i)) {
                store(coins, i);
                if (sum == n)
                    exchangeList.add(List.copyOf(nextExchange));
                else if (sum < n)
                    backTrackCoin(coins);
                remove();
            }
        }
    }

    private static boolean check(int[] coins, int coinNum) {
        if (coins[coinNum] > n) return false;
        boolean ans;
        int sum = 0;
        if (prevIndex.size() > 0 && prevIndex.get(prevIndex.size() - 1) >= coinNum) return false;
        return sum + coins[coinNum] <= n;
    }

    private static void store(int[] coins, int nextCoin) {
        nextExchange.add(coins[nextCoin]);
         prevIndex.add(nextCoin);
        sum += coins[nextCoin];
    }

    private static void remove() {
        int lastCoin = nextExchange.remove(nextExchange.size() - 1);
        prevIndex.remove(prevIndex.size() - 1);
        sum -= lastCoin;
    }
}
