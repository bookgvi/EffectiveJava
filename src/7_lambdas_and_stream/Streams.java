import jdk.dynalink.Operation;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import static java.util.stream.Collectors.*;

public class Streams {
    public static void main(String[] args) {
        List<String> arrList1 = new ArrayList<>();
        arrList1.add("QQQ");
        arrList1.add("QQQ");
        arrList1.add("QQQ");
        arrList1.add("WWW");
        arrList1.add("ee");
        arrList1.add("R");

//        Map<String, String> map1 = arrList1.stream().collect(toMap(String::toLowerCase, elt -> elt));
        Map<String, Long> map2 = arrList1.stream().collect(groupingBy(String::toLowerCase, TreeMap::new, counting()));
        Map<String, List<String>> map3 = arrList1.stream().collect(groupingBy(String::toLowerCase, toList()));

        Set<Object> set1 = Arrays.asList(null, null, "QQQ").stream().collect(toSet());
        String str = "";
        List<String> strList = new ArrayList<>();
        List<Object> objList = new ArrayList<>();
        Collection<?> collection = null;
        Set<String> setStr = Set.of("1", "2");
        Map<String, ?> hm1 = Map.of("Key", "value");
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(5);
        System.out.printf("List<String> is subType of Lsit<Object> %s \n", str instanceof Object);
        System.out.println("Finish");
    }

}