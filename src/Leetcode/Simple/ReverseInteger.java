package Leetcode.Simple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ReverseInteger {
    public static void main(String[] args) {
//        int x = -1;
//        int x = 5;
//        int x = 1;
        int x = (1 << 30);
//        int x = -(1 << 31);
//        int x = -(1 << 2);
//        int x = 120;
//        int x = 0;
//        int x = -321;
        System.out.println(x);
        System.out.println(reverseBrute(x));
//        System.out.println(reverse(x));
    }

    public static int reverseBrute(int x) {
        List<Integer> arrX = new ArrayList<>();
        final int DEFAULT = 0;
        int res = DEFAULT;
        while (x != 0) {
            int modX = x % 10;
            arrX.add(modX);
            x -= modX;
            x /= 10;
        }
        try {
            if (arrX.size() > 0) res = arrX.stream().reduce(0, (cur, prev) -> cur * 10 + prev);
            return res;
        } catch (Exception ex) {
            return DEFAULT;
        }
    }

    public static int reverse(int x) {
        int res;
        int res1 = 0;
        List<Integer> intToBit = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((x & (1 << i)) != 0) {
                intToBit.add(i);
            }
            res1 = Integer.BYTES * 8 | (1 << i);
        }
        res = Integer.reverse(x);
        res1 = (x & 0b1010) << 1 | (x >>> 1) & 0x55555555;
        ;
        System.out.printf("res1 = %d\n", res1);
        System.out.printf("intToBit = %s\n", intToBit);
        return res;
    }
}
