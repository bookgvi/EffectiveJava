package RPN.syntactic.visitor;

import RPN.syntactic.Expr;

public interface VoidVisitor<A> {
    void visit(Expr.Binary expr, A args);

    void visit(Expr.Unary expr, A args);

    void visit(Expr.Literal expr, A args);

    void visit(Expr.Grouping expr, A args);
}
