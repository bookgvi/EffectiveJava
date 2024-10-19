package RPN.token;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TokenType {
    PLUS, MINUS, MULTIPLY, DIVIDE,

    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACKET, RIGHT_BRACKET, SEMICOLON,

    AND, OR, XOR, NOT,

    EOL, EOF,

    IDENTIFIER, NUMBER, STRING, NIL
    ;

    private static final Map<String, TokenType> typesMap = Stream.of(values())
            .collect(Collectors.toMap(v -> v.name().toLowerCase(), Function.identity()));

    public static Map<String, TokenType> getTypesMap() {
        return Collections.unmodifiableMap(typesMap);
    }
}
