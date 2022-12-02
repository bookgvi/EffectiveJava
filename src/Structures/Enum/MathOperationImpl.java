package Structures.Enum;

enum MathOperationImpl implements IMathOperation, IExtMathOperation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY("*") {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    MULTIPLY_EXT("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }

        public double apply_ext(double x, double y, double z) {
            return x * y * z;
        }
    },
    DIVIDE("/") {
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String op;

    MathOperationImpl(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return op;
    }
}

class App {
    public static void main(String[] args) {
        IMathOperation plus = MathOperationImpl.PLUS;
        IMathOperation minus = MathOperationImpl.MINUS;
        IMathOperation multiply = MathOperationImpl.MULTIPLY;
        IMathOperation divide = MathOperationImpl.DIVIDE;
        IExtMathOperation extMul = MathOperationImpl.MULTIPLY_EXT;
        double x = 21, y = 2, z = 2;
        calcAndDisplay(plus, x, y);
        calcAndDisplay(minus, x, y);
        calcAndDisplay(multiply, x, y);
        calcAndDisplay(divide, x, y);
        calcAndDisplay(extMul, x, y, z);
    }

    private static void calcAndDisplay(IMathOperation op, double x, double y) {
        double res = op.apply(x, y);
        System.out.printf("%.2f %s %.2f = %.2f\n", x, op, y, res);
    }

    private static void calcAndDisplay(IExtMathOperation op, double x, double y, double z) {
        double res = op.apply_ext(x, y, z);
        System.out.printf("%.2f %s %.2f %s %.2f = %.2f\n", x, op, y, op, z, res);
    }
}
