package Lexing;



public class Token {


    public enum TokenType {
        WORD,
        NUMBER,
        ENDOFLINE,
        PRINT,
        READ,
        DATA,
        GOSUB,
        FOR,
        TO,
        STEP,
        NEXT,
        RETURN,
        IF,
        THEN,
        Function,
        While,
        END,
        StringLiteral,

        SINGLE_CHAR_SYMBOL,

        TWO_CHAR_SYMBOL,

        RIGHT_PAREN,

        LEFT_PAREN,

        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        EQUALS,

        NOT_EQUAL,
        PLUS,

        MINUS,

        MULTIPLY,

        DIVIDE,
        LABEL,





    }

    private String value;
    private TokenType token;
    private int lineNumber;
    private int position;



    public Token(TokenType token, int lineNumber, int position) {
        this.token = token;
        this.lineNumber = lineNumber;
        this.position = position;
    }


    public Token(TokenType token, int lineNumber, int position, String value) {
        this.token = token;
        this.lineNumber = lineNumber;
        this.position = position;
        this.value = value;
    }



    public String toString() {
        return "\n {Token: " + token + ", Value: " + value + ", Line: " + lineNumber + ", position: " + position + " }";

    }
    /**
     * @return the Token Value
     */
    public TokenType getType() {
        return token;
    }
}