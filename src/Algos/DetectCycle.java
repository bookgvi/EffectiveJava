package Algos;


public class DetectCycle {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int firstCycleVal = arr[1];
        ListNode head = Util.build(arr, firstCycleVal), cycleStartF = null, cycleStartB = null;
        if (head != null) {
            cycleStartF = Util.floydModify(head);
            int lambda = Util.lambda;
            cycleStartB = Util.brent(head);
            lambda = Util.lambda;
            int mu = Util.mu;
        }
    }

    private static class Util {
        private static int lambda = 0, mu = -1; // lambda - длина цикла, mu - индекс первого элемента в цикле

        private static ListNode build(int[] arr, int firstCycleVal) {
            int len = arr.length;
            if (len < 1) return null;
            ListNode head = new ListNode(arr[0]), next = null, cycleStart = null;
            if (len > 1) {
                next = head;
                for (int i = 1; i < len; i += 1) {
                    next.next = new ListNode(arr[i]);
                    next = next.next;
                    if (next.val == firstCycleVal) cycleStart = next;
                }
                next.next = cycleStart;
            }
            return head;
        }

        private static ListNode floyd(ListNode head) {
            ListNode fast = head.next.next, slow = head.next;
            while (slow != null && fast != null) {
                slow = slow.next;
                if (fast.next != null) {
                    fast = fast.next.next;
                    if (slow == fast) {
                        slow = head;
                        while (slow != fast) {
                            slow = slow.next;
                            if (fast != null) fast = fast.next;
                        }
                        return slow;
                    }
                }
            }
            return null;
        }

        private static ListNode floydModify(ListNode head) {
            ListNode fast = head.next.next, slow = head.next;
            lambda = 1;
            while (slow != null && fast != null && slow != fast) {
                slow = slow.next;
                if (fast.next != null) fast = fast.next.next;
                lambda += 1;
            }
            if (fast != null && fast.next != null && slow == fast) {
                slow = head;
                while (fast != null && slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
            return null;
        }

        private static ListNode brent(ListNode head) {
            ListNode fast = head.next, slow = head;
            int pow = 1;
            lambda = pow;
            while (fast != null && slow != fast) {
                if (pow == lambda) {  // время начать новую степень двойки?
                    pow <<= 1;
                    slow = fast;
                    lambda = 0;
                }
                fast = fast.next;
                lambda += 1;
            }

            // Находим позицию первого повторения длины λ
            if (fast != null && fast.next != null) {
                fast = head;
                slow = head;
                for (int i = 0; i < lambda; i += 1)
                    if (fast != null) fast = fast.next;
            //расстояние между черепахой и зайцем теперь равно λ.
                mu = 0;
                // Теперь черепаха и заяц движутся с одинаковой скоростью, пока не встретятся
                while (fast != null && slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                    mu += 1;
                }
                return slow;
            }
            lambda = -1;
            return null;
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
