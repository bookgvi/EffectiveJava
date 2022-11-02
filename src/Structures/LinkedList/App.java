package Structures.LinkedList;

public class App {
    public static void main(String[] args) {
        Node<Integer> head = new Node<>(1);
        head.addToTail(2);
        head.addToTail(3);
        head.addToTail(4);

        Node<Integer> newHead = head.delete(6);
        Node<Integer> newList = head.splitFirst(3);
        Node<Integer> newList2 = head.splitLast(3);
        System.out.println("FIN");
    }
}
