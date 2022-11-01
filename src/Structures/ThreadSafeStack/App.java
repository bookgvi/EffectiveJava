package Structures.ThreadSafeStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        pool.submit(() -> IntStream.range(0, arr.size()).parallel().forEach(i -> {
            Integer val = stack.pop();
            if (val != null) l1.add(val);
        })).get();
        pool.submit(() -> IntStream.range(0, arr.size()).parallel().forEach(i -> l2.add(threadSafeStack.threadSafePop()))).get();
        pool.shutdown();
    }
}
