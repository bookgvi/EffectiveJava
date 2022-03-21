package Structures.ListNode;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {3, 6, 12, 3, 1, 0, -10};
        ListNode head = build(arr);
        ListNode sortedList = sort(head);
    }

    private static ListNode sort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = getMid(head);
        return merge(sort(head), sort(mid));
    }

    private static ListNode merge(ListNode l, ListNode r) {
        ListNode merge = new ListNode(), mergeRes = merge;
        while (l != null && r != null) {
            if (l.val < r.val) {
                merge.next = l;
                l = l.next;
            } else {
                merge.next = r;
                r = r.next;
            }
            merge = merge.next;
        }
        merge.next = r != null ? r : l;
        return mergeRes.next;
    }

    private static ListNode getMid(ListNode head) {
        ListNode tmp = null, mid = null;
        while (head != null && head.next != null) {
            tmp = tmp == null ? head : tmp.next;
            head = head.next.next;
        }
        if (tmp != null) {
            mid = tmp.next;
            tmp.next = null;
        }
        return mid;
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
