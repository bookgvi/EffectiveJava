package RPN.lexerParser;

import RPN.syntactic.Expr;
import RPN.token.Token;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    @Test
    void interpret1() {
        String template = "(4 / (5 + 5)) * (7 - 8)";
        List<Token> tokens = (List<Token>) new Scanner(template).proceed();
        Expr expression = new Parser(tokens).expression();
        double res = (double) new Interpreter().interpret(expression, null);
        double ans = -0.4;
        assertEquals(ans, res);
    }

    @Test
    void interpret2() {
        String template = "(2 + 2)";
        List<Token> tokens = (List<Token>) new Scanner(template).proceed();
        Expr expression = new Parser(tokens).expression();
        double res = (double) new Interpreter().interpret(expression, null);
        double ans = 4;
        assertEquals(ans, res);
    }
}