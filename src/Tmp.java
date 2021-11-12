public class Tmp {
    private static long fact[] = fact();
    private static String nextPermutation(String str) {
        int len = str.length();
        for (int i = len - 2; i >= 0 ; i -= 1) {
            if (str.charAt(i) < str.charAt(i + 1)) {
                int min = i + 1;
                for (int j = min; j < len; j += 1)
                    if (str.charAt(j) < str.charAt(min) && str.charAt(j) > str.charAt(i))
                        min = j;
                str = swap(i, min, str);
                str = reverse(i + 1, str);
                break;
            }
        }
        return str;
    }

    private static String swap(int pos1, int pos2, String str) {
        StringBuilder tmpStr = new StringBuilder(str);
        tmpStr.replace(pos1, pos1 + 1, Character.toString(str.charAt(pos2)));
        tmpStr.replace(pos2, pos2 + 1, Character.toString(str.charAt(pos1)));
        return tmpStr.toString();
    }

    private static String reverse(int start, String str) {
        StringBuilder tmpStr = new StringBuilder();
        StringBuilder tmpStr2 = new StringBuilder();
        tmpStr.append(str, 0, start);
        tmpStr2.append(str, start, str.length()).reverse();
        tmpStr.append(tmpStr2);
        return tmpStr.toString();
    }

    private static String sort(String strOrig) {
        int len = strOrig.length(), b = 8, dw = 4;
        StringBuilder str = new StringBuilder(strOrig);
        int[] t = new int[len];
        for (int p = 0; p < dw; p += 1) {
            int[] count = new int[1 << b];
            for (int i = 0; i < len; i += 1)
                count[((str.charAt(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)] += 1;
            for (int i = 1; i < 1 << b; i += 1)
                count[i] += count[i - 1];
            for (int i = len - 1; i >= 0; i -= 1)
                t[--count[((str.charAt(i) ^ Integer.MIN_VALUE) >>> (p * b)) & ((1 << b) - 1)]] = str.charAt(i);
            str = new StringBuilder();
            for (int i = 0; i < len; i += 1) {
                str.append(Character.toString(t[i]));
            }
        }
        return str.toString();
    }

    private static long[] fact() {
        int max = 19;
        long[] fact = new long[max];
        fact[0] = 1;
        fact[1] = 1;
        fact[2] = 2;
        for(int i = 3; i < max; i += 1)
            fact[i] = fact[i - 1] * i;
        return fact;
    }

    public static void main(String[] args) {
        String str = "uckf";
        str = sort(str);
        for (int i = 0; i < fact[str.length()]; i += 1) {
            System.out.println(str);
            str = nextPermutation(str);
        }
    }
}
