package RPN.lexer;

import RPN.token.Token;
import RPN.token.TokenType;

import java.util.ArrayList;
import java.util.Collection;

public class Scanner1 {

    private final char[] buffer;
    private final Collection<Token> tokens;
    private int current;
    private int start;
    private int line;

    public Scanner1(String source) {
        this.buffer = source.toCharArray();
        this.tokens = new ArrayList<>();
        this.line = 1;
    }

    public Collection<Token> proceed() {
        while (!isEnd()) {
            start = current;
            scan();
        }
        addToken(TokenType.EOF, null, null);
        return tokens;
    }

    private void scan() {
        char ch = advance();
        switch (ch) {
            case '+': addToken(TokenType.PLUS); break;
            case '-': addToken(TokenType.MINUS); break;
            case '*': addToken(TokenType.MULTIPLY); break;
            case '/': addToken(TokenType.DIVIDE); break;
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n': line++; break;
            default:
                if (isDigit(ch)) {
                    number();
                } else {
                    System.out.println("Unknown symbol");
                }
                break;
        }
    }

    private void number() {
        while (!isEnd() && isDigit(peek())) {
            advance();
        }
        if (peek() == '.' && isDigit(peekNext())) {
            do {
                advance();
            } while (!isEnd() && isDigit(peek()));
        }
        String value = new String(buffer, start, current - start);
        addToken(TokenType.NUMBER, Double.parseDouble(value));
    }

    private char peekNext() {
        if (current + 1 >= buffer.length) {
            return '\u001a';
        }
        return buffer[current + 1];
    }

    private char peek() {
        return isEnd() ? '\u001a' : buffer[current];
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private char advance() {
        if (!isEnd()) {
            return buffer[current++];
        }
        return '\u001a';
    }
    private void addToken(TokenType kind) {
        addToken(kind, null);
    }

    private void addToken(TokenType kind, Object literal) {
        String lexeme = new String(buffer, start, current - start);
        addToken(kind, lexeme, literal);
    }

    private void addToken(TokenType kind, String lexeme, Object literal) {
        Token token = Token.builder()
                .kind(kind)
                .lexeme(lexeme)
                .value(literal)
                .start(start)
                .end(current)
                .line(line)
                .build();
        tokens.add(token);
    }

    private boolean isEnd() {
        return current >= buffer.length;
    }

}
