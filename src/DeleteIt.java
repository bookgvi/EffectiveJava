import java.time.LocalDate;
import java.time.MonthDay;
import java.util.*;

public class DeleteIt {

    private static Pair<Integer, Integer> detectCycle(ListNode head) {
        if (head == null || head.next == null) return new Pair<>(0, 0);
        int pow = 1, mu = 0, lambda = pow;
        ListNode slow = head.next, fast = head.next.next;
        while (fast != slow) {
            if (lambda == pow) {
                slow = fast;
                pow <<= 1;
                lambda = 0;
            }
            fast = fast.next;
            lambda += 1;
        }
        slow = head;
        fast = head;
        for (int i = 0; i < lambda; i += 1)
            fast = fast.next;
        while (fast != slow) {
            mu += 1;
            fast = fast.next;
            slow = slow.next;
        }
        return new Pair<>(lambda, mu);
    }

    private static ListNode build(int[] arr) {
        return build(arr, -1);
    }

    private static ListNode build(int[] arr, int indexToCycle) {
        ListNode head = new ListNode(), next = head, cycleStart = null;
        for (int i = 0, len = arr.length; i < len; i += 1) {
            next.val = arr[i];
            if (indexToCycle == i)
                cycleStart = next;
            if (i + 1 < len) {
                next.next = new ListNode();
                next = next.next;
            }
        }
        if (cycleStart != null)
            next.next = cycleStart;
        return head;
    }

    private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private static double getEarn(double sum, int n, double percent) {
        LocalDate date = LocalDate.now();
        if (n <= 0) return sum;
        int days = daysInMonth[date.getMonthValue()];
        double p = percent / 365 * 27;
        sum += sum * p / 100;
        return getEarn(sum, n - days, percent);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        ListNode head = build(arr, 2);
        Pair<Integer, Integer> lambdaMu = detectCycle(head);
        double res = getEarn(1000.00, 364, 9);

        int[] arr1 = {-1, 2, 4, 5}, arr2 = {3, 3, 5}, arr3 = {2, 3, 4, 5, 6};
        int i = 0, j = 0, k = 0;
        Integer ans = null;
        while (i < arr1.length) {
            while (j + 1 < arr2.length && arr1[i] > arr2[j]) j += 1;
            while (k + 1 < arr3.length && arr1[i] > arr3[k]) k += 1;
            if (arr1[i] == arr2[j] && arr1[i] == arr3[k]) {
                ans = arr1[i];
                break;
            }
            i += 1;
        }
    }

    private static class ListNode {
        private int val;
        private ListNode next;

        ListNode() {
            val = 0;
            next = null;
        }

        ListNode(int val) {
            this.val = val;
            next = null;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static class Pair<U, V> {
        private U lambda;
        private V mu;

        Pair(U u, V v) {
            this.lambda = u;
            this.mu = v;
        }
    }
}
