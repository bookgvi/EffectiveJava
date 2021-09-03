package Leetcode.Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ReverseInteger {
    public static void main(String[] args) {
        int x = (1 << 31) - 1;
//        int x = -(1 << 31);
//        int x = 120;
//        int x = 0;
//        int x = -321;
        System.out.println(x);
        System.out.println(reverseBrute(x));
    }

    public static int reverseBrute(int x) {
        List<Integer> arrX = new ArrayList<>();
        while (x != 0) {
            int modX = x % 10;
            arrX.add(modX);
            x -= modX;
            x /= 10;
        }
        try {
//            int res = arrX.stream().reduce(0, (cur, prev) -> cur * 10 + prev);
            return arrX.stream().reduce(0, (cur, prev) -> cur * 10 + prev);
        } catch (Exception ex) {
            return 0;
        }
    }
}
