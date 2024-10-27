package RPN.core;

import RPN.syntactic.Expr;
import RPN.syntactic.Stmt;
import RPN.token.TokenType;

import java.util.List;
import java.util.Objects;

public class Interpreter implements Expr.GenericVisitor<Object, Object>, Stmt.GenericVisitor<Object, Object> {

    public Object interpret(Expr expression, Object args) {
        Object value = null;
        try {
            value = evaluate(expression, args);
            System.out.println(stringify(value));
        } catch (Exception error) {
            System.out.println("ERROR");
        }
        return value;
    }

    public void interpret(List<Stmt> statements, Object... args) {
        for (Stmt stmt : statements) {
            try {
                Object res = execute(stmt, args);
                if (res != null) {
                    System.out.println(res);
                }
            } catch (RuntimeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private Object execute(Stmt stmt, Object... args) {
        return stmt.accept(this, args);
    }

    public String stringify(Object object) {
        if (object == null) return "nil";
        if (object instanceof Double) {
            String text = object.toString();
            if (text.endsWith(".0")) {
                text = text.substring(0, text.length() - 2);
            }
            return text;
        }
        return object.toString();
    }

    @Override
    public Object visit(Expr.Binary expr, Object args) {
        Object leftValue = evaluate(expr.getLeft(), args);
        Object rightValue = evaluate(expr.getRight(), args);
        TokenType operation = expr.getOperation().getKind();
        switch (operation) {
            case STAR:
                return (double) leftValue * (double) rightValue;
            case SLASH:
                return (double) leftValue / (double) rightValue;
            case MINUS:
                return (double) leftValue - (double) rightValue;
            case PLUS:
                if ((leftValue instanceof Double) && (rightValue instanceof Double)) {
                    return (double) leftValue + (double) rightValue;
                } else if ((leftValue instanceof String) && (rightValue instanceof String)) {
                    return leftValue + (String) rightValue;
                }
            case GREATER:
                return (double) leftValue > (double) rightValue;
            case GREATER_EQUAL:
                return (double) leftValue >= (double) rightValue;
            case LESS:
                return (double) leftValue < (double) rightValue;
            case LESS_EQUAL:
                return (double) leftValue <= (double) rightValue;
            case BANG_EQUAL:
                return !isEqual(leftValue, rightValue);
            case EQUAL_EQUAL:
                return isEqual(leftValue, rightValue);
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(Expr.Unary expr, Object args) {
        if (expr != null) {
            TokenType operation = expr.getOperation().getKind();
            Object rightValue = evaluate(expr.getRight(), args);
            switch (operation) {
                case PLUS:
                    return rightValue;
                case MINUS:
                    return -(double) rightValue;
                case BANG:
                    return !isTruthy(rightValue);
                default:
                    break;
            }
        }
        return null;
    }

    @Override
    public Object visit(Expr.Literal expr, Object args) {
        return expr.getValue();
    }

    @Override
    public Object visit(Expr.Grouping expr, Object args) {
        return evaluate(expr.getExpr(), args);
    }

    @Override
    public Object visit(Stmt.Expression stmt, Object... args) {
        return evaluate(stmt.getExpr(), args);
    }

    @Override
    public Object visit(Stmt.Print stmt, Object... args) {
        Object value = evaluate(stmt.getExpr(), args);
        System.out.println("print " + stringify(value));
        return null;
    }

    private boolean isTruthy(Object value) {
        if (value == null) {
            return false;
        }
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        return false;
    }

    private Object evaluate(Expr expr, Object args) {
        return expr.accept(this, args);
    }

    private boolean isEqual(Object a, Object b) {
        return Objects.equals(a, b);
    }

}
