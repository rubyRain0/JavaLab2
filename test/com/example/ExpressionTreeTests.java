package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

/**
 * This class contains a set of JUnit tests for the expression tree builder and evaluator.
 */
public class ExpressionTreeTests {

    /**
     * Tests building and evaluating a simple expression tree with no variables or functions.
     *
     * @throws Exception If there is an error during building or evaluating the expression tree.
     */
    @Test
    public void testSimpleExpression() throws Exception {
        String expression = "2 + 3";
        Map<String, Double> variables = new HashMap<>();
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
        double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
        assertEquals(5.0, result, 0.0);
    }

    /**
     * Tests building and evaluating an expression tree with a variable.
     *
     * @throws Exception If there is an error during building or evaluating the expression tree.
     */
    @Test
    public void testVariableExpression() throws Exception {
        String expression = "x + 3";
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 2.0);
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
        double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
        assertEquals(5.0, result, 0.0);
    }

    /**
     * Tests building and evaluating an expression tree with a function.
     *
     * @throws Exception If there is an error during building or evaluating the expression tree.
     */
    @Test
    public void testFunctionExpression() throws Exception {
        String expression = "sin(pi/2)";
        Map<String, Double> variables = new HashMap<>();
        variables.put("pi", Math.PI);
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        functions.put("sin", Math::sin);
        Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
        double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
        assertEquals(1.0, result, 0.0);
    }

    /**
     * Tests building and evaluating a complex expression tree with variables and operators.
     *
     * @throws Exception If there is an error during building or evaluating the expression tree.
     */
    @Test
    public void testComplexExpression() throws Exception {
        String expression = "2 * (x + 3) ^ (1 / y)";
        Map<String, Double> variables = new HashMap<>();
        variables.put("x", 2.0);
        variables.put("y", 2.0);
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
        double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
        assertEquals(4.47, result, 0.01);
    }

    /**
     * Tests building and evaluating an invalid expression tree.
     *
     * @throws Exception If there is an error during building or evaluating the expression tree.
     */
    @Test(expected = Exception.class)
    public void testInvalidExpression() throws Exception {
        String expression = "2 + * 3";
        Map<String, Double> variables = new HashMap<>();
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
        double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
    }
}
