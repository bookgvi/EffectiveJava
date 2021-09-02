package Leetcode.Simple;

public class ReverseInteger {
    public static void main(String[] args) {
        int x = 22;
        System.out.println(Integer.toBinaryString(x));
        System.out.println(Integer.toBinaryString(x >> 1));
        System.out.println(x >> 1);
    }
//    public int reverse(int x) {
//        String strX = Integer.toString(x);
//    }
}
