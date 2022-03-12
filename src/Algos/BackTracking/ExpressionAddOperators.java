package Algos.BackTracking;

import java.util.*;

public class ExpressionAddOperators {
    private static List<Integer> numbers;
    private static List<List<Integer>> numbersToCalculate;

    private static void generateNums(String num, int start) {
        for (int end = start, len = num.length(); end < len; end += 1) {
            int n = Integer.parseInt(num.substring(start, end + 1));
            numbers.add(n); //useNum(n);
            if (end + 1 == len) {
                System.out.println(numbers);
                numbersToCalculate.add(List.copyOf(numbers));
            } else
                generateNums(num, end + 1);
            if (!numbers.isEmpty()) numbers.remove(numbers.size() - 1); // removeLast();
        }
    }

    public static List<String> addOperators(String num, int target) {
        numbers = new ArrayList<>();
        numbersToCalculate = new ArrayList<>();
        String[] ops = {"+", "-", "*"};
        generateNums(num, 0);
        return null;
    }

    public static void main(String[] args) {
        String num = "1234";
        int target = 6;
        List<String> res = addOperators(num, target);
    }
}
