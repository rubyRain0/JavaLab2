package com.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This class is responsible for parsing an expression represented as a list of tokens into an expression tree.
 */
public class Parser {

    /**
     * The list of tokens representing the expression to be parsed.
     */
    private List<Token> tokens;

    /**
     * The index of the current token being parsed.
     */
    private int current;

    /**
     * A map of variables in the expression to their respective values.
     */
    private Map<String, Double> variables;

    /**
     * A map of functions in the expression to their respective implementations.
     */
    private Map<String, Function<Double, Double>> functions;

    /**
     * Constructs a new parser with the given tokens, variables, and functions.
     * The current index is initially set to 0.
     *
     * @param tokens The list of tokens representing the expression to be parsed.
     * @param variables A map of variables in the expression to their respective values.
     * @param functions A map of functions in the expression to their respective implementations.
     */
    public Parser(List<Token> tokens, Map<String, Double> variables, Map<String, Function<Double, Double>> functions) {
        this.tokens = tokens;
        this.variables = variables;
        this.functions = functions;
        this.current = 0;
    }

    /**
     * Parses the expression represented by the list of tokens and returns the root node of the resulting expression tree.
     *
     * @return The root node of the expression tree.
     * @throws Exception If there is an error during parsing.
     */
    public Node parse() throws Exception {
        return expression();
    }

    /**
     * Parses an expression, which is a sequence of terms separated by addition or subtraction operators.
     *
     * @return The root node of the subtree representing the expression.
     * @throws Exception If there is an error during parsing.
     */
    private Node expression() throws Exception {
        Node result = term();
        while (current < tokens.size() && (tokens.get(current).getType() == TokenType.OPERATOR &&
                ("+".equals(tokens.get(current).getValue()) || "-".equals(tokens.get(current).getValue())))) {
            Token operator = tokens.get(current++);
            Node right = term();
            Node parent = new Node(operator.getValue(), NodeType.OPERATOR);
            parent.addChild(result);
            parent.addChild(right);
            result = parent;
        }
        return result;
    }

    /**
     * Parses a term, which is a sequence of powers separated by multiplication or division operators.
     *
     * @return The root node of the subtree representing the term.
     * @throws Exception If there is an error during parsing.
     */
    private Node term() throws Exception {
        Node result = power();
        while (current < tokens.size() && (tokens.get(current).getType() == TokenType.OPERATOR &&
                ("*".equals(tokens.get(current).getValue()) || "/".equals(tokens.get(current).getValue())))) {
            Token operator = tokens.get(current++);
            Node right = power();
            Node parent = new Node(operator.getValue(), NodeType.OPERATOR);
            parent.addChild(result);
            parent.addChild(right);
            result = parent;
        }
        return result;
    }

    /**
     * Parses a power, which is a factor raised to another factor.
     *
     * @return The root node of the subtree representing the power.
     * @throws Exception If there is an error during parsing.
     */
    private Node power() throws Exception {
        Node result = factor();
        while (current < tokens.size() && tokens.get(current).getType() == TokenType.OPERATOR &&
                "^".equals(tokens.get(current).getValue())) {
            Token operator = tokens.get(current++);
            Node right = factor();
            Node parent = new Node(operator.getValue(), NodeType.OPERATOR);
            parent.addChild(result);
            parent.addChild(right);
            result = parent;
        }
        return result;
    }

    /**
     * Parses a factor, which can be a number, a variable or function, or an expression enclosed in parentheses.
     *
     * @return The root node of the subtree representing the factor.
     * @throws Exception If there is an error during parsing.
     */
    private Node factor() throws Exception {
        Node result;
        if (tokens.get(current).getType() == TokenType.NUMBER) {
            result = new Node(tokens.get(current++).getValue(), NodeType.NUMBER);
        } else if (tokens.get(current).getType() == TokenType.VARIABLE_OR_FUNCTION) {
            String name = tokens.get(current++).getValue();
            if (functions.containsKey(name)) {
                if (tokens.get(current).getType() != TokenType.LEFT_PAREN) {
                    throw new Exception("Expected '(' after function name: " + name);
                }
                current++;
                Node argument = expression();
                if (tokens.get(current).getType() != TokenType.RIGHT_PAREN) {
                    throw new Exception("Expected ')' after function argument");
                }
                current++;
                Node function = new Node(name, NodeType.VARIABLE_OR_FUNCTION);
                function.addChild(argument);
                result = function;
            } else {
                Node variable = new Node(name, NodeType.VARIABLE_OR_FUNCTION);
                result = variable;
            }
        } else if (tokens.get(current).getType() == TokenType.LEFT_PAREN) {
            current++;
            result = expression();
            if (tokens.get(current).getType() != TokenType.RIGHT_PAREN) {
                throw new Exception("Expected ')'");
            }
            current++;
        } else {
            throw new Exception("Unexpected token: " + tokens.get(current));
        }
        return result;
    }
}
