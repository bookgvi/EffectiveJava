package TestYourSelf;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class R3 {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 3, 5, 10, 25, 50};
        int n = 43, k = 17, a = 5;
        List<Integer> first = solve(n, coins);
//        while (k > 0) {
//            System.out.printf("%d ", first.get(k));
//            k -= first.get(k);
//        }
//        System.out.println();
        int tmp = modPow(a, n - 1, n);
        String isPrime = tmp == 1 ? "простое" : "составное";
        System.out.printf("%d - %s\n", n, isPrime);

        // Проверка Рабина-Миллера на простоту числа n
        List<Integer> rabinMiller = multipliers(a, k, n);
//        int i = searchOne(rabinMiller);
        System.out.println(rabinMiller);
//        if (i > 0) {
//            System.out.printf("%d, %d\n", rabinMiller[i - 1], rabinMiller[i - 1] % n);
//        }

//        System.out.println(erat(n + 1));
        int index = search(n, erat(n + 1));
        if (index == -1) System.out.printf("%d не простое\n", n);
        else System.out.printf("%d - простое\n", n);
    }

    private static List<Integer> solve(int n, int[] coins) {
        List<Integer> first = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        first.add(0);
        values.add(0);
        for (int i = 1; i <= n; i += 1) {
            first.add(0);
            values.add(Integer.MAX_VALUE / 4 * 3);
            for (int coin : coins) {
                if (i - coin >= 0 && values.get(i - coin) + 1 < values.get(i)) {
                    values.set(i, values.get(i - coin) + 1);
                    first.set(i, coin);
                }
            }
        }
        return first;
    }

    private static int phi(int n) {
        int res = n;
        for (int i = 2; i * i <= n; i += 1) {
            if (n % i == 0) {
                while (n % i == 0) n /= i;
                res -= res / i;
            }
        }
        if (n > 1) res -= res / n;
        return res;
    }

    private static int modPow(int n, int pow, int mod) {
        int res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = (res * n) % mod;
            n = (n * n) % mod;
            pow >>= 1;
        }
        return res;
    }

    private static int pow(int n, int pow) {
        int res = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) res *= n;
            n *= n;
            pow >>= 1;
        }
        return res;
    }

    private static int[] evklidExt(int a, int b) {
        int[] res = new int[3];
        if (a == 0) {
            res[0] = b;
            res[2] = 1;
            return res;
        }
        res = evklidExt(b % a, a);
        int tmp = res[1];
        res[1] = res[2] - (b / a) * res[1];
        res[2] = tmp;
        return res;
    }

    private static List<Integer> erat(int n) {
        List<Integer> primes = new ArrayList<>();
        Map<Integer, Boolean> erat = new HashMap<>();
        for (int i = 2; i <= n; i += 1) {
            erat.putIfAbsent(i, false);
            if (erat.get(i)) continue;
            primes.add(i);
            for (int j = 2 * i; j <= n; j += i) {
                erat.putIfAbsent(j, true);
            }
        }
        return primes;
    }

    private static int search(int n, List<Integer> arr) {
        int len = arr.size();
        int l = 0;
        int r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr.get(mid) == n) return mid;
            else if (arr.get(mid) < n) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    private static int search(int n, int[] arr) {
        int len = arr.length;
        int l = 0;
        int r = len - 1;
        while (r - l >= 0) {
            int mid = (r + l) / 2;
            if (arr[mid] == n) return mid;
            else if (arr[mid] > n) r = mid - 1;
            else l = mid + 1;
        }
        return -1;
    }

    private static List<Integer> multipliers(int a, int k, int n) {
        int n1 = (n > 0 && (n & 1) == 1) ? n - 1 : n;
        List<Integer> phis = new ArrayList<>();
        int i = 0;
        while (true) {
            int tmp = modPow(a, (n - 1) >> i, n);
            phis.add(tmp);
            if (tmp * 2 * i > n1) break;
            i += 1;
        }
        return phis;
    }

    private static int searchOne(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i += 1) {
            if (arr[i] == 1) return i;
        }
        return -1;
    }

    private static int[] insertSort(int[] arrOrig) {
        int[] arr = Arrays.copyOf(arrOrig, arrOrig.length);
        for (int i = 1, len = arr.length; i < len; i += 1) {
            for (int j = i; j > 0 && arr[j - 1] - arr[j] > 0; j -= 1) {
                swap(j, j - 1, arr);
            }
        }
        return arr;
    }

    private static void swap(int i, int j, int[] arr) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
