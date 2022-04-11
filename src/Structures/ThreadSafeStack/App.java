package Structures.ThreadSafeStack;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        final int THREAD_COUNT = 5;
        List<Integer> arr = List.of(1,2,3,4,5);
        StackBaseLinkedList<Integer> stack = new StackBaseLinkedList<>();
        StackBaseLinkedList<Integer> threadSafeStack = new StackBaseLinkedList<>();
        AtomicInteger v = new AtomicInteger(), v2 = new AtomicInteger();
        arr.parallelStream().forEach(stack::push);
        arr.parallelStream().forEach(i -> stack.pop());
        arr.parallelStream().forEach(threadSafeStack::threadSafePush);
        arr.parallelStream().forEach(i -> threadSafeStack.threadSafePop());
    }
}
