package Optional;

import java.util.HashMap;
import java.util.Optional;

class AppParent {
    final void parentMethod(String str) {
        System.out.println(str);
    }
    void parentMethod(int str) {
        System.out.println(str);
    }
}

public class App extends AppParent {
    public static void main(String[] args) {
        OptionalTest optionalTest = new OptionalTest(null);
        Optional<String> opStr = Optional.ofNullable(
                Optional.ofNullable(optionalTest.getDummy())
                        .orElseGet(() -> new Dummy())
                        .getDummyField()
        );
        System.out.println(opStr.orElse("empty..."));


        String str = null;
        opStr = Optional.ofNullable(str);
        System.out.println(opStr.orElse("empty..."));

        HashMap<String, String> tmpMap = new HashMap<>();
        tmpMap.put("1", "1");
        tmpMap.put("2", "1");
        tmpMap.put("3", "1");
        tmpMap.put("4", "1");
    }

    @Override
    void parentMethod(int str) {

    }
}
