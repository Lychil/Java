package com.example;

import java.util.Map;
import java.util.Stack;

/**
 * Класс {@code Calculator} выполняет вычисление арифметических выражений,
 * поддерживает переменные, стандартные арифметические операторы и встроенные функции.
 */
public class Calculator {

    /**
     * Проверяет, является ли указанное имя допустимым названием функции.
     *
     * @param input строка для проверки
     * @return {@code true}, если это имя зарегистрированной функции, иначе {@code false}
     */
    public static boolean containsFunction(String input) {
        try {
            Logic.getByName(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли символ допустимым оператором.
     *
     * @param c символ
     * @return {@code true}, если это один из: +, -, *, /, иначе {@code false}
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Проверяет, является ли переданное выражение корректным с точки зрения синтаксиса.
     * Учитываются только допустимые символы и сбалансированные скобки.
     *
     * @param tokens массив символов выражения
     * @return {@code true}, если выражение корректно, иначе {@code false}
     */
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

    /**
     * Выполняет вычисление переданного математического выражения.
     *
     * @param expression строка выражения (например: {@code (3 + a) * sqrt(16)})
     * @param variables карта с названиями переменных и их значениями
     * @return результат вычисления выражения
     * @throws IllegalArgumentException если выражение некорректное или переменные не определены
     * @throws ArithmeticException если происходит деление на ноль
     */
    public static double calculate(String expression, Map<String, Double> variables) {
        char[] tokens = expression.toCharArray();

        if (!isValid(tokens)) {
            throw new IllegalArgumentException("Некорректное выражение");
        }

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            // Парсинг чисел
            if (Character.isDigit(tokens[i])) {
                StringBuilder numStr = new StringBuilder();
                while (i < tokens.length && (Character.isDigit(tokens[i]) || tokens[i] == '.')) {
                    numStr.append(tokens[i++]);
                }
                numbers.push(Double.parseDouble(numStr.toString()));
                i--;
            }
            // Парсинг переменных и функций
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
                    } catch (NumberFormatException e) {
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
            // Открывающая скобка
            else if (tokens[i] == '(') {
                operations.push(tokens[i]);
            }
            // Закрывающая скобка
            else if (tokens[i] == ')') {
                while (operations.peek() != '(') {
                    numbers.push(computeOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            }
            // Арифметические операторы
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

    /**
     * Возвращает приоритет оператора.
     *
     * @param op оператор
     * @return 2 для * и /, 1 для + и -, 0 по умолчанию
     */
    private static int getPriority(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    /**
     * Выполняет математическую операцию над двумя числами.
     *
     * @param op оператор (+, -, *, /)
     * @param b второй операнд
     * @param a первый операнд
     * @return результат операции
     * @throws ArithmeticException при делении на ноль
     * @throws IllegalArgumentException при неизвестном операторе
     */
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
