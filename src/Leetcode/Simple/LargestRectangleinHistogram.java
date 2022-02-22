package Leetcode.Simple;

import java.util.*;

public class LargestRectangleinHistogram {
    public static void main(String[] args) {
        int[] heights = {1};
        int res = SolutionI.largestRectangleArea(heights);
    }

    /*
     * TLE
     * */
    private static class SolutionI {
        private static List<Integer> rectangles;

        public static int largestRectangleArea(int[] heights) {
            rectangles = new ArrayList<>();
            calcNextPosition(heights, 0);
            int max = heights[0];
            for (int squares : rectangles)
                max = Math.max(max, squares);
            return max;
        }

        private static void calcNextPosition(int[] heights, int pos) {
            int f = pos, b = pos, len = heights.length, it1 = pos, it2 = pos;
            while (it1 < len || it2 >= 0) {
                if (f + 1 < len && heights[pos] <= heights[f + 1]) f += 1;
                if (b - 1 >= 0 && heights[pos] <= heights[b - 1]) b -= 1;
                it1 += 1;
                it2 -= 1;
            }
            int l = Math.min(pos, b);
            int r = Math.max(pos, f);
            int square = heights[pos] * (r - l + 1);
            rectangles.add(square);
            if (pos + 1 < len)
                calcNextPosition(heights, pos + 1);
        }
    }
}