package TestYourSelf.DP;

import java.util.ArrayList;
import java.util.List;

/*
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of the stock at any time.
 * However, you can buy it then immediately sell it on the same day.
 * Find and return the maximum profit you can achieve.
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 */
public class BestTimetoBuyandSellStockII {
    public static int maxProfit(int[] prices) {
        int len = prices.length, sum = 0;
        for (int i = 1; i < len; i += 1) {
            sum += Math.max(prices[i] - prices[i - 1], 0);
        }
        return sum;
    }


    public static void main(String[] args) {
//        int[] prices = new int[]{1, 2, 3, 4, 5};
        int[] prices2 = new int[]{5, 4, 3, 2, 1};
//        int[] prices3 = new int[]{7,1,5,3,6,4};
//        int res = maxProfit(prices);
        int res2 = maxProfit(prices2);
//        int res3 = maxProfit(prices3);
    }
}
