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
        int[] values = new int[len];
        values[0] = 1;
        for (int i = 1; i < len; i += 1) {
            int counter = 0;
            for (int j = i - 1; j >= 0; j -= 1) {
                if (values[j] > counter && nums[j] < nums[i])
                    counter = values[j];
            }
            values[i] = counter + 1;
        }
        return values;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 4, 2, 5, 6, 3, 7};
        int[] nums = {2, 1, 3, 4, 1, 1};
        int[] res = solve(nums);
    }
}
