package Leetcode.Simple;

/*
 * https://leetcode.com/problems/swap-nodes-in-pairs/submissions/
 * */
public class SwapNodesInPairs {
    private static ListNode build(int[] arr) {
        int len = arr.length;
        if (len < 1) return null;
        ListNode head = new ListNode(arr[0]), next = head;
        for (int i = 1; i < len; i += 1) {
            next.next = new ListNode(arr[i]);
            next = next.next;
        }
        return head;
    }


    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = new ListNode(head.next.val, head), parent = head.next, newNext = new ListNode(-1);
        head = head.next.next;
        newHead.next.next = head != null ? newNext : null;
        while (head != null && head.next != null) {
            newNext.val = head.next.val;
            newNext.next = head;
            parent = head;
            head = head.next.next;
            newNext = newNext.next.next;
        }
        if (head != null) {
            newNext.val = head.val;
            newNext.next = null;
        } else {
            parent.next = null;
        }
        return newHead;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode head = build(arr);
        ListNode res = swapPairs(head);
    }

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
}
