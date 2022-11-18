package Lambda;

import javax.swing.text.html.Option;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;

public class FunctionalInterfaces {
    private static final Function<String, Map<String, Predicate<Integer>>> fabric = key -> Map.of(
            "isPositive", x -> x > 0,
            "isOdd", x -> (x & 1) == 1
    );

    private static Optional<Predicate<Integer>> getMap(String key) {
        return Optional.ofNullable(fabric.apply(key).get(key));
    }

    private static long binPow(int a, int pow) {
        long res = 1;
        while (pow > 0) {
            if ((pow & 1) == 1) res = res * a;
            a *= a;
            pow >>= 1;
        }
        return res;
    }

    private static long fact(int n) {
        long res = 1;
        for (int i = 1; i <= n; i += 1)
            res *= i;
        return res;
    }

    private static void fi() {
        Predicate<Integer> isPositive1;
        Predicate<Integer> isPositive = x -> x > 0;
        ToLongBiFunction<Integer, Integer> pows = FunctionalInterfaces::binPow;
        Function<Integer, Long> fact = FunctionalInterfaces::fact;

        Optional<Predicate<Integer>> predicateOpt = getMap("isPositive");
        boolean res = predicateOpt.map(integerPredicate -> integerPredicate.test(-11)).orElse(false);
        System.out.println(res);

        System.out.println(isPositive.test(-10));
        System.out.println(pows.applyAsLong(-10, 2));
        System.out.println(fact.apply(6));

    }

    public static void main(String[] args) {
        fi();
    }
}
