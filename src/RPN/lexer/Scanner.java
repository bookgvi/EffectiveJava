package RPN.lexer;

import RPN.token.Token;
import RPN.token.TokenType;

import java.util.ArrayList;
import java.util.Collection;

public class Scanner {
    private final char CTRL_Z = '\u001a';

    private final Collection<Token> tokens = new ArrayList<>();
    private final char[] buffer;
    private int start;
    private int current;
    private int line;

    public Scanner(String source) {
        this.buffer = source.toCharArray();
        this.line = 1;
    }

    public Collection<Token> proceed() {
        while (isNotEnd()) {
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
            case '(': addToken(TokenType.LEFT_PAREN); break;
            case ')': addToken(TokenType.RIGHT_PAREN); break;
            case '{': addToken(TokenType.LEFT_BRACKET); break;
            case '}': addToken(TokenType.RIGHT_BRACKET); break;
            case '/': addToken(TokenType.DIVIDE); break;
            case '"': string(); break;
            case '\n': line += 1; break;
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                if (isDigit(ch)) {
                    number();
                } else if (isAlpha(ch)) {
                    identifier();
                } else {
                    System.out.println("Incorrect symbol!!!");
                }
                break;
        }
    }

    private void string() {
        while (isNotEnd() && peek() != '"') {
            if (peek() == '\n') {
                line += 1;
            }
            advance();
        }
        if (!isNotEnd()) {
            System.out.println("Unterminated string");
            return;
        }
        advance();
        String lexeme = new String(buffer, start, current - start);
        String literal = new String(buffer, start + 1, current - 1 - start - 1);
        addToken(TokenType.STRING, lexeme, literal);
    }

    private void identifier() {
        while (isNotEnd() && isIdentifier(peek())) {
            advance();
        }
        String lexeme = new String(buffer, start, current - start);
        TokenType kind = TokenType.getTypesMap().getOrDefault(lexeme, TokenType.IDENTIFIER);
        addToken(kind, lexeme);
    }

    private boolean isIdentifier(char ch) {
        return Character.isJavaIdentifierPart(ch);
    }

    private boolean isAlpha(char ch) {
        return Character.isJavaIdentifierStart(ch);
    }

    private void number() {
        while (isNotEnd() && isDigit(peek())) {
            advance();
        }
        if (isNotEnd() && peek() == '.' && isDigit(peekNext())) {
            do {
                advance();
            } while (isNotEnd() && isDigit(peek()));
        }
        String lexeme = new String(buffer, start, current - start);
        addToken(TokenType.NUMBER, lexeme, Double.parseDouble(lexeme));
    }

    private char peekNext() {
        return current + 1 < buffer.length ? buffer[current + 1] : CTRL_Z;
    }

    private char peek() {
        return isNotEnd() ? buffer[current] : CTRL_Z;
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private char advance() {
        return isNotEnd() ? buffer[current++] : CTRL_Z;
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

    private boolean isNotEnd() {
        return current < buffer.length;
    }
}
