package Lambda;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class Fabric {
    private static final Map<String, Predicate<Integer>> fabric = Map.of(
            "isPositive", x -> x > 0,
            "isEven", x -> (x & 1) == 0
    );

    private static Optional<Predicate<Integer>> fabricMethod(String op) {
        return Optional.ofNullable(fabric.get(op));
    }

    public static void main(String[] args) {
        List<String> operations = List.of("isPositive", "FU", "isEven");
        int num = 10;
        System.out.println("---------------------------------------------------");
        for (String op : operations) {
            Optional<Predicate<Integer>> operationOp = fabricMethod(op);
            boolean result = operationOp.map(p -> p.test(num)).orElse(false);
            System.out.printf("Operation: \"%s\", for number %d, finished with result result \"%b\";\n", op, num, result);
        }
        System.out.println("---------------------------------------------------");
    }
}
