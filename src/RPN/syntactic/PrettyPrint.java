package RPN.syntactic;

import java.util.Optional;

public class PrettyPrint implements Expr.GenericVisitor<String, String> {

    public String process(Expr expr, String arg) {
        return expr.accept(this, arg);
    }
    @Override
    public String visit(Expr.Binary expr, String args) {
        return print(expr.getOperation().getLexeme(), expr.getLeft(), expr.getRight());
    }

    @Override
    public String visit(Expr.Unary expr, String args) {
        return print(expr.getOperation().getLexeme(), expr.getRight());
    }

    @Override
    public String visit(Expr.Literal expr, String args) {
        return print(Optional.ofNullable(expr).map(e -> e.getValue().toString()).orElse("nil"));
    }

    @Override
    public String visit(Expr.Grouping expr, String args) {
        return print("group", expr.getExpr());
    }

    public String print(String operation, Expr ...exprs) {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(operation);
        for (Expr expr : exprs) {
            if (expr != null) {
                sb.append(" ").append(expr.accept(this, null));
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
