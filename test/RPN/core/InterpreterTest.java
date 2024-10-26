package RPN.core;

import RPN.syntactic.Expr;
import RPN.token.Token;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterpreterTest {

    @Test
    void interpret_this_class() {
        String fileNAme = "/Users/bookgvi/IdeaProjects/EffectiveJava/test/RPN/core/InterpreterTest.java";
        try (Stream<Path> pathStream = Files.walk(Path.of(fileNAme))) {
            pathStream.forEach(path -> {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()));
                    String fileAsString = bufferedReader.lines().collect(Collectors.joining("\n"));
                    List<Token> tokens = (List<Token>) new Scanner(fileAsString).proceed();
                    Expr expr = new Parser(tokens).expression();
                    new Interpreter().interpret(expr, null);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

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