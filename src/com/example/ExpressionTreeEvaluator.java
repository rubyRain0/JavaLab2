package com.example;

import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.function.Function;

/**
 * This class is responsible for evaluating an expression tree.
 * It traverses the tree and computes the result of the expression it represents.
 */
public class ExpressionTreeEvaluator {

    /**
     * This method evaluates an expression tree and returns the result.
     *
     * @param node The root node of the expression tree.
     * @param variables A map of variables in the expression to their respective values.
     * @param functions A map of functions in the expression to their respective implementations.
     * @return The result of the expression represented by the tree.
     * @throws Exception If there is an unknown node type, an unknown operator, or if a variable is not defined.
     */
    public static double evaluateExpressionTree(Node node, Map<String, Double> variables, Map<String, Function<Double, Double>> functions) throws Exception {
        if (node.getType() == NodeType.NUMBER) {
            return Double.parseDouble(node.getValue());
        } else if (node.getType() == NodeType.VARIABLE_OR_FUNCTION) {
            String name = node.getValue();
            if (functions.containsKey(name)) {
                Node argument = node.getChildren().get(0);
                double value = evaluateExpressionTree(argument, variables, functions);
                return functions.get(name).apply(value);
            } else {
                if (!variables.containsKey(name)) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter value for variable " + name + ":");
                    double value = scanner.nextDouble();
                    variables.put(name, value);
                }
                return variables.get(name);
            }
        } else if (node.getType() == NodeType.OPERATOR) {
            String operator = node.getValue();
            Node left = node.getChildren().get(0);
            Node right = node.getChildren().get(1);
            double leftValue = evaluateExpressionTree(left, variables, functions);
            double rightValue = evaluateExpressionTree(right, variables, functions);
            if (operator.equals("+")) {
                return leftValue + rightValue;
            } else if (operator.equals("-")) {
                return leftValue - rightValue;
            } else if (operator.equals("*")) {
                return leftValue * rightValue;
            } else if (operator.equals("/")) {
                return leftValue / rightValue;
            } else if (operator.equals("^")) {
                return Math.pow(leftValue, rightValue);
            } else {
                throw new Exception("Unknown operator: " + operator);
            }
        } else {
            throw new Exception("Unknown node type: " + node.getType());
        }
    }
}
