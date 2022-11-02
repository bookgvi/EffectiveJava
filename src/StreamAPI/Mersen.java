package StreamAPI;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Mersen {

    public static void main(String[] args) {
        Test tst = new Test() {
            @Override
            void displayAppHeader() {
                System.out.println("Mersen counts");
            }
        };
        tst.setBye("BB");
        tst.displayAppHeader();
        tst.hello();
        tst.display20Mersens();
        System.out.println(tst.getBye());
    }
}

abstract class Test implements IGreeting{
    private String bye = "Bye";

    String getBye() {
        return bye;
    }

    void setBye(String bye) {
        this.bye = bye;
    }

    private Stream<BigInteger> primes() {
        return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
    }

    void display20Mersens() {
        primes().map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
                .filter(mersen -> mersen.isProbablePrime(50))
                .limit(20)
                .forEach(System.out::println);
    }

    void hello() {
        System.out.println(f);
    }

    abstract void displayAppHeader();
}

interface IGreeting {
    String f = "Hello";
}