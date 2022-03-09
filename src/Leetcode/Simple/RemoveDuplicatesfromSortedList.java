package Leetcode.Simple;

/*
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/submissions/
 * */
public class RemoveDuplicatesfromSortedList {
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode sentinel = new ListNode(0, head), prev = sentinel;
        while (head != null) {
            while (head.next != null && head.next.val == head.val)
                head = head.next;
            prev.next = head;
            prev = prev.next;
            head = head.next;
        }
        return sentinel.next;
    }

    private static ListNode build(int[] arr) {
        ListNode sentinel = new ListNode(), next = sentinel;
        for (int i = 0, len = arr.length; i < len; i += 1) {
            next.val = arr[i];
            if (i + 1 < len) {
                next.next = new ListNode();
                next = next.next;
            }
        }
        return sentinel;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3, 3, 4};
        ListNode head = build(arr);
        ListNode newHead = deleteDuplicates(head);
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
