package Leetcode.Simple;

/*
 * https://leetcode.com/problems/merge-k-sorted-lists/submissions/
 * */
public class MergekSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) return lists[0];
        else if (lists.length == 0) return null;
        int len = lists.length, mid = len >> 1;
        ListNode[] left = new ListNode[mid];
        ListNode[] right = new ListNode[len - mid];
        System.arraycopy(lists, 0, left, 0, mid);
        System.arraycopy(lists, mid, right, 0, len - mid);
        return merge(mergeKLists(left), mergeKLists(right));
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode merge = new ListNode(), res = merge;
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
        return res.next;
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
        int[][] arrays = {{1, 2, 3}, {4, 5, 6}, {1, 3, 6}};
        ListNode[] lists = new ListNode[arrays.length];
        int i = 0;
        for (int[] arr : arrays)
            lists[i++] = build(arr);
        ListNode merge = mergeKLists(lists);
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
