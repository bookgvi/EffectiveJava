package Algos;

public class ReverseNums {
    private static int[] reverse(int start, int end, int[] arr) {
        for (int i = start; i <= (start + end) >>> 1; i += 1) {
            int tmp = end + start - i;
            swap(tmp, i, arr);
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int start = 0, end = arr.length - 1;
        arr = reverse(start, end, arr);
    }
}
