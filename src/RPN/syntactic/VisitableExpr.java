package RPN.syntactic;

public interface VisitableExpr {
    <R, A> R accept(Expr.GenericVisitor<R, A> visitor, A args);
    <A> void accept(Expr.VoidVisitor<A> visitor, A args);
}
