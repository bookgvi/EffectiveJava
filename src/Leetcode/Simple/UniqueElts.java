package Leetcode.Simple;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class UniqueElts {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr = new ArrayList<>();
        try {
            int count = Integer.parseInt(r.readLine());
            for (int i = 0; i < count; i += 1) {
                arr.add(Integer.parseInt(r.readLine()));
            }
        } catch (Exception ignored) {
        }

        System.out.println(arr);
    }

    private static List<Integer> customSet(List<Integer> arr) {
        int len = arr.size();
        for (int i = 1; i < len; i <<= 1) {
            for (int j = 0; j < len - i; j += i << 1) {
            }
        }
        return null;
    }

    private static List<Integer> solve(List<Integer> arr) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1, len = arr.size() - 1; i < len; i += 1) {
            if (arr.get(i + 1) != arr.get(i)) res.add(i);
        }
        if (res.get(res.size() - 1) != arr.get(arr.size())) res.add(arr.get(arr.size() - 1));
        return res;
    }
}
