package Structures.ListNode;

/*
 * https://leetcode.com/problems/add-two-numbers/submissions/
 * */
public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = l1;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            l1.val = a + b + carry;
            carry = l1.val > 9 ? 1 : 0;
            if (carry == 1) l1.val -= 10;
            if (l2 != null) l2 = l2.next;
            if (l1.next == null && carry != 0)
                l1.next = new ListNode();
            else if (l2 != null && l1.next == null)
                l1.next = new ListNode();
            l1 = l1.next;
        }
        return res;
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
        int[] arr1 = {3, 7};
        int[] arr2 = {7, 3};
        ListNode sum = addTwoNumbers(build(arr1), build(arr2));
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
