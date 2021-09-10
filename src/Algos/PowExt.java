package Algos;

import java.util.*;

public class PowExt {
    public static void main(String[] args) {
        int n = 2;
        int pow = 25;
        List<Integer> powsOfTwo = getSubSetElem(pow);
        System.out.println(powsOfTwo);
        System.out.println(pow(n, pow));
        System.out.println(pow(n, powsOfTwo));
    }

    private static List<Integer> getSubSetElem(int set) {
        List<Integer> subSetElems = new ArrayList<>();
        for (int i = 0; i < Integer.BYTES * 8; i++) {
            if ((set & (1 << i)) != 0) subSetElems.add(1 << i);
        }
        return subSetElems;
    }

    private static int pow(int n, int pow) {
        int res = 1;
        while(pow > 0) {
            if ((pow & 1) == 1) {
                res *= n;
                pow--;
            }
            n *= n;
            pow >>= 1;
        }
        return res;
    }

    /*
    * Полиноминальный способ возведения в степень, можно использовать совместно с методом
    * получения значений степеней 2 (метод getSubSetElem)
    * */
    private static int pow(int n, List<Integer> powsOfTwo) {
        int res = 1;
        for(int pow : powsOfTwo) {
            res *= pow(n, pow);
        }
        return res;
    }
}
