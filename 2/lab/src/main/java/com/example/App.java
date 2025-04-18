package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Введите мат. выражение( Пример: (3+5)*10 ): ");
        String mathExpression = inputScanner.nextLine();
        
        Map<String, Double> variableValues = extractVariables(mathExpression, inputScanner);

        try {
            double calculationResult = Calculator.calculate(mathExpression, variableValues);
            System.out.println("Результат: " + calculationResult);
        } catch (IllegalArgumentException error) {
            System.out.println("Ошибка: " + error.getMessage());    
        } catch (ArithmeticException error) {
            System.out.println("Ошибка в рассчетах: " + error.getMessage());
        }

        inputScanner.close();
    }

    private static Map<String, Double> extractVariables(String expr, Scanner scanner) {
        Map<String, Double> vars = new HashMap<>();
        char[] chars = expr.toCharArray();

        for (int idx = 0; idx < expr.length(); idx++) {
            if (Character.isLetter(chars[idx])) {
                StringBuilder varBuilder = new StringBuilder();

                while (idx < chars.length && Character.isLetter(chars[idx])) {
                    varBuilder.append(chars[idx++]);
                }
                idx--;

                String currentVar = varBuilder.toString();

                if (!vars.containsKey(currentVar) && !Calculator.containsFunction(currentVar)) {
                    System.out.print("Укажите значение для " + currentVar + ": ");
                    vars.put(currentVar, scanner.nextDouble());
                }
            }
        }
        return vars;
    }
}