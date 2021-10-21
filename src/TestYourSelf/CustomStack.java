package TestYourSelf;

import java.util.Arrays;
import java.util.EmptyStackException;

public class CustomStack {

    public static void main(String[] args) {
        push(4);
        push(5);
        push(6);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }

    private static final int CAPACITY = 1;
    private static int size = 0;
    private static Object[] elements = new Object[CAPACITY];

    static void push(Object e) {
        changeCapacity();
        elements[size++] = e;
    }

    static Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object res = elements[--size];
        elements[size] = null;
        return res;
    }

    private static void changeCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}