package Functions;

import java.util.Objects;

public class Dummy {
    /**
     * @param val - positive integer
     * @return power of two for inpu value
     * @throws NullPointerException if val == null
     */
    static Integer powOfTwo(Integer val) {
        assert val != null;
//        Objects.requireNonNull(val, "NPE");
        return val << 1;
    }

    public static void main(String[] args) {
        Integer res = powOfTwo(null);
    }
}
