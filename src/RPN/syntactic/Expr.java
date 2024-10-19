package RPN.syntactic;

import RPN.syntactic.visitor.GenericVisitor;
import RPN.syntactic.visitor.Visitable;
import RPN.syntactic.visitor.VoidVisitor;
import RPN.token.Token;

/**
 * expression → literal | unary | binary | grouping ;
 * literal grouping unary binary operator → NUMBER | STRING | "true" | "false" | "nil" ;
 * → "(" expression ")" ;
 * → ( "-" | "!" ) expression ;
 * → expression operator expression ;
 * → "==" | "!=" | "<" | "<=" | ">" | ">=" | "+" | "-" | "*" | "/" ;
 */
public abstract class Expr implements Visitable {

    public abstract <A> void accept(VoidVisitor<A> visitor, A args);
    public abstract <R, A> R accept(GenericVisitor<R, A> visitor, A args);

    public static class Binary extends Expr {
        private final Expr left;
        private final Expr right;
        private final Token operation;

        public Binary(Expr left, Token operation, Expr right) {
            this.left = left;
            this.operation = operation;
            this.right = right;
        }

        public Expr getLeft() {
            return left;
        }

        public Expr getRight() {
            return right;
        }

        public Token getOperation() {
            return operation;
        }

        @Override
        public <A> void accept(VoidVisitor<A> visitor, A args) {
            visitor.visit(this, args);
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }
    }

    public static class Unary extends Expr {
        private final Token operation;
        private final Expr right;

        public Unary(Token operation, Expr right) {
            this.operation = operation;
            this.right = right;
        }

        public Token getOperation() {
            return operation;
        }

        public Expr getRight() {
            return right;
        }

        @Override
        public <A> void accept(VoidVisitor<A> visitor, A args) {
            visitor.visit(this, args);
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }
    }

    public static class Literal extends Expr {
        private final Object value;

        public Literal(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        @Override
        public <A> void accept(VoidVisitor<A> visitor, A args) {
            visitor.visit(this, args);
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }
    }

    public static class Grouping extends Expr {
        private final Expr expr;

        public Grouping(Expr expr) {
            this.expr = expr;
        }

        public Expr getExpr() {
            return expr;
        }

        @Override
        public <A> void accept(VoidVisitor<A> visitor, A args) {
            visitor.visit(this, args);
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }
    }
}
