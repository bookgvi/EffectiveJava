package RPN.token;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {
    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,
    AMPERSAND,

    // One or two character tokens.
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,
    // Literals.
    IDENTIFIER, STRING, NUMBER,

    // Comments
    END_OF_LINE_COMMENT, TRADITIONAL_COMMENT,

    // Keywords.
    AND, FALSE, FUN, NIL, OR,
    PRINT, TRUE,
    EOF,

    // JLS 21
    ABSTRACT, CONTINUE, FOR, NEW, SWITCH,
    ASSERT, DEFAULT, IF, PACKAGE, SYNCHRONIZED,
    BOOLEAN, DO, GOTO, PRIVATE, THIS,
    BREAK, DOUBLE, IMPLEMENTS, PROTECTED, THROW,
    BYTE, ELSE, IMPORT, PUBLIC, THROWS,
    CASE, ENUM, INSTANCEOF, RETURN, TRANSIENT,
    CATCH, EXTENDS, INT, SHORT, TRY,
    CHAR, FINAL, INTERFACE, STATIC, VOID,
    CLASS, FINALLY, LONG, STRICTFP, VOLATILE,
    CONST, FLOAT, NATIVE, SUPER, WHILE,

    EXPORTS, OPENS, REQUIRES, USES, YIELD,
    MODULE, PERMITS, SEALED, VAR,
    NON_SEALED, PROVIDES, TO, WHEN,
    OPEN, RECORD, TRANSITIVE, WITH,

    ;

    private static final Map<String, TokenType> typesMap = Stream.of(values())
            .collect(Collectors.toMap(v -> v.name().toLowerCase(), Function.identity()));

    public static Map<String, TokenType> getTypesMap() {
        return Collections.unmodifiableMap(typesMap);
    }
}
