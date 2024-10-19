package RPN.token;

public class Token {
    private final TokenType kind;
    private final String lexeme;
    private final Object value; // literal
    private final int start;
    private final int end;
    private final int line;

    private Token(
            TokenType kind,
            String lexeme,
            Object value,
            int start,
            int end,
            int line
    ) {
        this.kind = kind;
        this.lexeme = lexeme;
        this.value = value;
        this.start = start;
        this.end = end;
        this.line = line;
    }

    public TokenType getKind() {
        return kind;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Object getValue() {
        return value;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getLine() {
        return line;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return kind + " " + lexeme + " " + value;
    }

    public static class Builder {
        private TokenType kind;
        private String lexeme;
        private Object value; // literal
        private int start;
        private int end;
        private int line;

        public Builder kind(TokenType kind) {
            this.kind = kind;
            return this;
        }

        public Builder lexeme(String lexeme) {
            this.lexeme = lexeme;
            return this;
        }

        public Builder value(Object value) {
            this.value = value;
            return this;
        }

        public Builder start(int start) {
            this.start = start;
            return this;
        }

        public Builder end(int end) {
            this.end = end;
            return this;
        }

        public Builder line(int line) {
            this.line = line;
            return this;
        }

        public Token build() {
            return new Token(
                    kind,
                    lexeme,
                    value,
                    start,
                    end,
                    line
            );
        }
    }
}
