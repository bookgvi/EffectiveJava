package Algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Coins {
    private static final List<Integer> coins = new ArrayList<>(List.of(1, 2, 5));
    private static final int mainCoin = 10;
    private static final Map<Integer,Boolean> ready = new HashMap<>();
    private static final Map<Integer,Integer> value = new HashMap<>();

    public static void main(String[] args) {
        int res = solveRecursion(mainCoin);
        System.out.println(value);
        value.clear();
        ready.clear();
        Map<Integer, Integer> res2 = solve(mainCoin);
        System.out.println(res2);
    }

    private static int solveRecursion(int inCoin) {
        int result = Integer.MAX_VALUE / 2;
        if (inCoin < 0) return Integer.MAX_VALUE / 2;
        else if (inCoin == 0) return 0;
        else if (ready.containsKey(inCoin) && ready.get(inCoin)) return value.get(inCoin);
        else {
            for(int coin : coins) {
                result = Math.min(result, solveRecursion(inCoin - coin) + 1);
            }
        }
        ready.put(inCoin, true);
        value.put(inCoin, result);
        return result;
    }

    private static Map<Integer, Integer> solve(int inCoin) {
        value.put(0, 0);
        for(int i = 1; i <= inCoin; i++) {
            value.put(inCoin, Integer.MAX_VALUE / 2);
            for(int coin : coins) {
                if (i - coin >= 0) {
                    int val = value.getOrDefault(i - coin, Integer.MAX_VALUE / 2);
                    value.put(i, Math.min(value.getOrDefault(i, Integer.MAX_VALUE / 2), val + 1));
                }
            }
        }
        return value;
    }
}
