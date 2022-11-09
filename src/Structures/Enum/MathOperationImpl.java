package Structures.Enum;

enum MathOperationImpl implements IMathOperation {
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
        double x = 21, y = 2;
        calcAndDisplay(plus, x, y);
        calcAndDisplay(minus, x, y);
        calcAndDisplay(multiply, x, y);
        calcAndDisplay(divide, x, y);
    }

    private static void calcAndDisplay(IMathOperation op, double x, double y) {
        double res = op.apply(x, y);
        System.out.printf("%.2f %s %.2f = %.2f\n", x, op, y, res);
    }
}
