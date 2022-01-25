package Leetcode.Simple;

public class ValidMountainArray {
    public static boolean validMountainArray(int[] A) {
        int N = A.length;
        int i = 0;

        // walk up
        while (i+1 < N && A[i] < A[i+1])
            i++;

        // peak can't be first or last
        if (i == 0 || i == N-1)
            return false;

        // walk down
        while (i+1 < N && A[i] > A[i+1])
            i++;

        return i == N-1;
    }

    public static void main(String[] args) {
        int[] arr = {6,7,4};
        boolean res = validMountainArray(arr);
    }
}
