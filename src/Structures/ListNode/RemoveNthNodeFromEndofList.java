package Structures.ListNode;

import java.util.*;

/*
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/submissions/
 * */
public class RemoveNthNodeFromEndofList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        ListNode res = head;
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int len = list.size();

        if (len - n >= 0) {
            ListNode del = list.get(len - n);
            if (len - n - 1 > 0) {
                ListNode parentOfDel = list.get(len - n - 1);
                parentOfDel.next = del.next != null ? del.next : null;
            } else if (len == n) {
                res = del.next;
            } else {
                if (res != null) res.next = del.next;
            }
        }
        return res;
    }

    private static ListNode build(int[] arr) {
        ListNode head = new ListNode(), next = head;
        for (int i = 0, len = arr.length; i < len; i += 1) {
            next.val = arr[i];
            if (i + 1 < len) {
                next.next = new ListNode();
                next = next.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        int n = 2;
        ListNode res = removeNthFromEnd(build(arr), n);
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
