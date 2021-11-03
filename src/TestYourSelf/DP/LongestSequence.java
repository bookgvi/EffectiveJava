package TestYourSelf.DP;

public class LongestSequence {
    /*
    * nums = {2, 1, 3, 4, 1, 1}
    * values = {0, 0, 0, 0, 0, 0}
    * counter = 0, i = 1, j = 0
    * nums[i] = 1, nums[j] = 2
    *
    * */
    private static int[] solve(int[] nums) {
        int len = nums.length;
        int[] d = new int[len];
        d[0] = 1;
        for (int i = 0; i < len; i += 1) {
            int maxIndex = 0;
            for (int j = i - 1; j >= 0; j -= 1) {
                if (d[j] > maxIndex && nums[j] < nums[i])
                    maxIndex = d[j];
            }
            d[i] = maxIndex + 1;
        }
        return d;
    }

    public static void main(String[] args) {
        int[] nums = {1, 4, 2, 5, 6, 3, 7};
//        int[] nums = {2, 1, 3, 4, 1, 1};
        int[] res = solve(nums);
    }
}
