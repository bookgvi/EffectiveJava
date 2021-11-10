package TestYourSelf.DP;

/*
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
public class BestTimetoBuyandSellStock {
    public static int maxProfit(int[] prices) {
        int len = prices.length, max = 0, tmp = 0, minPrice = prices[0];
        for (int i = 0; i < len - 1; i += 1) {
            minPrice = min(prices[i], minPrice);
            tmp = max(prices[i + 1] - minPrice, tmp);
            max = max(tmp, max);
        }
        return max;
    }

    private static int min(int a, int b) {
        return a - ((a - b) & ((b - a) >> 31));
    }

    private static int max(int a, int b) {
        return a - ((a - b) & ((a - b) >> 31));
    }


    public static void main(String[] args) {
        int[] prices = new int[]{1, 2};
//        int[] prices = new int[]{1, 2, 3, 4, 5};
//        int[] prices = new int[]{5, 4, 3, 2, 1};
//        int[] prices = new int[]{7,1,5,3,6,4};
        int res = maxProfit(prices);
    }
}
