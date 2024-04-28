package com.example;

/**
 * This class represents a token in an expression.
 * Each token has a value and a type.
 */
public class Token {

    /**
     * The value of the token. This could be a number, a variable, an operator, a function name, or a parenthesis.
     */
    private String value;

    /**
     * The type of the token. This could be a NUMBER, a VARIABLE_OR_FUNCTION, an OPERATOR, a LEFT_PAREN, or a RIGHT_PAREN.
     */
    private TokenType type;

    /**
     * Constructs a new token with the given value and type.
     *
     * @param value The value of the token.
     * @param type The type of the token.
     */
    public Token(String value, TokenType type) {
        this.value = value;
        this.type = type;
    }

    /**
     * Returns the value of the token.
     *
     * @return The value of the token.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the type of the token.
     *
     * @return The type of the token.
     */
    public TokenType getType() {
        return type;
    }

    /**
     * Returns a string representation of the token.
     *
     * @return A string representation of the token.
     */
    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", type=" + type +
                '}';
    }
}
