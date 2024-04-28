package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for tokenizing an expression into a list of tokens.
 */
public class Tokenizer {

    /**
     * The expression to be tokenized.
     */
    private String expression;

    /**
     * The current position in the expression being tokenized.
     */
    private int position;

    /**
     * Constructs a new tokenizer with the given expression.
     * The current position is initially set to 0.
     *
     * @param expression The expression to be tokenized.
     */
    public Tokenizer(String expression) {
        this.expression = expression;
        this.position = 0;
    }

    /**
     * Tokenizes the expression and returns a list of tokens.
     *
     * @return A list of tokens representing the expression.
     * @throws Exception If there is an error during tokenization.
     */
    public List<Token> tokenize() throws Exception {
        List<Token> tokens = new ArrayList<>();
        while (position < expression.length()) {
            char ch = expression.charAt(position);
            if (Character.isWhitespace(ch)) {
                position++;
            } else if (Character.isDigit(ch)) {
                tokens.add(readNumber());
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') {
                tokens.add(new Token(ch + "", TokenType.OPERATOR));
                position++;
            } else if (ch == '(') {
                tokens.add(new Token("(", TokenType.LEFT_PAREN));
                position++;
            } else if (ch == ')') {
                tokens.add(new Token(")", TokenType.RIGHT_PAREN));
                position++;
            } else if (ch == ',') {
                tokens.add(new Token(",", TokenType.COMMA));
                position++;
            } else {
                tokens.add(readVariableOrFunction());
            }
        }
        tokens.add(new Token("", TokenType.EOF));
        return tokens;
    }

    /**
     * Reads a number from the expression and returns a token representing it.
     *
     * @return A token representing the number.
     * @throws Exception If there is an error during reading.
     */
    private Token readNumber() throws Exception {
        StringBuilder builder = new StringBuilder();
        while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
            builder.append(expression.charAt(position++));
        }
        if (position < expression.length() && expression.charAt(position) == '.') {
            builder.append(expression.charAt(position++));
            while (position < expression.length() && Character.isDigit(expression.charAt(position))) {
                builder.append(expression.charAt(position++));
            }
        }
        try {
            double value = Double.parseDouble(builder.toString());
            return new Token(value + "", TokenType.NUMBER);
        } catch (NumberFormatException e) {
            throw new Exception("Invalid number: " + builder.toString());
        }
    }

    /**
     * Reads a variable or function name from the expression and returns a token representing it.
     *
     * @return A token representing the variable or function name.
     * @throws Exception If there is an error during reading.
     */
    private Token readVariableOrFunction() throws Exception {
        StringBuilder builder = new StringBuilder();
        while (position < expression.length() && Character.isLetter(expression.charAt(position))) {
            builder.append(expression.charAt(position++));
        }
        String name = builder.toString();
        if (name.isEmpty()) {
            throw new Exception("Unexpected character: " + expression.charAt(position));
        }
        return new Token(name, TokenType.VARIABLE_OR_FUNCTION);
    }
}
