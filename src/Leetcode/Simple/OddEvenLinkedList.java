package Leetcode.Simple;

import java.util.*;
import java.util.stream.Collectors;

/*
* https://leetcode.com/problems/odd-even-linked-list/
* */
public class OddEvenLinkedList {

    public static class ListNode {
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

    private static void init(ListNode h, List<Integer> arr, int i) {
        if (i >= arr.size()) return;
        h.next = new ListNode(arr.get(i));
        init(h.next, arr, i += 1);
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        int index = 2;
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        oddList.add(head.val);
        if (head.next == null) return head;
        evenList.add(head.next.val);

        ListNode oddHead = new ListNode(head.val);
        ListNode odd = head;
        ListNode even = head.next;
        head = head.next;
        while (head.next != null) {
            if ((index & 1) == 0) {
                odd.next = head.next;
                oddList.add(head.next.val);
                head = head.next;
            } else if ((index & 1) == 1) {
                even.next = head.next;
                evenList.add(head.next.val);
                head = head.next;
            }
            index += 1;
        }
        oddList.addAll(evenList);
        init(oddHead, oddList, 1);

        return oddHead;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.stream(new int[]{1, 2}).boxed().collect(Collectors.toList());
        ListNode head = new ListNode(arr.get(0));
        init(head, arr, 1);
        head = oddEvenList(head);
    }
}
