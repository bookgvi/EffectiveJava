package Algos.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 12, 12, 22, 3, 1, 2, 11, 99, 2, 23, 32 ,5, 4, 4, 6};

        int arrSize = (int) 7e6;
        int maxValue = Integer.MAX_VALUE / 10 * 9;
        Supplier<int[]> arrSuplier = () -> generateArr(arrSize, maxValue);

        ISort bubble = new BubbleSort();
        ISort select = new SelectSort();
        ISort insert = new InsertSort();
        ISort count = new CountingSort();
        RadixSort radix = new RadixSort();

        System.out.printf("Before sort: %s\n", Arrays.toString(arr));
        arr = bubble.sort(arr);
        System.out.printf("BubbleSort: %s\n", Arrays.toString(arr));
        arr = select.sort(arr);
        System.out.printf("SelectSort: %s\n", Arrays.toString(arr));
        arr = count.sort(arr);
        System.out.printf("CountingSort: %s\n", Arrays.toString(arr));

        IntStream arrStream = Arrays.stream(arrSuplier.get());
        long start = System.nanoTime();
        List<Integer> arrList = arrStream.sorted().boxed().collect(Collectors.toList());
        double elapsedTime = (System.nanoTime() - start) / 1e9;
        System.out.printf("Sort from lib: %.2f seconds for %d elements\n", elapsedTime, arrSize);

        List<Integer> arrToSort = Arrays.stream(arrSuplier.get()).boxed().collect(Collectors.toList());
        start = System.nanoTime();
        arrList = radix.sort(arrToSort);
        elapsedTime = (System.nanoTime() - start) / 1e9;
        System.out.printf("RadixSort (LSB): %.2f seconds for %d elements\n", elapsedTime, arrSize);

        arrToSort = Arrays.stream(arrSuplier.get()).boxed().collect(Collectors.toList());
        start = System.nanoTime();
        arrList = radix.lsbSort(arrToSort);
        elapsedTime = (System.nanoTime() - start) / 1e9;
        System.out.printf("LSB: %.2f seconds for %d elements\n", elapsedTime, arrSize);

//        start = System.nanoTime();
//        arr = insert.sort(arrSuplier.get());
//        elapsedTime = (System.nanoTime() - start) / 1e9;
//        System.out.printf("InsertSort: %.2f seconds for %d elements\n", elapsedTime, arrSize);

    }

    private static int[] generateArr(int size, int maxValue) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i += 1)
            arr[i] = ThreadLocalRandom.current().nextInt(maxValue);
        return arr;
    }
}
