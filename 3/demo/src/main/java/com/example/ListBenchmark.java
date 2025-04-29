package com.example;

import java.util.*;

public class ListBenchmark {
    
    public static long testAdd(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.add(i);
        }
        return System.nanoTime() - start;
    }

    public static long testGet(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.get(i);
        }
        return System.nanoTime() - start;
    }

    public static long testRemove(List<Integer> list, int operationsCount) {
        long start = System.nanoTime();
        for (int i = 0; i < operationsCount; i++) {
            list.remove(0);
        }
        return System.nanoTime() - start;
    }
}