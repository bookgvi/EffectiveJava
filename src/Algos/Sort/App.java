package Algos.Sort;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 12, 12, 22, 3, 1, 2, 11, 99, 2, 23, 32 ,5, 4, 4, 6};

        ISort bubble = new BubbleSort();
        ISort select = new SelectSort();
        ISort insert = new InsertSort();
        ISort count = new CountingSort();

        System.out.printf("Before sort: %s\n", Arrays.toString(arr));
        arr = bubble.sort(arr);
        System.out.printf("BubbleSort: %s\n", Arrays.toString(arr));
        arr = select.sort(arr);
        System.out.printf("SelectSort: %s\n", Arrays.toString(arr));
        arr = insert.sort(arr);
        System.out.printf("InsertSort: %s\n", Arrays.toString(arr));
        arr = count.sort(arr);
        System.out.printf("CountingSort: %s\n", Arrays.toString(arr));

    }
}
