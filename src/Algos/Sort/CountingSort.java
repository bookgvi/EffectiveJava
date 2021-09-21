package Algos.Sort;

public class CountingSort extends AbstractSort {

    @Override
    public int[] sort(int[] arrOrig) {
        int[] count = new int[100];
        int[] sortedArr = new int[arrOrig.length];
        for (int j : arrOrig) count[j] += 1;
        int k = 0;
        for (int i = 0; i < count.length; i += 1)
            while (count[i]-- > 0) sortedArr[k++] = i;
        return sortedArr;
    }
}
