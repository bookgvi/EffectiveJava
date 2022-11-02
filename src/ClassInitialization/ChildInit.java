package ClassInitialization;

public class ChildInit extends Init implements IInit {
    static String chVal = "default";

    static {
        System.out.println("Static block init int ChildInit.class");
        Init.val = chVal;
    }

    {
        System.out.println("NON Static block init int ChildInit.class");
    }

    ChildInit(String val) {
        Init.val = val;
    }
}
