package RPN.syntactic;

import RPN.lexerParser.Scanner;
import RPN.token.Token;
import RPN.token.TokenType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class ReversePolishNotationTest {


    private final Token plus = Token.builder()
            .kind(TokenType.PLUS)
            .lexeme("+")
            .value("+")
            .build();
    private final Token minus = Token.builder()
            .kind(TokenType.MINUS)
            .lexeme("-")
            .value("-")
            .build();
    private final Token multiply = Token.builder()
            .kind(TokenType.STAR)
            .lexeme("*")
            .value("*")
            .build();
    private final Token divide = Token.builder()
            .kind(TokenType.SLASH)
            .lexeme("/")
            .value("/")
            .build();

    @Test
    public void test1() {
        Expr expr = new Expr.Binary(
                new Expr.Unary(minus,
                        new Expr.Grouping(
                                new Expr.Binary(
                                        new Expr.Literal(4),
                                        divide,
                                        new Expr.Grouping(
                                                new Expr.Binary(
                                                        new Expr.Literal(5),
                                                        plus,
                                                        new Expr.Literal(6)
                                                )
                                        )
                                )
                        )
                ),
                multiply,
                new Expr.Grouping(
                        new Expr.Binary(
                                new Expr.Literal(7),
                                minus,
                                new Expr.Literal(8)
                        )
                )
        );

        String ans = "4 5 6 + group / group - 7 8 - group *";
        String res = new ReversePolishNotation().process(expr);
        System.out.println(res);
        System.out.println();
        Assertions.assertEquals(ans, res);
    }

    @Test
    public void test2() {
        Expr expr = new Expr.Binary(
                new Expr.Grouping(new Expr.Binary(new Expr.Literal(1), plus, new Expr.Literal(2))),
                multiply,
                new Expr.Grouping(new Expr.Binary(new Expr.Literal(4), minus, new Expr.Literal(3)))
        );

        String ans = "1 2 + group 4 3 - group *";
        String res = new ReversePolishNotation().process(expr);
        System.out.println(res);
        System.out.println();
        Assertions.assertEquals(ans, res);
    }

    @Test
    public void test_parse1() {
        String template = "(1 + 2) * (4 - 3)";
//        String template = "-(4 / (5 + 6)) * (7 - 8)";
        Collection<Token> tokens = new Scanner(template).proceed();
        for (Token token : tokens) {
            System.out.println(token);
        }
        System.out.println("FIN\n");
    }
}