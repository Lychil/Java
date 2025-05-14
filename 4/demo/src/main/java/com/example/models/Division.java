package com.example.models;

public class Division {
    private static int idCounter = 100;
    private final int divisionId;
    private final String divisionName;

    public Division(String divisionName) {
        this.divisionId = nextId();
        this.divisionName = divisionName;
    }

    private static int nextId() {
        return idCounter++;
    }

    @Override
    public String toString() {
        return "Division{" +
                "divisionId=" + divisionId +
                ", divisionName='" + divisionName + '\'' +
                '}';
    }
}
