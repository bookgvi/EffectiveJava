package Leetcode.Simple;

/*
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/submissions/
 * */
public class RemoveDuplicatesfromSortedListII {

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode senteniel = new ListNode(0, head), prev = senteniel;
        while (head != null) {
            if (head.next != null && head.next.val == head.val) {
                while (head.next != null && head.next.val == head.val)
                    head = head.next;
                prev.next = head.next;
            } else
                prev = prev.next;
            head = head.next;
        }
        return senteniel.next;
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
        int[] arr = {1, 2, 3, 3, 4, 4, 5};
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
