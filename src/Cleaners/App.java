package Cleaners;

public class App {
    public static void main(String[] args) throws Exception {
        int MAX_LEAFS = (int) 1e9 + 9;

//        try(AutumnStreet autumnStreet = new AutumnStreet(MAX_LEAFS)) {
//            System.out.println("Leaf fall...");
//        }
        AutumnStreet autumnStreet = new AutumnStreet(MAX_LEAFS);
        System.out.println("Pease out...");
        System.gc();
    }
}
