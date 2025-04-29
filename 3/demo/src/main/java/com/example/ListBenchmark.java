package com.example;

import java.util.List;

/**
 * Класс для измерения производительности операций со списками.
 * Предоставляет методы для тестирования времени выполнения операций
 * добавления, получения и удаления элементов.
 */
public class ListBenchmark {
    
    /**
     * Измеряет время выполнения операций добавления элементов в список.
     *
     * @param list список для тестирования
     * @param operationsCount количество добавляемых элементов
     * @return время выполнения операций в наносекундах
     * @throws NullPointerException если переданный список равен null
     */
    public static long testAdd(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.add(i);
        }
        return System.nanoTime() - start;
    }

    /**
     * Измеряет время выполнения операций получения элементов из списка.
     *
     * @param list список для тестирования
     * @param operationsCount количество операций получения
     * @return время выполнения операций в наносекундах
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     * @throws NullPointerException если переданный список равен null
     */
    public static long testGet(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.get(i);
        }
        return System.nanoTime() - start;
    }

    /**
     * Измеряет время выполнения операций удаления элементов из списка.
     *
     * @param list список для тестирования
     * @param operationsCount количество удаляемых элементов
     * @return время выполнения операций в наносекундах
     * @throws IndexOutOfBoundsException если список пуст
     * @throws NullPointerException если переданный список равен null
     */
    public static long testRemove(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.remove(0);
        }
        return System.nanoTime() - start;
    }
}