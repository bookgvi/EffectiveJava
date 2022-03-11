package Structures.ListNode;

/*
 * https://leetcode.com/problems/rotate-list/submissions/
 * */
public class RotateList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int len = 1, newHeadPosition;
        ListNode hewHead, origHead = head;
        while (head.next != null) {         // get the list length
            len += 1;
            head = head.next;
        }
        newHeadPosition = len - (k % len);  // calculate th position for new head
        head.next = origHead;               // cycled the list!!!
        len = 0;
        while (len != newHeadPosition) {    // traverse to the new tail
            head = head.next;
            len += 1;
        }
        hewHead = head.next;                // set new head
        head.next = null;                   // break the cycle
        return hewHead;
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
//        int[] arr = {1,2,3,4,5};
//        int k = 2;
//        int[] arr = {0, 1, 2};
//        int k = 4;
        int[] arr = {1, 2};
        int k = 3;
        ListNode head = rotateRight(build(arr), k);
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
