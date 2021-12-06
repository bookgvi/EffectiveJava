package Leetcode.Simple;

import java.util.*;
import java.util.stream.*;

/*
 * We have n chips, where the position of the ith chip is position[i].
 * We need to move all the chips to the same position. In one step, we can change the position of the ith chip from position[i] to:
 * position[i] + 2 or position[i] - 2 with cost = 0.
 * position[i] + 1 or position[i] - 1 with cost = 1.
 * Return the minimum cost needed to move all the chips to the same position.
 *
 *
 * https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/
 * */
public class MinimumCosttoMoveChipstoTheSamePosition {

    public static int minCostToMoveChips(int[] position) {
        List<Pair> coins = new ArrayList<>();
        int minVal = position[0], maxVal = position[0];
        int len = 1;

        Arrays.sort(position);
        int  prev = position[0];
        coins.add(Pair.add(0, position[0]));
        for (int pos : position) {
            if (pos != prev) {
                coins.add(Pair.add(1, pos));
                prev = pos;
                len += 1;
            } else {
                coins.set(len - 1, Pair.add(coins.get(len - 1).value + 1, coins.get(len - 1).pos));
            }
            maxVal = Math.max(maxVal, pos);
            minVal = Math.min(minVal, pos);
        }

        if (coins.size() == 1) return 0;

//        List<Integer> dp = new ArrayList<>();
        int ans = 0, minAns = (int) 1e9 + 2;
        for (int index = 0; index < len; index += 1) {
            for (int i = index + 1, k = 0; k < len; i += 1, k += 1) {
                if (i < len) {
                    int delta = coins.get(i).pos - coins.get(index).pos;
                    if((delta & 1) == 1) ans += coins.get(i).value;
                }
                if (index - 1 - k >= 0) {
                    int delta = coins.get(index).pos - coins.get(index - 1 - k).pos;
                    if ((delta & 1) == 1) ans += coins.get(index - 1 - k).value;
                }
            }
//            dp.add(ans);
            minAns = Math.min(ans, minAns);
            ans = 0;
        }
        return minAns;
    }

    public static void main(String[] args) {
        int[] position = {5,5,2,2};
//        int[] position = {10,3,3,1,6,2,1,10,6,6};
//        int[] position = {1, 1000000000};
//        int[] position = {1, 2, 3};
        int res = minCostToMoveChips(position);
    }

    private static class Pair {
        int value;
        int pos;

        Pair(int first, int second) {
            value = first;
            pos = second;
        }

        static Pair add(int first, int second) {
            return new Pair(first, second);
        }
    }
}
