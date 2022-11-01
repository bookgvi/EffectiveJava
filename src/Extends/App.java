package Extends;

import java.time.Instant;

class Parent {
    Parent() {
        printTime();
    }

    void printTime() {
    }
}

class Child extends Parent {
    static Instant now;
    static {
        now = Instant.now();
    }
    Child() {
    }

    @Override
    public void printTime() {
        System.out.println(now);
    }
}

public class App {
    public static void main(String[] args) {
        Child ch = new Child();
        ch.printTime();
    }
}
