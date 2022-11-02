package Structures.LinkedList;

import java.util.Objects;

class Node<T> {
    private final T data;
    private Node<T> next;
    private Pair<Node<T>, Node<T>> dividedList;

    Node(T data) {
        this.data = data;
        this.next = null;
    }

    Node<T> addToTail(T data) {
        Node<T> newNode = new Node<T>(data);
        Node<T> cur = this;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
        return this;
    }

    Node<T> delete(T data) {
        return delete(this, data);
    }

    /**
     * Delete node
     *
     * @param head = Node to start finder
     * @param data = data to find
     * @return Node - new head Node
     */
    Node<T> delete(Node<T> head, T data) {
        if (Objects.equals(head.data, data)) {
            Node<T> next = head.next;
            head.next = null;
            return next;
        }
        Pair<Node<T>, Node<T>> prevAndCurNodes = findNode(head, data);
        if (prevAndCurNodes.getSecond() != null) {
            prevAndCurNodes.getFirst().next = (prevAndCurNodes.getSecond().next);
        }
        return head;
    }

    Node<T> splitFirst(T data) {
        if (this.dividedList == null) split(data);
        return dividedList.getFirst();

    }
    Node<T> splitLast(T data) {
        if (this.dividedList == null) split(data);
        return dividedList.getSecond();
    }

    private Pair<Node<T>, Node<T>> split(T data) {
        Node<T> head = this;
        Pair<Node<T>, Node<T>> prevAndCurNodes = findNode(head, data);
        Node<T> prev = prevAndCurNodes.getFirst();
        if (prev != null) prev.next = null;
        if (prevAndCurNodes.getSecond() == null) {
            prevAndCurNodes.setSecond(head);
        }
        prevAndCurNodes.setFirst(head);
        this.dividedList = new Pair<>(prevAndCurNodes.getFirst(), prevAndCurNodes.getSecond());
        return prevAndCurNodes;
    }

    private Pair<Node<T>, Node<T>> findNode(Node<T> head, T data) {
        Node<T> prev = null;
        Node<T> cur = head;
        Pair<Node<T>, Node<T>> res = new Pair<>(prev, cur);
        do {
            if (cur.data == data) {
                return res;
            }
            prev = cur;
            cur = cur.next;
            res.setFirst(prev);
            res.setSecond(cur);
        } while (cur.next != null);
        res.setFirst(null);
        res.setSecond(null);

        return res;
    }

    private static class Pair<U, V> {
        private U first;
        private V second;

        Pair(U f, V s) {
            this.first = f;
            this.second = s;
        }

        U getFirst() {
            return first;
        }

        void setFirst(U first) {
            this.first = first;
        }

        V getSecond() {
            return second;
        }

        void setSecond(V second) {
            this.second = second;
        }
    }
}
