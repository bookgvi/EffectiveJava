package Algos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Primes {

    public static void main(String[] args) {
        int x = 150;
        System.out.printf("Primes for %d: %s\n", x, getPrimes(x));
        Map<Integer, Boolean> erath = erathosfen(x);
        System.out.println(erath.size() > 1000 ? erath.size() : erath);
    }

    /*
     * Метод заполнения массива простыми множителями для числа на входе
     * */
    public static List<Integer> getPrimes(int x) {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i * i <= x; i++) {
            while (x % i == 0) {
                primes.add(i);
                x /= i;
            }
        }
        if (x > 1) primes.add(x);
        return primes;
    }

    /*
     * Алгоритм "Решето Эратосфена" - заполнение массива c "пометкой" простое число (true) или нет (false)
     * */
    public static Map<Integer, Boolean> erathosfen(int n) {
        Map<Integer, Boolean> erathosfen = new HashMap<>();
        for (int i = 2; i <= n; i++) {
            erathosfen.putIfAbsent(i, true);
            if (!erathosfen.get(i) || (i != 2 && i % 2 == 0)) continue;
            for (int j = 2 * i; j <= n; j += i) {
                erathosfen.putIfAbsent(j, false);
            }
        }
        return erathosfen;
    }
}
