package RPN.core.stmt;

import RPN.core.Interpreter;
import RPN.core.Parser;
import RPN.core.Scanner;
import RPN.syntactic.Expr;
import RPN.syntactic.Stmt;
import RPN.token.Token;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StmtInterpreterTest {

    @Test
    public void test1() throws Exception {
        String fileName = "/Users/bookgvi/IdeaProjects/EffectiveJava/test/RPN/core/stmt/for_test/";
        try (Stream<Path> pathStream = Files.walk(Paths.get(fileName))) {
            pathStream
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            BufferedReader buffer = new BufferedReader(new FileReader(path.toFile()));
                            String text = buffer.lines().collect(Collectors.joining("\n"));
                            List<Token> tokens = (List<Token>) new Scanner(text).proceed();
                            Parser parser = new Parser(tokens);
                            List<Stmt> statements = parser.parse();

                            Interpreter interpreter = new Interpreter();
                            interpreter.interpret(statements, null);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
