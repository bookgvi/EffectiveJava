package RPN.syntactic;

public abstract class Stmt implements VisitableStmt {

    public abstract <R, A> R accept(GenericVisitor<R, A> visitor, A args);

    public interface GenericVisitor<R, A> {
        R visit(Stmt.Expression stmt, Object... args);
        R visit(Stmt.Print stmt, Object... args);
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
}
