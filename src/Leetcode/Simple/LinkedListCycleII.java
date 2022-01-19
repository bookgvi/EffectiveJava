package Leetcode.Simple;

/*
* https://leetcode.com/problems/linked-list-cycle-ii/submissions/
* */
public class LinkedListCycleII {
    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private static ListNode build(int[] arr) {
        int n = arr.length;
        ListNode head = null, next = null, cycleStart = null;
        if (n > 0) head = new ListNode(arr[0]);
        if (n > 1) {
            next = head;
            for (int i = 1; i < n; i += 1) {
                next.next = new ListNode(arr[i]);
                next = next.next;
                if (i == 1) cycleStart = next;
            }
        }
        // create cycle
        if (next != null) next.next = cycleStart;
        return head;
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        int[] arr = {3};
        int[] arr = {3,2,0,-4};
        ListNode head = build(arr);
        ListNode cycleStart = detectCycle(head);
    }
}
