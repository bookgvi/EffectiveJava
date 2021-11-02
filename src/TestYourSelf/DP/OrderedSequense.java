package TestYourSelf.DP;

public class OrderedSequense {
    private static int[] solve(String str) {
        int len = str.length();
        int[] d = new int[len];
        d[0] = 1;
        for (int i = 1; i < len; i += 1) {
            d[i] = 1;
            if (str.charAt(i) - str.charAt(i - 1) == 1)
                d[i] = d[i - 1] + 1;
        }
        return d;
    }

    public static void main(String[] args) {
        String str = "aabcfedefgrr";
        int[] res = solve(str);
    }
}
