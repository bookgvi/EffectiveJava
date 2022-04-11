package Structures.ThreadSafeStack;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int THREAD_COUNT = 5;
        ForkJoinPool pool = new ForkJoinPool(THREAD_COUNT);
        List<Integer> arr = List.of(1,2,3,4,5);
        StackBaseLinkedList<Integer> stack = new StackBaseLinkedList<>();
        StackBaseLinkedList<Integer> threadSafeStack = new StackBaseLinkedList<>();
//        AtomicInteger v = new AtomicInteger(), v2 = new AtomicInteger();
        pool.submit(() -> arr.parallelStream().forEach(stack::push)).get();
        pool.submit(() -> arr.parallelStream().forEach(threadSafeStack::threadSafePush)).get();
        pool.submit(() -> arr.parallelStream().forEach(i -> stack.pop())).get();
        pool.submit(() -> arr.parallelStream().forEach(i -> threadSafeStack.threadSafePop())).get();
        pool.shutdown();
    }
}
