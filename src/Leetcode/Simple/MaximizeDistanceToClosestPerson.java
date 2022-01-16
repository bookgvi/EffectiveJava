package Leetcode.Simple;

/*
* https://leetcode.com/problems/maximize-distance-to-closest-person/submissions/
* */
public class MaximizeDistanceToClosestPerson {
    public static int maxDistToClosest(int[] seats) {
        int n = seats.length, ansPos = 0, maxDist = 0;
        int[] occupied = new int[n];
        for (int i = 1, l = 0, r = 0; i < n; i += 1) {
            if (seats[i] == 0) {
                if (i <= r) occupied[i] = Math.min(occupied[i - l], r - i + 1);
                while (i - occupied[i] >= 0 && i + occupied[i] < n && seats[i - occupied[i]] == 0 && seats[i + occupied[i]] == 0)
                    occupied[i] += 1;
                if (i + occupied[i] - 1 > r) {
                    l = i;
                    r = i + occupied[i] - 1;
                }
            }
        }
        if (seats[0] == 0) {
            int k = 0;
            occupied[0] = 0;
            while (seats[k] != 1) {
                occupied[0] += 1;
                k += 1;
            }
        }
        if (seats[n - 1] == 0) {
            int k = n - 1;
            occupied[n - 1] = 0;
            while(seats[k] != 1) {
                occupied[n - 1] += 1;
                k -= 1;
            }
        }
        for (int i = 0; i < n; i += 1) {
            if (maxDist < occupied[i]) {
                maxDist = occupied[i];
                ansPos = i;
            }
        }
        return maxDist;
    }

    public static void main(String[] args) {
        int[] seats = new int[]{0,0,1};
        int res = maxDistToClosest(seats);
    }
}
