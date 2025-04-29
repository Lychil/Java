package com.example;

/**
 * Утилитный класс для форматированного вывода результатов тестирования производительности.
 * Предоставляет метод для отображения результатов в табличном формате.
 */
public class BenchmarkResults {

    /**
     * Выводит строку с результатами тестирования в табличном формате.
     *
     * @param collectionType тип тестируемой коллекции (например, "ArrayList" или "LinkedList")
     * @param opsCount количество выполненных операций
     * @param addNs время выполнения операций добавления в наносекундах
     * @param getNs время выполнения операций получения в наносекундах
     * @param removeNs время выполнения операций удаления в наносекундах
     * @throws IllegalArgumentException если collectionType равен null
     */
    public static void display(String collectionType, int opsCount, long addNs, long getNs, long removeNs) {
        String row = String.format(
            "| %-15s | %-16d | %-16d | %-16d | %-16d |",
            collectionType, opsCount, addNs, getNs, removeNs
        );
        System.out.println(row);
    }
}