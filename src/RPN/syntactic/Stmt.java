package RPN.syntactic;

import RPN.token.Token;

public abstract class Stmt implements VisitableStmt {

    public abstract <R, A> R accept(GenericVisitor<R, A> visitor, A args);

    public interface GenericVisitor<R, A> {
        R visit(Stmt.Expression stmt, Object... args);
        R visit(Stmt.Print stmt, Object... args);
        R visit(Stmt.Var stmt, Object... args);
    }

    public static class Expression extends Stmt {
        private final Expr expr;

        public Expression(Expr expr) {
            this.expr = expr;
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }

        public Expr getExpr() {
            return expr;
        }
    }

    public static class Print extends Stmt {
        private final Expr expr;

        public Print(Expr expr) {
            this.expr = expr;
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }

        public Expr getExpr() {
            return expr;
        }
    }

    public static class Var extends Stmt {
        private final Token name;
        private final Expr initializer;

        public Var(Token name, Expr initializer) {
            this.name = name;
            this.initializer = initializer;
        }

        @Override
        public <R, A> R accept(GenericVisitor<R, A> visitor, A args) {
            return visitor.visit(this, args);
        }

        public Token getName() {
            return name;
        }

        public Expr getInitializer() {
            return initializer;
        }
    }
}
