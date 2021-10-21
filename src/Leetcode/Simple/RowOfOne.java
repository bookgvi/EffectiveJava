package Leetcode.Simple;

import java.io.*;
import java.util.*;

public class RowOfOne {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr = new ArrayList<>();
        try {
            int count = Integer.parseInt(r.readLine());
            for (int i = 0; i < count; i += 1) {
                arr.add(Integer.parseInt(r.readLine()));
            }
        } catch (Exception ignored) {
        }
        System.out.println(search(arr));
    }

    private static int search(List<Integer> arr) {
        int len = arr.size(), k = 0, max = 0;
        for (int i = 0; i < len; i += 1) {
            if (arr.get(i) == 1) {
                k = Math.max(arr.get(i), k + arr.get(i));
                max = Math.max(k, max);
            } else {
                k = 0;
            }
        }
        return max;
    }
}
