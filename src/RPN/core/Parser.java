package RPN.core;

import RPN.syntactic.Expr;
import RPN.syntactic.Stmt;
import RPN.token.Token;
import RPN.token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static RPN.token.TokenType.BANG;
import static RPN.token.TokenType.BANG_EQUAL;
import static RPN.token.TokenType.EQUAL;
import static RPN.token.TokenType.EQUAL_EQUAL;
import static RPN.token.TokenType.FALSE;
import static RPN.token.TokenType.GREATER;
import static RPN.token.TokenType.GREATER_EQUAL;
import static RPN.token.TokenType.IDENTIFIER;
import static RPN.token.TokenType.LEFT_PAREN;
import static RPN.token.TokenType.LESS;
import static RPN.token.TokenType.LESS_EQUAL;
import static RPN.token.TokenType.MINUS;
import static RPN.token.TokenType.NIL;
import static RPN.token.TokenType.NUMBER;
import static RPN.token.TokenType.PLUS;
import static RPN.token.TokenType.PRINT;
import static RPN.token.TokenType.RIGHT_PAREN;
import static RPN.token.TokenType.SEMICOLON;
import static RPN.token.TokenType.SLASH;
import static RPN.token.TokenType.STAR;
import static RPN.token.TokenType.STRING;
import static RPN.token.TokenType.TRUE;
import static RPN.token.TokenType.VAR;

/**
 * program -> declaration* EOF" ;
 * declaration -> varDeclaration | statement ;
 * <p>
 * varDeclaration -> "var" IDENTIFIER ("=" expression)? ";" ;
 * <p>
 * statement -> exprStmt | printStmt ;
 * exprStmt -> expression ";" ;
 * printStmt -> "print" expression ";" ;
 * <p>
 * expression → equality ;
 * equality → comparison ( ( "!=" | "==" ) comparison )* ;
 * comparison → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
 * term → factor ( ( "-" | "+" ) factor )* ;
 * factor → unary ( ( "/" | "*" ) unary )* ;
 * unary → ( "!" | "-" | "+" ) unary | primary ;
 * primary → NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")" | IDENTIFIER;
 *
 */
public class Parser {
    private final List<Token> tokens;
    private int current;

    public Parser(List<Token> tokens) {
        this.tokens = Optional.ofNullable(tokens).orElse(new ArrayList<>());
    }

    public List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (isNotEnd()) {
            statements.add(declaration());
        }
        return statements;
    }

    /**
     * declaration -> varDeclaration | statement ;
     * @return Statement
     */
    public Stmt declaration() {
        if (match(VAR)) {
            return varDeclaration();
        }
        return statement();
    }

    /**
     * varDeclaration -> "var" IDENTIFIER ("=" expression)? ";" ;
     * @return Statement
     */
    private Stmt varDeclaration() {
        Token name = consume(IDENTIFIER, "Expect variable name.");
        Expr initializer = null;
        if (match(EQUAL)) {
            initializer = expression();
        }
        consume(SEMICOLON, "Expect ';' after value.");
        return new Stmt.Var(name, initializer);
    }

    /**
     * statement -> exprStmt | printStmt ;
     * @return Statement
     */
    public Stmt statement() {
        if (match(PRINT)) {
            return printStmt();
        }
        return exprStmt();
    }

    /**
     * exprStmt -> expression ";" ;
     * @return Statement
     */
    private Stmt exprStmt() {
        Expr value = expression();
        consume(SEMICOLON,  "Expect ';' after value.");
        return new Stmt.Expression(value);
    }

    /**
     * printStmt -> "print" expression ";" ;
     * @return Statement
     */
    private Stmt printStmt() {
        Expr value = expression();
        consume(SEMICOLON,  "Expect ';' after value.");
        return new Stmt.Print(value);
    }

    /**
     * expression -> equality ;
     * @return Expression
     */
    public Expr expression() {
        return equality();
    }

    /**
     * equality → comparison ( ( "!=" | "==" ) comparison )* ;
     * @return expression
     */
    public Expr equality() {
        Expr expr = comparison();

        while (match(BANG_EQUAL, EQUAL_EQUAL)) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr, operator, right);
        }

        return expr;
    }

    /**
     * comparison -> term (("<" | ">" | ">=" | "<=") term)*
     * @return term
     */
    public Expr comparison() {
        Expr expr = term();
        while (match(GREATER, GREATER_EQUAL, LESS, LESS_EQUAL)) {
            Token operator = previous();
            Expr right = term();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    /**
     * term -> factor (("+" | "-") factor)*
     * @return factor
     */
    public Expr term() {
        Expr expr = factor();
        while (match(MINUS, PLUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    /**
     * factor -> unary (("*" | "/") unary)*
     * @return unary
     */
    public Expr factor() {
        Expr expr = unary();
        while (match(SLASH, STAR)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator, right);
        }
        return expr;
    }

    /**
     * unary -> ("!" | "-" | "+") unary | primary
     * @return primary
     */
    public Expr unary() {
        while (match(BANG, MINUS, PLUS)) {
            Token operator = previous();
            Expr right = primary();
            new Expr.Unary(operator, right);
        }
        return primary();
    }

    /**
     * primary -> (NUMBER | STRING | "true" | "false" | "null" | "(" expression ")"
     * @return expression | null
     */
    public Expr primary() {
        if (match(TRUE)) return new Expr.Literal(true);
        if (match(FALSE)) return new Expr.Literal(false);
        if (match(NIL)) return new Expr.Literal(null);
        if (match(NUMBER, STRING)) return new Expr.Literal(previous().getValue());
        if (match(LEFT_PAREN)) {
            Expr expr = expression();
            consume(RIGHT_PAREN, "Expect ')'");
            return new Expr.Grouping(expr);
        }
        if (match(IDENTIFIER)) return new Expr.Variable(previous());
        return null;
    }

    private Token consume(TokenType type, String msg) {
        if (check(type)) return advance();
        throw new RuntimeException(msg);
    }

    private boolean match(TokenType... types) {
        for (TokenType kind : types) {
            if (check(kind)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isNotEnd()) {
            return peek().getKind() == type;
        }
        return false;
    }

    private Token advance() {
        if (isNotEnd()) {
            ++current;
        }
        return previous();
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return  tokens.get(current - 1);
    }

    private boolean isNotEnd() {
        return peek().getKind() != TokenType.EOF;
    }
}
