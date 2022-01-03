package TestYourSelf;

import java.util.*;

public class BracketSequenceII {
    private static boolean isSequence(String seq) {
        Stack<Character> s = new Stack<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
        int len = seq.length();
        for (int i = 0; i < len; i += 1) {
            char ch = seq.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{')
                s.push(ch);
            else {
                if (s.isEmpty()) return false;
                else {
                    char chFromStack = s.pop();
                    if (ch != pairs.get(chFromStack)) return false;
                }
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        String seq = "((}[()[]()]{}))";
        boolean isSeq = isSequence(seq);
    }
}
