package RPN.lexerParser;

import RPN.syntactic.Expr;
import RPN.syntactic.PrettyPrint;
import RPN.syntactic.ReversePolishNotation;
import RPN.token.Token;
import org.junit.jupiter.api.Test;

import java.util.List;

class ParserTest {

    @Test
    public void test1() {
        String template = "(1 + 2) * (4 - 3)";
        List<Token> tokens = (List<Token>) new Scanner(template).proceed();
        Parser parser = new Parser(tokens);
        Expr expr = parser.expression();
        PrettyPrint prettyPrint = new PrettyPrint();
        String res = prettyPrint.process(expr, null);

        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        String rpn = reversePolishNotation.process(expr);
        System.out.println(res);
        System.out.println(rpn);
    }

    @Test
    public void test2() {
        String template = "(4 / (5 + 6)) * (7 - 8)";
        List<Token> tokens = (List<Token>) new Scanner(template).proceed();
        Parser parser = new Parser(tokens);
        Expr expr = parser.expression();
        PrettyPrint prettyPrint = new PrettyPrint();
        String res = prettyPrint.process(expr, null);

        ReversePolishNotation reversePolishNotation = new ReversePolishNotation();
        String rpn = reversePolishNotation.process(expr);
        System.out.println(res);
        System.out.println(rpn);
    }
}