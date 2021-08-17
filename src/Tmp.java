public class Tmp {
    private static String category = "electronic trading system";
    public static void main1(String[] args) {
        Tmp system = null;
        System.out.println(system.category);
    }

    public static void main1(String[] args, int index) {
        Tmp system = null;
        System.out.printf("%s, \n %d", system.category, index);
    }

    public static void main(String[] args) {
        main1(args);
    }
}
