package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;

/*
 * https://leetcode.com/problems/sort-list/submissions/
 * */
public class SortList {
    private static final int SIZE = (int) 1e4 + 1;

    public static void main(String[] args) {
        int[] nums = {-1, 5, 3, 4, 0};
        ListNode head = buildList(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        ListNode newHead = sortList(head);
    }

    public static List<Integer> sort(List<Integer> arr) {
        int len = arr.size(), b = 8, dw = 4;
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int elt : arr)
                count[((elt ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((arr.get(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = arr.get(i);
            arr = new ArrayList<>();
            for (int i = 0; i < len; i += 1)
                arr.add(t[i]);
        }
        return arr;
    }


    private static ListNode buildList(List<Integer> nums) {
        ListNode head = new ListNode(), next = head;
        for (int i = 0, len = nums.size(); i < len; i += 1) {
            next.val = nums.get(i);
            if (i + 1 < len) {
                next.next = new ListNode();
                next = next.next;
            }
        }
        next.next = null;
        return head;
    }

    private static List<Integer> buildArr(ListNode head) {
        List<Integer> arr = new ArrayList<>();
        while (head != null) {
            arr.add(head.val);
            head = head.next;
        }
        return arr;
    }

    public static ListNode sortList(ListNode head) {
        List<Integer> arr = buildArr(head);
        if (arr.size() == 0) return null;
        arr = sort(arr);
        return buildList(arr);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
