package Algos;

public class DetectCycleII {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        int cycleStartIndex = 1;
        ListNode head = build(arr, cycleStartIndex);
        Pair<Integer, Integer> cycleParams = brentCycle(head);
    }

    private static ListNode build(int[] arr, int cycleStartIndex) {
        int len = arr.length, i = 1;
        ListNode head = new ListNode(arr[0]), next, cycleStart = null;
        next = head;
        while (i < len) {
            next.next = new ListNode(arr[i]);
            next = next.next;
            if (i == cycleStartIndex) cycleStart = next;
            i += 1;
        }
        if (cycleStart != null) next.next = cycleStart;
        return head;
    }

    private static Pair<Integer, Integer> brentCycle(ListNode head) {
        int pow = 1, lambda = pow, mu = 0;
        if (head == null || head.next == null || head.next.next == null) return new Pair<>(-1, -1);
        ListNode fast = head.next.next, slow = head.next;
        while (fast != null && fast != slow) {
            if (pow == lambda) {
                pow <<= 1;
                lambda = 0;
                slow = fast;
            }
            fast = fast.next != null ? fast.next.next : null;
            lambda += 1;
        }
        if (fast == null) return new Pair<>(-1, -1);
        fast = head; slow = head;
        for (int i = 0; i < lambda; i += 1)
            fast = fast.next;
        while(fast != slow) {
            fast = fast.next;
            slow = slow.next;
            mu += 1;
        }
        return new Pair<>(lambda, mu);
    }

    private static class Pair<U, V> {
        private U cycleLength;
        private V cycleStartIndex;

        Pair() {
        }

        Pair(U first, V second) {
            this.cycleLength = first;
            this.cycleStartIndex = second;
        }
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
