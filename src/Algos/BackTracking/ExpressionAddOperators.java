package Algos.BackTracking;

import java.util.*;

public class ExpressionAddOperators {
    private static List<Integer> numbers;
    private static List<List<Integer>> numbersToCalculate;
    private static List<Integer> preCalc;
    private static List<String> res;
    private static StringBuilder action;
    private static Map<String, Integer> opUsedCounter;

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

    private static boolean canUseDigit() {
        return true;
    }

    private static void saveAction(String op, int num) {
        preCalc.add(num);
        if (action.length() > 0) {
            char lastCh = action.charAt(action.length() - 1);
            if (lastCh >= '0' && lastCh <= '9') action.append(op).append(num);
            else action.append(num).append(op);
        } else action.append(num).append(op);
    }

    private static void removeLast() {
        preCalc.remove(preCalc.size() - 1);
        if (action.length() > 0) action.deleteCharAt(action.length() - 1);
        if (action.length() > 0) action.deleteCharAt(action.length() - 1);
    }

    private static boolean isFinish(List<Integer> numbers) {
        return preCalc.size() == numbers.size();
    }

    private static boolean canUseOp(String op, List<Integer> numbers) {
        return opUsedCounter.getOrDefault(op, 0) <= numbers.size() - 1;
    }

    private static void useOp(String op) {
        opUsedCounter.put(op, opUsedCounter.getOrDefault(op, 0) + 1);
    }

    private static void removeOp(String op) {
        int newVal = opUsedCounter.getOrDefault(op, 0);
        opUsedCounter.put(op, newVal == 0 ? 0 : newVal - 1);
    }

    private static void backTrack(List<Integer> numbers, String[] ops, int target, int start) {
        for (String op : ops) {
            if (canUseOp(op, numbers)) {
                useOp(op);
                for (int end = start, len = numbers.size(); end < len; end += 1) {
                    if (canUseDigit()) {
                        saveAction(op, numbers.get(end));
                        if (isFinish(numbers)) {
                            char lastCh = action.charAt(action.length() - 1);
                            if (lastCh < '0' || lastCh > '9') action.deleteCharAt(action.length() - 1);
                            res.add(action.toString());
                        } else
                            backTrack(numbers, ops, target, end + 1);
                        removeLast();
                    }
                }
            }
        }
    }

    public static List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        numbers = new ArrayList<>();
        numbersToCalculate = new ArrayList<>();
        preCalc = new ArrayList<>();

        String[] ops = {"+", "-"};
        generateNums(num, 0);

        for (List<Integer> numbers : numbersToCalculate) {
            if (numbers.size() < 2) continue;
            action = new StringBuilder();
            opUsedCounter = new HashMap<>();
            backTrack(numbers, ops, target, 0);
        }
        return null;
    }

    public static void main(String[] args) {
        String num = "1234";
        int target = 6;
        List<String> res = addOperators(num, target);
    }
}
