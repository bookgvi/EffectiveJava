package Leetcode.Simple;

import java.util.Arrays;

public class KokoEatingBananas {
    private static boolean checkSpeed(int speed, int h, int[] arr) {
        int c = 0, i, len = arr.length;
        for (i = 0; i < len; i += 1) {
            int hours = arr[i] / speed;
            int t = arr[i] % speed == 0 ? hours : hours + 1;
            c += Math.max(t, 1);
            if (c > h) break;
        }
        return c <= h;
    }

    private static int binSearch(int h, int[] arr) {
        int len = arr.length, l = 0, r = arr[len - 1];
        while (r - l > 1) {
            int mid = (r + l) >> 1;
            boolean speed = checkSpeed(mid, h, arr);
            if (speed) r = mid;
            else l = mid;
        }
        return r;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        return binSearch(h, piles);
    }

    public static void main(String[] args) {
//        int[] piles = {30,11,23,4,20};
//        int h = 5;
        int[] piles = {30, 11, 23, 4, 20};
        int h = 6;
//        int[] piles = {3,6,7,11};
//        int h = 8;
        int res = minEatingSpeed(piles, h);
    }
}
