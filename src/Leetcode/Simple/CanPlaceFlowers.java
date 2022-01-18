package Leetcode.Simple;

/*
* https://leetcode.com/problems/can-place-flowers/solution/
* */
public class CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length, i = 0;
        while (i < len && n > 0) {
            if (flowerbed[i] == 1) i += 1;
            else {
                if (flowerbed[Math.max(0, i - 1)] != 1 && flowerbed[Math.min(len - 1, i + 1)] != 1) {
                    flowerbed[i] = 1;
                    n -= 1;
                    i += 1;
                }
            }
            i += 1;
        }
        return n == 0;
    }

    public static void main(String[] args) {
        int n = 2;
        int[] arr = {1,0,0,0,1};
        boolean res = canPlaceFlowers(arr, n);
    }
}
