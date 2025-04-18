package com.example;

import java.util.Map;
import java.util.Stack;

public class Calculator {

    public static boolean containsFunction(String input) {
        try {
            Logic.getByName(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean isValid(char[] tokens) {
        Stack<Character> stack = new Stack<>();

        for (char token : tokens) {
            if (token == ' ') continue;
            if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (!Character.isDigit(token) && !isOperator(token) && 
                      token != '.' && !Character.isLetter(token)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static double calculate(String expression, Map<String, Double> variables) {
        char[] tokens = expression.toCharArray();

        if (!isValid(tokens)) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            if (Character.isDigit(tokens[i])) {
                StringBuilder numStr = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    numStr.append(tokens[i++]);
                }
                numbers.push(Double.parseDouble(numStr.toString()));
                i--;
            } 
            else if (Character.isLetter(tokens[i])) {
                StringBuilder name = new StringBuilder();
                boolean isFunction = false;

                while (i < tokens.length && (Character.isLetter(tokens[i]) || tokens[i] == '(')) {
                    if (tokens[i] == '(') {
                        isFunction = true;
                        i++;
                        break;
                    }
                    name.append(tokens[i++]);
                }

                if (isFunction) {
                    String func = name.toString();
                    StringBuilder argStr = new StringBuilder();
                    
                    while (i < tokens.length && tokens[i] != ')') {
                        argStr.append(tokens[i++]);
                    }
                    
                    String argument = argStr.toString();
                    Logic function = Logic.getByName(func);
                    
                    try {
                        double val = Double.parseDouble(argument);
                        numbers.push(function.execute(val));
                    } 
                    catch (NumberFormatException e) {
                        if (!variables.containsKey(argument)) {
                            throw new IllegalArgumentException("Переменная не найдена: " + argument);
                        }
                        numbers.push(function.execute(variables.get(argument)));
                    }
                } else {
                    String var = name.toString();
                    if (!variables.containsKey(var)) {
                        throw new IllegalArgumentException("Переменная не найдена: " + var);
                    }
                    numbers.push(variables.get(var));
                    i--;
                }
            } 
            else if (tokens[i] == '(') {
                operations.push(tokens[i]);
            } 
            else if (tokens[i] == ')') {
                while (operations.peek() != '(') {
                    numbers.push(computeOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            } 
            else if (isOperator(tokens[i])) {
                while (!operations.empty() && getPriority(tokens[i]) <= getPriority(operations.peek())) {
                    numbers.push(computeOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(tokens[i]);
            }
        }

        while (!operations.empty()) {
            numbers.push(computeOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private static int getPriority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private static double computeOperation(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': 
                if (b == 0) throw new ArithmeticException("Делить на ноль запрещено");
                return a / b;
            default: throw new IllegalArgumentException("Оператор не допустим: " + op);
        }
    }
}