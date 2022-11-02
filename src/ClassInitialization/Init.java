package ClassInitialization;

public class Init implements IInit {
    static {
        System.out.println("Static block init int Init.class");
    }
    {
        System.out.println("NON Static block init int Init.class");
    }
    static String val;
    Init() {
        displayFieldVal();
    }

    void displayFieldVal() {
        System.out.println(val);
    }
}
