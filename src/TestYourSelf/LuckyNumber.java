package TestYourSelf;

public class LuckyNumber {
    private static int nextNum(int n) {
        int d = 0, base = 10;
        while(n > 0) {
            int tmp = n % base;
            d += tmp * tmp;
            n /= base;
        }
        return d;
    }

    /*
    * lambda - длина цикла
    * */
    private static boolean isHappy(int n) {
        int fast = nextNum(n), slow = n, pow = 1, lambda = pow;
        while (fast != 1 && fast != slow) {
            if (pow == lambda) {
                pow <<= 1;
                lambda = 0;
                slow = fast;
            }
            fast = nextNum(fast);
            lambda += 1;
        }

        // next code is not necessary for current task
        fast = n;
        slow = n;
        for (int i = 0; i < lambda; i += 1)
            fast = nextNum(fast);
        while (fast != slow) {
            slow = nextNum(slow);
            fast = nextNum(fast);
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        int n = 1111111;
        boolean isHappy = isHappy(n);
    }
}
