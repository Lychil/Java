package com.example;

public class BenchmarkResults {

    public static void display(String collectionType, int opsCount, long addNs, long getNs, long removeNs) {
        String row = String.format(
            "| %-15s | %-16d | %-16d | %-16d | %-16d |",
            collectionType, opsCount, addNs, getNs, removeNs
        );
        System.out.println(row);
    }
}