package Leetcode.Simple;

/*
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * https://leetcode.com/problems/daily-temperatures/
 * */
public class DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length, si = 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i += 1) {
            while (si + i < len && temperatures[i] >= temperatures[i + si]) si += 1;
            if (si + i < len) {
                res[i] = si;
            }
            si = 1;
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] t = {73, 74, 75, 71, 69, 72, 76, 73};
//        int[] t = {30,40,50,60};
//        int[] t = {30,60,90};
        int[] t = {55,38,53,81,61,93,97,32,43,78};
        int[] res = dailyTemperatures(t);
    }
}
