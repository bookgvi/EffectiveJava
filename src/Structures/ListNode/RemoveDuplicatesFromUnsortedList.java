package Structures.ListNode;

public class RemoveDuplicatesFromUnsortedList {

    private static ListNode getSet(ListNode head) {
        ListNode sentinel = new ListNode(0, head), prev = sentinel;
        while (head != null) {
            while (head.next != null && head.val == head.next.val)
                head = head.next;
            prev.next = head;
            prev = prev.next;
            head = head.next;
        }
        return sentinel.next;
    }

    private static ListNode getUniqueOnly(ListNode head) {
        ListNode sentinel = new ListNode(0, head), prev = sentinel;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val)
                    head = head.next;
                prev.next = head.next;
            } else
                prev = prev.next;
            head = head.next;
        }
        return sentinel.next;
    }

    private static ListNode sort(ListNode node) {
        if (node == null || node.next == null) return node;
        ListNode mid = divideList(node);
        return merge(sort(node), sort(mid));
    }

    private static ListNode merge(ListNode left, ListNode right) {
        ListNode merge = new ListNode(), mergeRes = merge;
        while (left != null && right != null) {
            if (left.val < right.val) {
                merge.next = left;
                left = left.next;
            } else {
                merge.next = right;
                right = right.next;
            }
            merge = merge.next;
        }
        merge.next = right != null ? right : left;
        return mergeRes.next;
    }

    private static ListNode divideList(ListNode head) {
        ListNode prev = null, mid = null;
        while (head != null && head.next != null) {
            prev = prev == null ? head : prev.next;
            head = head.next.next;
        }
        if (prev != null) {
            mid = prev.next;
            prev.next = null;
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

    public static void main(String[] args) {
        int[] arr = {2, 5, 4, 1, 1, 9};
        ListNode head = build(arr);
        ListNode sortedList = sort(head);
        ListNode uniqueOnly = getUniqueOnly(sortedList);
        ListNode set = getSet(sortedList);
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