package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * https://leetcode.com/problems/accounts-merge/submissions/
 * */
public class AccountsMerge {
    private static Map<String, List<String>> g;
    private static Set<String> used;

    private static boolean comparator(String str1, String str2) {
        int len1 = str1.length(), len2 = str2.length(), len = Math.min(len1, len2);
        for (int i = 0; i < len; i += 1)
            if (str1.charAt(i) != str2.charAt(i)) return str1.charAt(i) < str2.charAt(i);
        return len1 < len2;
    }

    private static void merge(int l, int mid, int r, List<String> arr, int off) {
        int it1 = 0, it2 = 0;
        List<String> merge = IntStream.range(0, r - l).mapToObj(i -> "").collect(Collectors.toList());
        l += off;
        r += off;
        mid += off;
        while (l + it1 < mid && mid + it2 < r) {
            if (comparator(arr.get(l + it1), arr.get(mid + it2))) {
                merge.set(it1 + it2, arr.get(l + it1));
                it1 += 1;
            } else {
                merge.set(it1 + it2, arr.get(mid + it2));
                it2 += 1;
            }
        }
        while (l + it1 < mid) {
            merge.set(it1 + it2, arr.get(l + it1));
            it1 += 1;
        }
        while (mid + it2 < r) {
            merge.set(it1 + it2, arr.get(mid + it2));
            it2 += 1;
        }
        for (int i = 0; i < it1 + it2; i += 1)
            arr.set(l + i, merge.get(i));
    }

    private static void mergeSort(int start, int end, List<String> arr) {
        end -= start;
        for (int i = 1; i < end; i <<= 1)
            for (int j = 0; j < end - i; j += i << 1)
                merge(j, i + j, Math.min(end, j + (i << 1)), arr, start);
    }

    private static Map<String, List<String>> build(List<List<String>> A) {
        for (List<String> emails : A) {
            for (int i = 2; i < emails.size(); i += 1) {
                List<String> v = g.getOrDefault(emails.get(i - 1), new ArrayList<>());
                v.add(emails.get(i));
                g.putIfAbsent(emails.get(i - 1), v);

                v = g.getOrDefault(emails.get(i), new ArrayList<>());
                v.add(emails.get(i - 1));
                g.putIfAbsent(emails.get(i), v);
            }
        }
        return g;
    }

    private static void dfs(String email, List<List<String>> ans) {
        used.add(email);
        ans.get(ans.size() - 1).add(email);
        for (String nextEmail : g.getOrDefault(email, new ArrayList<>())) {
            if (!used.contains(nextEmail)) {
                dfs(nextEmail, ans);
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        g = new HashMap<>();
        used = new HashSet<>();
        List<List<String>> ans = new ArrayList<>();
        build(accounts);
        for (List<String> emails : accounts) {
            if (!used.contains(emails.get(1))) {
                ans.add(new ArrayList<>(List.of(emails.get(0))));
                dfs(emails.get(1), ans);
                mergeSort(1, ans.get(ans.size() - 1).size(), ans.get(ans.size() - 1));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<String>> A = List.of(
                List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com")
        );
        List<List<String>> ans = accountsMerge(A);
    }
}
