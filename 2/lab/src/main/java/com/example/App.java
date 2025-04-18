package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс {@code App} является точкой входа в приложение-калькулятор.
 * Он запрашивает у пользователя математическое выражение и переменные (если есть),
 * вычисляет результат и выводит его в консоль.
 */
public class App {

    /**
     * Главный метод приложения. Запрашивает у пользователя выражение,
     * обрабатывает переменные, вычисляет результат и выводит его.
     *
     * @param args аргументы командной строки (не используются)
     */
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

    /**
     * Извлекает переменные из выражения и запрашивает у пользователя их значения.
     * Функции (например, sin, cos) игнорируются и не запрашиваются.
     *
     * @param expr    строка с математическим выражением
     * @param scanner сканнер для считывания значений переменных от пользователя
     * @return отображение имён переменных и их значений
     */
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
