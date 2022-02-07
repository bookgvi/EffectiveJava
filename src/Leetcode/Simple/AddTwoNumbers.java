package Leetcode.Simple;

import java.util.*;

/*
* https://leetcode.com/problems/add-two-numbers/submissions/
* */
public class AddTwoNumbers {
    private static ListNode arrToList(List<Integer> arr) {
        ListNode head, next;
        int len = arr.size();
        head = new ListNode(arr.get(0));
        if (len > 1) {
            next = new ListNode(arr.get(1));
            head.next = next;
            for (int i = 2; i < len; i += 1) {
                next.next = new ListNode(arr.get(i));
                next = next.next;
            }
        }
        return head;
    }

    private static List<Integer> listToArr(ListNode head) {
        List<Integer> tmp = new ArrayList<>();
        while (head != null) {
            tmp.add(head.val);
            head = head.next;
        }
        return tmp;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> a = listToArr(l1), b = listToArr(l2);
        int len = Math.max(a.size(), b.size());
        for (int i = 0, carry = 0; i < len || carry != 0; i += 1) {
            if (i == a.size()) a.add(0);
            int valB = i < b.size() ? b.get(i) : 0;
            int valA = a.get(i);
            a.set(i, valA + valB + carry);
            carry = a.get(i) > 9 ? 1 : 0;
            if (carry == 1) a.set(i, a.get(i) - 10);
        }
        return arrToList(a);
    }

    public static void main(String[] args) {
        List<Integer> arr1 = List.of(1, 2, 3, 4);
        List<Integer> arr2 = List.of(9, 3, 4);
        ListNode head1 = arrToList(arr1);
        ListNode head2 = arrToList(arr2);
        List<Integer> num = listToArr(head1);
        ListNode res = addTwoNumbers(head1, head2);
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
