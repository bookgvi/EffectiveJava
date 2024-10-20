package RPN.lexerParser;

import RPN.token.Token;
import RPN.token.TokenType;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    void proceed() {
        String str = "1 + 1.22 * 3 / 5";
        Collection<Token> tokens = new Scanner(str).proceed();
        assertFalse(tokens.isEmpty());
    }

    // Successfully tokenize a simple arithmetic expression with operators and numbers
    @Test
    public void test_tokenize_arithmetic_expression() {
        Scanner scanner = new Scanner("3 + 4 * 2");
        Collection<Token> tokens = scanner.proceed();
        assertEquals(6, tokens.size());
        assertEquals(TokenType.NUMBER, tokens.iterator().next().getKind());
    }

    // Handle an empty input string and return only an EOF token
    @Test
    public void test_empty_input_returns_eof() {
        Scanner scanner = new Scanner("");
        Collection<Token> tokens = scanner.proceed();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.iterator().next().getKind());
    }

    // Correctly add an EOF token at the end of processing
    @Test
    public void test_correctly_add_eof_token() {
        // Arrange
        Scanner scanner = new Scanner("3 + 5");

        // Act
        Collection<Token> result = scanner.proceed();

        // Assert
        assertEquals(4, result.size());
        Token lastToken = result.stream().reduce((first, second) -> second).orElse(null);
        assertNotNull(lastToken);
        assertEquals(TokenType.EOF, lastToken.getKind());
        assertNull(lastToken.getLexeme());
        assertNull(lastToken.getValue());
    }

    @Test
    public void test_correctly_add_string_token() {
        // Arrange
        Scanner scanner = new Scanner("3 + 5 + \"QQQ\"");

        // Act
        Collection<Token> result = scanner.proceed();

        // Assert
        assertEquals(6, result.size());
        AtomicReference<Token> tokenAtom = new AtomicReference<>();
        Iterator<Token> iterator = result.iterator();
        IntStream.rangeClosed(0, 4).forEach(i -> tokenAtom.set(iterator.next()));
        assertEquals(TokenType.STRING, tokenAtom.get().getKind());
    }

    @Test
    public void test_correctly_add_string_token2() {
        // Arrange
        Scanner scanner = new Scanner("\"QQQ\"");

        // Act
        Collection<Token> result = scanner.proceed();

        // Assert
        assertEquals(2, result.size());
        AtomicReference<Token> tokenAtom = new AtomicReference<>();
        Iterator<Token> iterator = result.iterator();
        IntStream.rangeClosed(0, 0).forEach(i -> tokenAtom.set(iterator.next()));
        assertEquals(TokenType.STRING, tokenAtom.get().getKind());
    }
}