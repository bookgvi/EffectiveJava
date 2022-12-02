package Concurrency;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreading {
}


//public class ThisClass {

//    Thread myThread;

//    public ThisClass(Source source) {
//        source.registerListener(new EventListener() {
//            public void onEvent(Event e) {
//                ThisClass.this.dd
//            }
//        });
//
//        myThread = new Thread(); // в изначальном вопросе небыло
//
//        ....
//
//        myThread.start();
//    }

//    void myFunc() {
//        myThread.start();
//    }
//}

class MakeitSafe2 {
    private static final int THRESHOLD = 60;
    private AtomicLong count = new AtomicLong(0);
    private AtomicInteger value = new AtomicInteger(54);

    public long getCount() {
        return count.get();
    }

    public void add() {
        // bla bla
        if (value.get() > THRESHOLD) {
            count.set(0);
        } else {
            value.addAndGet(5);
            count.incrementAndGet();
        }

    }
}


class MyClass {
    private static void myFunc() {
        System.out.println("Функция myFunc");
    }

    public static void main(String[] args) {
        Set<Integer> tmpSet = Set.of(1,2,3,4);
        System.out.println("tmpSet = " + tmpSet);
        MyClass myObj = null;

        myObj.myFunc();
    }
}

