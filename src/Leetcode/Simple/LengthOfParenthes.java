package Leetcode.Simple;

import java.util.Stack;

public class LengthOfParenthes {
    private static int solve(String seq) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int k = 0;
        for (int i = 0, len = seq.length(); i < len; i += 1) {
            if (seq.charAt(i) == ')') {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else {
                    k = Math.max(k, i - stack.peek());
                }
            } else stack.push(i);
        }
        return k;
    }

    public static void main(String[] args) {
        String seq = "))()((";
        int res = solve(seq);
    }
}
