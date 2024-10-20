package RPN.syntactic;

import RPN.syntactic.visitor.GenericVisitor;

import java.util.Optional;

public class ReversePolishNotation implements GenericVisitor<String, String> {

    public String process(Expr expr) {
        return expr.accept(this, null);
    }

    @Override
    public String visit(Expr.Binary expr, String args) {
        return result(expr.getOperation().getLexeme(), expr.getLeft(), expr.getRight());
    }

    @Override
    public String visit(Expr.Unary expr, String args) {
        return result(expr.getOperation().getLexeme(), expr.getRight());
    }

    @Override
    public String visit(Expr.Literal expr, String args) {
        return Optional.ofNullable(expr).map(ex -> ex.getValue().toString()).orElse("");
    }

    @Override
    public String visit(Expr.Grouping expr, String args) {
        return result("group", expr.getExpr());
    }

    public String result(String op, Expr ...exprs) {
        StringBuilder sb = new StringBuilder();
        for (Expr expr : exprs) {
            if (expr != null) {
                sb.append(expr.accept(this, null)).append(" ");
            }
        }
        sb.append(op);
        return sb.toString();
    }
}
