package Leetcode.Simple;

import java.util.ArrayList;
import java.util.List;

/*
 * Given the head of a linked list and an integer val,
 * remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 * */
public class RemoveLinkedListElements {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode removeElements(ListNode head, int val) {
        ListNode curHead = head, prevHead = head, next;
        while (curHead != null) {
            next = curHead.next;
            if (curHead.val == val && curHead == head) {
                head = curHead.next;
                prevHead = head;
            } else if (curHead.val == val) {
                prevHead.next = next;
            } else {
                prevHead = curHead;
            }
            curHead = next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] head = {1, 1, 1, 2, 6, 3, 4, 5, 6};
        int len = head.length, k = 0;
        List<ListNode> ln = new ArrayList<>();
        ListNode curNode = null;
        for (int i = len - 1; i >= 0; i -= 1) {
            ListNode newNode = new ListNode(head[i], curNode);
            curNode = newNode;
            ln.add(newNode);
        }
        curNode = removeElements(curNode, 1);
        return;
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
