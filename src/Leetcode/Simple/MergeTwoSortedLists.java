package Leetcode.Simple;

/*
 * https://leetcode.com/problems/merge-two-sorted-lists/submissions/
 * */
public class MergeTwoSortedLists {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode merge = new ListNode(), mergeRes = merge;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                merge.next = l1;
                l1 = l1.next;
            } else {
                merge.next = l2;
                l2 = l2.next;
            }
            merge = merge.next;
        }
        merge.next = l1 != null ? l1 : l2;
        return mergeRes.next;
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
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {3, 4, 4, 5};
        ListNode l1 = build(arr1), l2 = build(arr2);
        ListNode merge = mergeTwoLists(l1, l2);
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
