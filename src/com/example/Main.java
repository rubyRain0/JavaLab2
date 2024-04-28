package com.example;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression:");
        String expression = scanner.nextLine();

        Map<String, Double> variables = new HashMap<>();
        Map<String, Function<Double, Double>> functions = new HashMap<>();
        functions.put("sin", Math::sin);
        functions.put("cos", Math::cos);
        functions.put("tan", Math::tan);
        functions.put("ln", Math::log);

        try {
            Node root = ExpressionTreeBuilder.buildExpressionTree(expression, variables, functions);
            double result = ExpressionTreeEvaluator.evaluateExpressionTree(root, variables, functions);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
