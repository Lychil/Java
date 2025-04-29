package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Главный класс приложения для тестирования производительности List-реализаций.
 * Сравнивает производительность ArrayList и LinkedList при выполнении базовых операций.
 */
public class Main {

    /**
     * Точка входа в приложение.
     * 
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        final int COUNT = 10000;
        
        testListPerformance(new ArrayList<>(), "ArrayList", COUNT);
        testListPerformance(new LinkedList<>(), "LinkedList", COUNT);
    }

    /**
     * Тестирует производительность операций добавления, получения и удаления элементов.
     * 
     * @param list тестируемая реализация List
     * @param name название реализации (для вывода результатов)
     * @param count количество операций для выполнения
     */
    private static void testListPerformance(List<Integer> list, String name, int count) {
        long addTime = ListBenchmark.testAdd(list, count);
        long getTime = ListBenchmark.testGet(list, count);
        long removeTime = ListBenchmark.testRemove(list, count);

        if (list instanceof ArrayList) {
            System.out.println("Performance Test Results:");
            System.out.println("| List Type       | Operations Count | Add Time         | Get Time         | Remove Time      |");
            System.out.println("|-----------------|------------------|------------------|------------------|------------------|");
        }
        
        BenchmarkResults.display(name, count, addTime, getTime, removeTime);
    }
}