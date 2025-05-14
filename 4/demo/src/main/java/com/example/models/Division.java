package com.example.models;

/**
 * Класс, представляющий подразделение компании.
 */
public class Division {
    private static int idCounter = 100;
    private final int divisionId;
    private final String divisionName;

    /**
     * Конструктор класса Division.
     *
     * @param divisionName название подразделения.
     */
    public Division(String divisionName) {
        this.divisionId = nextId();
        this.divisionName = divisionName;
    }

    /**
     * Генерирует уникальный ID для нового подразделения.
     *
     * @return уникальный ID.
     */
    private static int nextId() {
        return idCounter++;
    }

    /**
     * Возвращает строковое представление подразделения.
     *
     * @return строковое представление подразделения.
     */
    @Override
    public String toString() {
        return "Division{" +
                "divisionId=" + divisionId +
                ", divisionName='" + divisionName + '\'' +
                '}';
    }
}
