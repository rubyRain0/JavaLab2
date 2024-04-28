package com.example;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * This class is responsible for building an expression tree from a given mathematical expression.
 * It uses a Tokenizer to break down the expression into tokens and a Parser to construct the tree.
 */
public class ExpressionTreeBuilder {

    /**
     * This method builds and returns an expression tree from a given mathematical expression.
     *
     * @param expression The mathematical expression to be converted into an expression tree.
     * @param variables A map of variables in the expression to their respective values.
     * @param functions A map of functions in the expression to their respective implementations.
     * @return The root node of the expression tree.
     * @throws Exception If there is an error in the expression, during tokenization, or during parsing.
     */
    public static Node buildExpressionTree(String expression, Map<String, Double> variables, Map<String, Function<Double, Double>> functions) throws Exception {
        Tokenizer tokenizer = new Tokenizer(expression);
        List<Token> tokens = tokenizer.tokenize();
        Parser parser = new Parser(tokens, variables, functions);
        return parser.parse();
    }
}
